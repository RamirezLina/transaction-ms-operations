package com.transactionmgmt.operations.service.report;

import com.transactionmgmt.operations.domain.account.Account;
import com.transactionmgmt.operations.domain.movement.Movement;
import com.transactionmgmt.operations.service.dto.mappers.ReportDtoMapper;
import com.transactionmgmt.operations.service.dto.report.AccountReportDto;
import com.transactionmgmt.operations.service.dto.report.MovementReportDto;
import com.transactionmgmt.operations.service.movement.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final MovementService movementService;
    private final ReportDtoMapper reportDtoMapper;


    @Override
    public List<AccountReportDto> getReportByClient(Long clientId, LocalDate startDate, LocalDate endDate) {
        List<Movement> movementsToReport = movementService.getMovementsByClientId(clientId, startDate, endDate);
        String clientName = "DESCONOCIDO";
        return movementsToReport.stream()
                .collect(Collectors.groupingBy(movement -> movement.getCuenta().getId()))
                .values().stream()
                .map(movementsList -> {
                    Account account = movementsList.get(0).getCuenta();
                    List<MovementReportDto> movementReporList = movementsList.stream()
                            .map(movement -> new MovementReportDto(
                                    movement.getFecha(),
                                    movement.getSaldoInicial(),
                                    movement.getValor(),
                                    movement.getSaldoFinal(),
                                    movement.isEstado()
                            ))
                            .collect(Collectors.toList());
                    return reportDtoMapper.toDto(account, movementReporList, clientName);
                })
                .collect(Collectors.toList());
    }
}
