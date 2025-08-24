package com.transactionmgmt.operations.domain.movement;

import com.transactionmgmt.operations.domain.account.Account;
import com.transactionmgmt.operations.persistence.entities.AccountEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Movement {
    private Long id;
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldoInicial;
    private boolean estado;
    private boolean activo;
    private Account cuenta;

     public void setDefaultValues() {
        this.estado = true;
        this.activo = true;
    }



    public void softDelete(){
        this.activo = false;
    }
}
