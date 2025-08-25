package com.transactionmgmt.operations.service.dto.report;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public record MovementReportDto(
        @JsonProperty("Fecha")
        LocalDate fecha,
        @JsonProperty("Saldo Inicial")
        Double saldoInicial,
        @JsonProperty("Movimiento")
        Double movimiento,
        @JsonProperty("Saldo Disponible")
        Double saldoFinal,
        @JsonProperty("Estado")
        boolean estado
) {
}
