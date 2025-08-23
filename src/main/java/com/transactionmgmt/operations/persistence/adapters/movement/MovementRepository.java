package com.transactionmgmt.operations.persistence.adapters.movement;

import com.transactionmgmt.operations.domain.movement.Movement;

import java.util.List;
import java.util.Optional;

public interface MovementRepository {
    Movement saveMovement(Movement movement);
    Optional<Movement> getMovementById(Long id);
    List<Movement> getAllMovements();
    void deleteMovement(Long id);
}
