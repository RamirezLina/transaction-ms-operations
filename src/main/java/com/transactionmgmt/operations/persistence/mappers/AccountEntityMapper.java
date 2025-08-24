package com.transactionmgmt.operations.persistence.mappers;

import com.transactionmgmt.operations.domain.account.Account;
import com.transactionmgmt.operations.persistence.entities.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountEntityMapper {
    AccountEntityMapper INSTANCE = Mappers.getMapper(AccountEntityMapper.class);

    AccountEntity toEntity(Account account);
    Account toModel(AccountEntity entity);
}
