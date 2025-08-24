package com.transactionmgmt.operations.domain.account;

import lombok.*;
import java.security.SecureRandom;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Account {
    private Long id;
    private long numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private boolean estado;
    private Long clienteId;

    public Account() {
        this.estado = true;
        this.numeroCuenta = generateNumberAccount();
    }

    private long generateNumberAccount() {
        SecureRandom random = new SecureRandom();
        int numero = 100000 + random.nextInt(900000);
        return Long.parseLong(String.valueOf(numero));
    }
    
    public void softDelete(){
        this.estado = false;
    }


}
