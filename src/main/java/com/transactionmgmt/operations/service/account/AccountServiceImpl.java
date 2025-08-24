package com.transactionmgmt.operations.service.account;

import com.transactionmgmt.operations.domain.account.Account;
import com.transactionmgmt.operations.persistence.adapters.account.AccountRepository;
import com.transactionmgmt.operations.service.dto.account.AccountDto;
import com.transactionmgmt.operations.service.dto.account.CreateAccountDto;
import com.transactionmgmt.operations.service.dto.account.UpdateAccountDto;
import com.transactionmgmt.operations.service.dto.mappers.AccountDtoMapper;
import com.transactionmgmt.operations.service.exception.BusinessException;
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
    public void createAccount(CreateAccountDto dto) {
        if (accountRepository.existsByNumeroCuenta(dto.numeroCuenta())) {
            throw BusinessException.Type.ACCOUNT_ALREADY_EXISTS.build();
        }
        Account account = accountDtoMapper.toModel(dto);
        account.setDefaultValues();
        accountRepository.saveAccount(account);
    }

    @Override
    @Transactional
    public AccountDto updateAccount(Long id, UpdateAccountDto dto) {
        Account account = accountRepository.getAccountById(id)
                .orElseThrow(BusinessException.Type.ACCOUNT_NOT_EXISTS::build);
        
        Account accountToUpdate = account.toBuilder()
                .tipoCuenta(dto.tipoCuenta())
                .saldoInicial(dto.saldoInicial()).build();
        accountRepository.saveAccount(accountToUpdate);
        return accountDtoMapper.toDto(accountToUpdate);
    }

    @Transactional(readOnly = true)
    public AccountDto getAccountDtoById(Long id) {
        Account account = accountRepository.getAccountById(id)
                .orElseThrow(BusinessException.Type.ACCOUNT_NOT_EXISTS::build);
        return accountDtoMapper.toDto(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccountById(Long id) {
        return accountRepository.getAccountById(id)
                .orElseThrow(BusinessException.Type.ACCOUNT_NOT_EXISTS::build);
        
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
        Account account = accountRepository.getAccountById(id)
                .orElseThrow(BusinessException.Type.ACCOUNT_NOT_EXISTS::build);
        account.softDelete();
        accountRepository.saveAccount(account);
    }
}
