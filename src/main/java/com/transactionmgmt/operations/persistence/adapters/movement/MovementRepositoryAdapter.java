package com.transactionmgmt.operations.persistence.adapters.movement;

import com.transactionmgmt.operations.domain.movement.Movement;
import com.transactionmgmt.operations.persistence.entities.MovementEntity;
import com.transactionmgmt.operations.persistence.mappers.MovementEntityMapper;
import com.transactionmgmt.operations.persistence.repositories.MovementDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovementRepositoryAdapter implements MovementRepository {
    private final MovementDataRepository movementDataRepository;
    private final MovementEntityMapper movementEntityMapper;

    @Override
    public Movement saveMovement(Movement movement) {
        MovementEntity entity = movementEntityMapper.toEntity(movement);
        MovementEntity saved = movementDataRepository.save(entity);
        return movementEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Movement> getMovementById(Long id) {
        return movementDataRepository.findById(id)
                .filter(MovementEntity::isActivo)
                .map(movementEntityMapper::toDomain);
    }

    @Override
    public List<Movement> getAllMovements() {
        return movementDataRepository.findAll().stream()
                .filter(MovementEntity::isActivo)
                .map(movementEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
    
}
