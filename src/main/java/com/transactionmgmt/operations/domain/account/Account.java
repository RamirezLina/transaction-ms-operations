package com.transactionmgmt.operations.domain.account;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private long numeroCuenta;
    private AccountType tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private Long clienteId;

    public void setDefaultValues() {
        this.estado = true;
    }
    
    public void softDelete(){
        this.estado = false;
    }


}
