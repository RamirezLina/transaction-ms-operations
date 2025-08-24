package com.transactionmgmt.operations.service.dto.mappers;

import com.transactionmgmt.operations.domain.movement.Movement;
import com.transactionmgmt.operations.service.dto.movement.MovementDto;
import org.springframework.stereotype.Component;

@Component
public class MovementDtoMapper {
    public MovementDto toDto(Movement movement, long numeroCuenta) {
        if (movement == null) {
            return null;
        }
        return new MovementDto(
            movement.getId(),
            numeroCuenta,
            movement.getFecha(),
            movement.getTipoMovimiento() != null ? movement.getTipoMovimiento().name() : null,
            movement.getValor(),
            movement.getSaldoInicial(),
            movement.getSaldoFinal(),
            movement.isEstado()
        );
    }
}
