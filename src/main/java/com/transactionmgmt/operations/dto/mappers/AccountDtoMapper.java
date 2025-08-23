package com.transactionmgmt.operations.dto.mappers;

import com.transactionmgmt.operations.domain.account.Account;
import com.transactionmgmt.operations.dto.account.AccountDto;
import com.transactionmgmt.operations.dto.account.CreateAccountDto;
import com.transactionmgmt.operations.dto.account.UpdateAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountDtoMapper {
    AccountDtoMapper INSTANCE = Mappers.getMapper(AccountDtoMapper.class);

    AccountDto toDto(Account account);
    Account toDomain(AccountDto dto);
    Account toDomain(CreateAccountDto dto);
    void updateDomainFromDto(UpdateAccountDto dto, @org.mapstruct.MappingTarget Account account);
}
