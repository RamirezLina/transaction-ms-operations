package com.transactionmgmt.operations.service.dto.movement;

import com.transactionmgmt.operations.domain.movement.MovementType;
import jakarta.validation.constraints.NotNull;

public record CreateMovementDto(
    @NotNull Long cuentaId,
    @NotNull MovementType tipoMovimiento,
    @NotNull Double valor
) {}
