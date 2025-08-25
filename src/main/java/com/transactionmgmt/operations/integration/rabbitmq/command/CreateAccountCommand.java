package com.transactionmgmt.operations.integration.rabbitmq.command;

import com.transactionmgmt.operations.domain.account.AccountType;

public record CreateAccountCommand(
        Long numeroCuenta,
        AccountType tipoCuenta,
        Double saldoInicial,
        Long clienteId
) {}
