package com.transactionmgmt.operations.persistence.repositories;

import com.transactionmgmt.operations.persistence.entities.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementDataRepository extends JpaRepository<MovementEntity, Long> {
}
