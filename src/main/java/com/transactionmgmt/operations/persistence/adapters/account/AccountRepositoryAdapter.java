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
        AccountEntity entity = accountDataRepository.save(accountEntityMapper.toEntity(account));
       return accountEntityMapper.toModel(entity);
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountDataRepository.findById(id)
                .filter(AccountEntity::isEstado)
                .map(accountEntityMapper::toModel);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDataRepository.findAll().stream()
                .filter(AccountEntity::isEstado)
                .map(accountEntityMapper::toModel)
                .collect(Collectors.toList());
    }


    @Override
    public boolean existsByNumeroCuenta(long numeroCuenta) {
        return accountDataRepository.existsByNumeroCuenta(numeroCuenta);
    }
}
