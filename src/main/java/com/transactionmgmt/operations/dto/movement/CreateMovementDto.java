package com.transactionmgmt.operations.dto.movement;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateMovementDto {
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
}
