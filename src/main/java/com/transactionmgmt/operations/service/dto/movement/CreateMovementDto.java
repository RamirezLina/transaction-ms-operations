package com.transactionmgmt.operations.service.dto.movement;

import jakarta.validation.constraints.NotNull;

public record CreateMovementDto(
    @NotNull Long cuentaId,
    @NotNull String tipoMovimiento,
    @NotNull Double valor
) {}
