package com.transactionmgmt.operations.service.movement;

import com.transactionmgmt.operations.domain.account.Account;
import com.transactionmgmt.operations.domain.movement.Movement;
import com.transactionmgmt.operations.persistence.adapters.movement.MovementRepository;
import com.transactionmgmt.operations.service.account.AccountService;
import com.transactionmgmt.operations.service.dto.mappers.MovementDtoMapper;
import com.transactionmgmt.operations.service.dto.movement.CreateMovementDto;
import com.transactionmgmt.operations.service.dto.movement.MovementDto;
import com.transactionmgmt.operations.service.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class RegisterMovementServiceImpl implements RegisterMovementService {
    private final MovementRepository movementRepository;
    private final MovementDtoMapper movementDtoMapper;
    private final AccountService accountService;

    @Override
    @Transactional
    public MovementDto registerMovement(CreateMovementDto dto) {

        Account relatedAccount = accountService.getAccountById(dto.cuentaId());
        double initialAccountBalance = relatedAccount.getSaldoInicial();
        double newAccountBalance = validateAccountFunds(dto, initialAccountBalance);

        Account accountUpdated = updateBalanceAccount(relatedAccount, newAccountBalance);
        
        Movement movement = Movement.builder()
                .fecha(nowInUTCMinus5())
                .tipoMovimiento(dto.tipoMovimiento())
                .valor(dto.valor())
                .saldoInicial(initialAccountBalance)
                .saldoFinal(newAccountBalance)
                .cuenta(accountUpdated)
                .build();
        movement.setDefaultValues();
        Movement saved = movementRepository.saveMovement(movement);
        return movementDtoMapper.toDto(saved, saved.getCuenta().getNumeroCuenta());
    }

    private Account updateBalanceAccount(Account relatedAccount, double newAccountBalance) {
        Account accountUpdated = relatedAccount.toBuilder()
                .saldoInicial(newAccountBalance)
                .build();
        accountUpdated = accountService.saveAccount(accountUpdated);
        return accountUpdated;
    }

    private double validateAccountFunds(CreateMovementDto dto, double initialAccountBalance) {
        double newAccountBalance = initialAccountBalance + dto.valor();

        if(newAccountBalance < 0){
            throw BusinessException.Type.NOT_ENOUGH_FUNDS.build();
        }
        return newAccountBalance;
    }

    private LocalDate nowInUTCMinus5() {
        return ZonedDateTime.now(ZoneId.of("America/Bogota")).toLocalDate();
    }

}

