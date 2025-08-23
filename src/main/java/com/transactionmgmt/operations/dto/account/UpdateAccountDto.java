package com.transactionmgmt.operations.dto.account;

import lombok.Data;

@Data
public class UpdateAccountDto {
    private String tipoCuenta;
    private Double saldoInicial;
    private String estado;
}
