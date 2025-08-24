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
public class MovementServiceImpl implements MovementService {
    private final MovementRepository movementRepository;
    private final MovementDtoMapper movementDtoMapper;

    @Override
    @Transactional
    public MovementDto updateMovement(Long id, UpdateMovementDto dto) {
        Movement movement = movementRepository.getMovementById(id)
                .orElseThrow(BusinessException.Type.MOVEMENT_NOT_EXISTS::build);
        Movement movementToUpdate = movement.toBuilder()
                .tipoMovimiento(dto.tipoMovimiento())
                .estado(dto.estado())
                .build();
        Movement updated = movementRepository.saveMovement(movementToUpdate);
        return movementDtoMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public MovementDto getMovementById(Long id) {
        Movement movement = movementRepository.getMovementById(id)
                .orElseThrow(BusinessException.Type.MOVEMENT_NOT_EXISTS::build);
        return movementDtoMapper.toDto(movement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementDto> getAllMovements() {
        return movementRepository.getAllMovements().stream()
                .map(movementDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteMovement(Long id) {
        Movement movement = movementRepository.getMovementById(id)
                .orElseThrow(BusinessException.Type.MOVEMENT_NOT_EXISTS::build);
        movement.softDelete();
        movementRepository.saveMovement(movement);
    }
    
    private  LocalDateTime nowInUTCMinus5() {
        return ZonedDateTime.now(ZoneId.of("America/Bogota")).toLocalDateTime();
    }
    
    
}
