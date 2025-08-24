package com.transactionmgmt.operations.service.dto.mappers;

import com.transactionmgmt.operations.domain.movement.Movement;
import com.transactionmgmt.operations.service.dto.movement.MovementDto;
import com.transactionmgmt.operations.service.dto.movement.CreateMovementDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovementDtoMapper {
    MovementDto toDto(Movement movement);
    Movement toModel(CreateMovementDto dto);
}
