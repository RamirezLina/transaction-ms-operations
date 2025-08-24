package com.transactionmgmt.operations.service.dto.account;

import com.transactionmgmt.operations.domain.account.AccountType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateAccountDto(
        @NotNull AccountType tipoCuenta,
        @NotNull @Positive Double saldoInicial

) {}
