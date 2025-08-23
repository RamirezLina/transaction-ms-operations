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
    public Account saveAccount(Account account) {
        AccountEntity entity = accountEntityMapper.toEntity(account);
        AccountEntity saved = accountDataRepository.save(entity);
        return accountEntityMapper.toDomain(saved);
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
    public void deleteAccount(Long id) {
        accountDataRepository.deleteById(id);
    }

    @Override
    public boolean existsByNumeroCuenta(String numeroCuenta) {
        return accountDataRepository.existsByNumeroCuenta(numeroCuenta);
    }
}
