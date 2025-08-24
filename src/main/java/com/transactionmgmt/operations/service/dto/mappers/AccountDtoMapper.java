package com.transactionmgmt.operations.service.dto.mappers;

import com.transactionmgmt.operations.domain.account.Account;
import com.transactionmgmt.operations.service.dto.account.AccountDto;
import com.transactionmgmt.operations.service.dto.account.CreateAccountDto;
import com.transactionmgmt.operations.service.dto.account.UpdateAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountDtoMapper {
    AccountDtoMapper INSTANCE = Mappers.getMapper(AccountDtoMapper.class);

    AccountDto toDto(Account account);
    Account toModel(AccountDto dto);
    Account toModel(CreateAccountDto dto);
}
