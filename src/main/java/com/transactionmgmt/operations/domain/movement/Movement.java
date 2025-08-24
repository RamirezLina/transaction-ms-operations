package com.transactionmgmt.operations.domain.movement;

import com.transactionmgmt.operations.domain.account.Account;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Movement {
    private Long id;
    private LocalDate fecha;
    private MovementType tipoMovimiento;
    private Double valor;
    private Double saldoInicial;
    private Double saldoFinal;
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
