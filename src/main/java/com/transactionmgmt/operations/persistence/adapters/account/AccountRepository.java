package com.transactionmgmt.operations.persistence.adapters.account;

import com.transactionmgmt.operations.domain.account.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account saveAccount(Account account);
    Optional<Account> getAccountById(Long id);
    List<Account> getAllAccounts();
    boolean existsByNumeroCuenta(long numeroCuenta);
}
