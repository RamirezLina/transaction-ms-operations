package com.transactionmgmt.operations.dto.account;

import lombok.Data;

@Data
public class CreateAccountDto {
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private String estado;
}
