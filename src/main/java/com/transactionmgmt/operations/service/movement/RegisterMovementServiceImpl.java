package com.transactionmgmt.operations.service.movement;

import com.transactionmgmt.operations.domain.account.Account;
import com.transactionmgmt.operations.domain.movement.Movement;
import com.transactionmgmt.operations.persistence.adapters.movement.MovementRepository;
import com.transactionmgmt.operations.service.account.AccountService;
import com.transactionmgmt.operations.service.dto.mappers.MovementDtoMapper;
import com.transactionmgmt.operations.service.dto.movement.CreateMovementDto;
import com.transactionmgmt.operations.service.dto.movement.MovementDto;
import com.transactionmgmt.operations.service.dto.movement.UpdateMovementDto;
import com.transactionmgmt.operations.service.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        
        Movement movement = Movement.builder()
                
                .build();
        Movement saved = movementRepository.saveMovement(movement);
        return movementDtoMapper.toDto(saved);
    }
    
}
