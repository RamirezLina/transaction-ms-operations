package com.transactionmgmt.operations.service.account;

import com.transactionmgmt.operations.domain.account.Account;
import com.transactionmgmt.operations.persistence.adapters.account.AccountRepository;
import com.transactionmgmt.operations.dto.account.AccountDto;
import com.transactionmgmt.operations.dto.account.CreateAccountDto;
import com.transactionmgmt.operations.dto.account.UpdateAccountDto;
import com.transactionmgmt.operations.dto.mappers.AccountDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountDtoMapper accountDtoMapper;

    @Override
    @Transactional
    public AccountDto createAccount(CreateAccountDto dto) {
        if (accountRepository.existsByNumeroCuenta(dto.getNumeroCuenta())) {
            throw new IllegalArgumentException("El número de cuenta ya existe");
        }
        Account account = accountDtoMapper.toDomain(dto);
        Account saved = accountRepository.saveAccount(account);
        return accountDtoMapper.toDto(saved);
    }

    @Override
    @Transactional
    public AccountDto updateAccount(Long id, UpdateAccountDto dto) {
        Account account = accountRepository.getAccountById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        accountDtoMapper.updateDomainFromDto(dto, account);
        Account updated = accountRepository.saveAccount(account);
        return accountDtoMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.getAccountById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        return accountDtoMapper.toDto(account);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDto> getAllAccounts() {
        return accountRepository.getAllAccounts().stream()
                .map(accountDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteAccount(Long id) {
        if (!accountRepository.getAccountById(id).isPresent()) {
            throw new IllegalArgumentException("Cuenta no encontrada");
        }
        accountRepository.deleteAccount(id);
    }
}
