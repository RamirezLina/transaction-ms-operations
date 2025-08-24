package com.transactionmgmt.operations.service.dto.movement;

import jakarta.validation.constraints.NotNull;

public record UpdateMovementDto(
    @NotNull String tipoMovimiento,
    @NotNull Boolean estado
) {}
