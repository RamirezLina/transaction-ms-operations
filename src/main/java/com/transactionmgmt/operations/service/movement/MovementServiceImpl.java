package com.transactionmgmt.operations.service.movement;

import com.transactionmgmt.operations.domain.movement.Movement;
import com.transactionmgmt.operations.persistence.adapters.movement.MovementRepository;
import com.transactionmgmt.operations.service.dto.mappers.MovementDtoMapper;
import com.transactionmgmt.operations.service.dto.movement.MovementDto;
import com.transactionmgmt.operations.service.dto.movement.UpdateMovementDto;
import com.transactionmgmt.operations.service.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        return movementDtoMapper.toDto(updated, updated.getCuenta().getNumeroCuenta());
    }

    @Override
    @Transactional(readOnly = true)
    public MovementDto getMovementById(Long id) {
        Movement movement = movementRepository.getMovementById(id)
                .orElseThrow(BusinessException.Type.MOVEMENT_NOT_EXISTS::build);
        return movementDtoMapper.toDto(movement, movement.getCuenta().getNumeroCuenta());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementDto> getAllMovements() {
        return movementRepository.getAllMovements().stream()
                .map(movement -> movementDtoMapper.toDto(movement, movement.getCuenta().getNumeroCuenta()))
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

    @Override
    public List<Movement> getMovementsByClientId(Long clientId, LocalDate startDate, LocalDate endDate) {
        return movementRepository.getMovementsByClient(clientId, startDate, endDate);
    }

}
