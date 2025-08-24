package com.transactionmgmt.operations.service.movement;

import com.transactionmgmt.operations.service.dto.movement.CreateMovementDto;
import com.transactionmgmt.operations.service.dto.movement.MovementDto;


public interface RegisterMovementService {
    MovementDto registerMovement(CreateMovementDto dto);

}
