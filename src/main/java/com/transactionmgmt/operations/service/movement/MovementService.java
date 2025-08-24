package com.transactionmgmt.operations.service.movement;

import com.transactionmgmt.operations.service.dto.movement.MovementDto;
import com.transactionmgmt.operations.service.dto.movement.CreateMovementDto;
import com.transactionmgmt.operations.service.dto.movement.UpdateMovementDto;

import java.util.List;

public interface MovementService {
    MovementDto updateMovement(Long id, UpdateMovementDto dto);
    MovementDto getMovementById(Long id);
    List<MovementDto> getAllMovements();
    void deleteMovement(Long id);
}
