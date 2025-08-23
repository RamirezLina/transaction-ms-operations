package com.transactionmgmt.operations.service.account;

import com.transactionmgmt.operations.dto.account.AccountDto;
import com.transactionmgmt.operations.dto.account.CreateAccountDto;
import com.transactionmgmt.operations.dto.account.UpdateAccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(CreateAccountDto dto);
    AccountDto updateAccount(Long id, UpdateAccountDto dto);
    AccountDto getAccountById(Long id);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
}
