package com.transactionmgmt.operations.service.dto.movement;

import com.transactionmgmt.operations.domain.movement.MovementType;
import jakarta.validation.constraints.NotNull;

public record UpdateMovementDto(
    @NotNull MovementType tipoMovimiento,
    @NotNull Boolean estado
) {}
