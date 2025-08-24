package com.transactionmgmt.operations.persistence.mappers;

import com.transactionmgmt.operations.domain.movement.Movement;
import com.transactionmgmt.operations.persistence.entities.MovementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovementEntityMapper {
    MovementEntityMapper INSTANCE = Mappers.getMapper(MovementEntityMapper.class);

    MovementEntity toEntity(Movement movement);
    Movement toDomain(MovementEntity entity);
}
