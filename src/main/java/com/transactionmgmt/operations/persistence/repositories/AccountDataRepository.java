package com.transactionmgmt.operations.persistence.repositories;

import com.transactionmgmt.operations.persistence.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDataRepository extends JpaRepository<AccountEntity, Long> {
    boolean existsByNumeroCuenta(long numeroCuenta);
}
    