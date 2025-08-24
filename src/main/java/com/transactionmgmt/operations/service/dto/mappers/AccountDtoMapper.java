package com.transactionmgmt.operations.service.dto.mappers;

import com.transactionmgmt.operations.domain.account.Account;
import com.transactionmgmt.operations.service.dto.account.AccountDto;
import com.transactionmgmt.operations.service.dto.account.CreateAccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountDtoMapper {

    AccountDto toDto(Account account);
    Account toModel(CreateAccountDto dto);
}
