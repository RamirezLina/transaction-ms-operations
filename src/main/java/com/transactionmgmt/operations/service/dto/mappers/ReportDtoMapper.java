package com.transactionmgmt.operations.service.dto.mappers;

import com.transactionmgmt.operations.domain.account.Account;
import com.transactionmgmt.operations.domain.movement.Movement;
import com.transactionmgmt.operations.service.dto.movement.MovementDto;
import com.transactionmgmt.operations.service.dto.report.AccountReportDto;
import com.transactionmgmt.operations.service.dto.report.MovementReportDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReportDtoMapper {
    public AccountReportDto toDto(Account account, List<MovementReportDto> movementReportDtoList, String clientName) {
        if (account == null) {
            return null;
        }
        
        return new AccountReportDto(
                clientName,
                account.getNumeroCuenta(),
                account.getTipoCuenta(),
                account.getSaldoInicial(),
                movementReportDtoList               
            
        );
    }
    
}
