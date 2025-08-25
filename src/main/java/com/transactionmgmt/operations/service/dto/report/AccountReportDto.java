package com.transactionmgmt.operations.service.dto.report;

import com.transactionmgmt.operations.domain.account.AccountType;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountReportDto(
        @JsonProperty("Cliente")
        String nombreCliente,
        @JsonProperty("Numero Cuenta")
        long numeroCuenta,
        @JsonProperty("Tipo Cuenta")
        AccountType tipoCuenta,
        @JsonProperty("Saldo Actual")
        Double saldoActual,
        List<MovementReportDto> movimientosCuenta
) {
}
