package com.transactionmgmt.operations.persistence.repositories;

import com.transactionmgmt.operations.persistence.entities.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovementDataRepository extends JpaRepository<MovementEntity, Long> {

    List<MovementEntity> findAllByCuentaClienteIdAndFechaBetween(Long clienteId, LocalDate startDate, LocalDate endDate);
}
