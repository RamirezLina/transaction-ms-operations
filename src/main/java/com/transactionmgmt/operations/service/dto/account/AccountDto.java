package com.transactionmgmt.operations.service.dto.account;

public record AccountDto(
    Long id,
    long numeroCuenta,
    String tipoCuenta,
    Double saldoInicial,
    boolean estado
) {}
