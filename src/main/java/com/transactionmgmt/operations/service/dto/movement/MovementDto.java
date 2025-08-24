package com.transactionmgmt.operations.service.dto.movement;

import java.time.LocalDate;

public record MovementDto(
        Long id,
        long numeroCuenta,
        LocalDate fecha,
        String tipoMovimiento,
        Double valor,
        Double saldoInicial,
        Double saldoFinal,
        boolean estado
) {
}