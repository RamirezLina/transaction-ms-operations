package com.transactionmgmt.operations.persistence.adapters.account;

import com.transactionmgmt.operations.domain.account.Account;
import com.transactionmgmt.operations.persistence.entities.AccountEntity;
import com.transactionmgmt.operations.persistence.mappers.AccountEntityMapper;
import com.transactionmgmt.operations.persistence.repositories.AccountDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepository {
    private final AccountDataRepository accountDataRepository;
    private final AccountEntityMapper accountEntityMapper;

    @Override
    public void saveAccount(Account account) {
        accountDataRepository.save(accountEntityMapper.toEntity(account));
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountDataRepository.findById(id)
                .map(accountEntityMapper::toDomain);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDataRepository.findAll().stream()
                .map(accountEntityMapper::toDomain)
                .collect(Collectors.toList());
    }


    @Override
    public boolean existsByNumeroCuenta(long numeroCuenta) {
        return accountDataRepository.existsByNumeroCuenta(numeroCuenta);
    }
}
