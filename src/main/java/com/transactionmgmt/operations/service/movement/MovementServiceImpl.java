package com.transactionmgmt.operations.service.movement;

import com.transactionmgmt.operations.domain.movement.Movement;
import com.transactionmgmt.operations.persistence.adapters.movement.MovementRepository;
import com.transactionmgmt.operations.dto.mappers.MovementDtoMapper;
import com.transactionmgmt.operations.dto.movement.CreateMovementDto;
import com.transactionmgmt.operations.dto.movement.MovementDto;
import com.transactionmgmt.operations.dto.movement.UpdateMovementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {
    private final MovementRepository movementRepository;
    private final MovementDtoMapper movementDtoMapper;

    @Override
    @Transactional
    public MovementDto createMovement(CreateMovementDto dto) {
        Movement movement = movementDtoMapper.toDomain(dto);
        Movement saved = movementRepository.saveMovement(movement);
        return movementDtoMapper.toDto(saved);
    }

    @Override
    @Transactional
    public MovementDto updateMovement(Long id, UpdateMovementDto dto) {
        Movement movement = movementRepository.getMovementById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movimiento no encontrado"));
        movementDtoMapper.updateDomainFromDto(dto, movement);
        Movement updated = movementRepository.saveMovement(movement);
        return movementDtoMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public MovementDto getMovementById(Long id) {
        Movement movement = movementRepository.getMovementById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movimiento no encontrado"));
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
        if (!movementRepository.getMovementById(id).isPresent()) {
            throw new IllegalArgumentException("Movimiento no encontrado");
        }
        movementRepository.deleteMovement(id);
    }
}
