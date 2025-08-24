package com.transactionmgmt.operations.service.account;

import com.transactionmgmt.operations.service.dto.account.AccountDto;
import com.transactionmgmt.operations.service.dto.account.CreateAccountDto;
import com.transactionmgmt.operations.service.dto.account.UpdateAccountDto;

import java.util.List;

public interface AccountService {
    void createAccount(CreateAccountDto dto);
    AccountDto updateAccount(Long id, UpdateAccountDto dto);
    AccountDto getAccountById(Long id);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
}
