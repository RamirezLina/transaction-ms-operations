package com.transactionmgmt.operations.service.movement;

import com.transactionmgmt.operations.domain.movement.Movement;
import com.transactionmgmt.operations.service.dto.movement.MovementDto;
import com.transactionmgmt.operations.service.dto.movement.UpdateMovementDto;

import java.time.LocalDate;
import java.util.List;

public interface MovementService {
    MovementDto updateMovement(Long id, UpdateMovementDto dto);
    MovementDto getMovementById(Long id);
    List<MovementDto> getAllMovements();
    void deleteMovement(Long id);
    List<Movement> getMovementsByClientId(Long clientId, LocalDate startDate, LocalDate endDate);
}
