package com.transactionmgmt.operations.service.dto.account;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateAccountDto(
        @NotNull @Min(100000) Long numeroCuenta,
        @NotNull String tipoCuenta,
        @NotNull @Positive Double saldoInicial,
        @NotNull Long clienteId
) {}
