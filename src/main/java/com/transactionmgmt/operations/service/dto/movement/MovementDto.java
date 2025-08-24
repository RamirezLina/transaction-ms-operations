package com.transactionmgmt.operations.service.dto.movement;

import java.time.LocalDateTime;

public record MovementDto(
    long numeroCuenta,
    LocalDateTime fecha,
    String tipoMovimiento,
    Double valor,
    Double saldoInicial,
    Double saldoFinal,
    boolean estado
) {}