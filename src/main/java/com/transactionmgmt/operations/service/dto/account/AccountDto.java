package com.transactionmgmt.operations.service.dto.account;

import com.transactionmgmt.operations.domain.account.AccountType;

public record AccountDto(
    Long id,
    long numeroCuenta,
    AccountType tipoCuenta,
    long clienteId,
    Double saldoInicial,
    boolean estado
) {}
