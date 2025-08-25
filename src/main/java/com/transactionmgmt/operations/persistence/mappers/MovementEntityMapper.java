package com.transactionmgmt.operations.persistence.mappers;

import com.transactionmgmt.operations.domain.movement.Movement;
import com.transactionmgmt.operations.persistence.entities.MovementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovementEntityMapper {

    MovementEntity toEntity(Movement movement);
    Movement toModel(MovementEntity entity);
}
