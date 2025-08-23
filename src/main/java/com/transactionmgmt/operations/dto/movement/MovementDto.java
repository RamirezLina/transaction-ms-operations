package com.transactionmgmt.operations.dto.movement;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MovementDto {
    private Long id;
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
}
