package com.transactionmgmt.operations.service.movement;

import com.transactionmgmt.operations.domain.account.Account;
import com.transactionmgmt.operations.domain.account.AccountType;
import com.transactionmgmt.operations.domain.movement.Movement;
import com.transactionmgmt.operations.domain.movement.MovementType;
import com.transactionmgmt.operations.persistence.adapters.movement.MovementRepository;
import com.transactionmgmt.operations.service.account.AccountService;
import com.transactionmgmt.operations.service.dto.mappers.MovementDtoMapper;
import com.transactionmgmt.operations.service.dto.movement.CreateMovementDto;
import com.transactionmgmt.operations.service.dto.movement.MovementDto;
import com.transactionmgmt.operations.service.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterMovementServiceImplTest {
    @Mock
    private MovementRepository movementRepository;
    @Mock
    private MovementDtoMapper movementDtoMapper;
    @Mock
    private AccountService accountService;
    @InjectMocks
    private RegisterMovementServiceImpl registerMovementService;
    

    @Test
    void registerMovementAddMoney_successful() {
        CreateMovementDto dto = new CreateMovementDto(1L, MovementType.AHORROS, 100D);
        Account account = getAccount();
        Movement movement = getMovement();

        when(accountService.getAccountById(anyLong())).thenReturn(account);
        when(accountService.saveAccount(any(Account.class))).thenReturn(account);
        when(movementRepository.saveMovement(any(Movement.class))).thenReturn(movement);
        when(movementDtoMapper.toDto(any(), anyLong())).thenReturn(mock(MovementDto.class));

        MovementDto result = registerMovementService.registerMovement(dto);

        assertNotNull(result);

        ArgumentCaptor<Movement> movementCaptor = ArgumentCaptor.forClass(Movement.class);
        verify(movementRepository, times(1)).saveMovement(movementCaptor.capture());
        Movement capturedMovement = movementCaptor.getValue();

        assertEquals(1000.0, capturedMovement.getSaldoInicial());
        assertEquals(1100, capturedMovement.getSaldoFinal());
        assertEquals(100.0, capturedMovement.getValor());
        assertTrue(capturedMovement.isEstado());
        assertTrue(capturedMovement.isActivo());
        assertEquals(1234567890L, capturedMovement.getCuenta().getNumeroCuenta());
        

        verify(accountService, times(1)).getAccountById(1L);
        verify(movementDtoMapper, times(1)).toDto(any(), anyLong());
    }

    @Test
    void registerMovementRetireMoney_successful() {
        CreateMovementDto dto = new CreateMovementDto(1L, MovementType.AHORROS, -600D);
        Account account = getAccount();
        Movement movement = getMovement();

        when(accountService.getAccountById(anyLong())).thenReturn(account);
        when(accountService.saveAccount(any(Account.class))).thenReturn(account);
        when(movementRepository.saveMovement(any(Movement.class))).thenReturn(movement);
        when(movementDtoMapper.toDto(any(), anyLong())).thenReturn(mock(MovementDto.class));

        MovementDto result = registerMovementService.registerMovement(dto);

        assertNotNull(result);

        ArgumentCaptor<Movement> movementCaptor = ArgumentCaptor.forClass(Movement.class);
        verify(movementRepository, times(1)).saveMovement(movementCaptor.capture());
        Movement capturedMovement = movementCaptor.getValue();

        assertEquals(1000.0, capturedMovement.getSaldoInicial());
        assertEquals(400.0, capturedMovement.getSaldoFinal());
        assertEquals(-600.0, capturedMovement.getValor());
        assertTrue(capturedMovement.isEstado());
        assertTrue(capturedMovement.isActivo());
        assertEquals(1234567890L, capturedMovement.getCuenta().getNumeroCuenta());


        verify(accountService, times(1)).getAccountById(1L);
        verify(movementDtoMapper, times(1)).toDto(any(), anyLong());
    }

    @Test
    void registerMovement_AccountNotFound() {
        CreateMovementDto dto = mock(CreateMovementDto.class);

        when(accountService.getAccountById(anyLong())).thenThrow(BusinessException.Type.ACCOUNT_NOT_EXISTS.build());

        assertThrows(BusinessException.class, () -> registerMovementService.registerMovement(dto));
    }

    Account getAccount() {
        return new Account(1L, 1234567890L, AccountType.AHORROS, 1000.0, true, 1L);
    }

    Movement getMovement() {
        return Movement.builder()
                .id(1L)
                .cuenta(getAccount())
                .saldoInicial(1000.0)
                .saldoFinal(900.0)
                .valor(100.0)
                .estado(true)
                .build();
    }
}
