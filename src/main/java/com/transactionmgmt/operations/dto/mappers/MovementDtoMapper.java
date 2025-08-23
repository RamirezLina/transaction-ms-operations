package com.transactionmgmt.operations.dto.mappers;

import com.transactionmgmt.operations.domain.movement.Movement;
import com.transactionmgmt.operations.dto.movement.MovementDto;
import com.transactionmgmt.operations.dto.movement.CreateMovementDto;
import com.transactionmgmt.operations.dto.movement.UpdateMovementDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovementDtoMapper {
    MovementDtoMapper INSTANCE = Mappers.getMapper(MovementDtoMapper.class);

    MovementDto toDto(Movement movement);
    Movement toDomain(MovementDto dto);
    Movement toDomain(CreateMovementDto dto);
    void updateDomainFromDto(UpdateMovementDto dto, @org.mapstruct.MappingTarget Movement movement);
}
