package com.transactionmgmt.operations.service.report;

import com.transactionmgmt.operations.service.dto.report.AccountReportDto;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
   
    List<AccountReportDto> getReportByClient(Long clientId, LocalDate startDate, LocalDate endDate);
 
}
