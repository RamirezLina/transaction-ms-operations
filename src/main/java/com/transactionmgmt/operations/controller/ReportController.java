package com.transactionmgmt.operations.controller;

import com.transactionmgmt.operations.service.dto.report.AccountReportDto;
import com.transactionmgmt.operations.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReportController {
    
    private final ReportService reportService;

    @GetMapping("/{clientId}")
    public ResponseEntity<List<AccountReportDto>> getReportByClient(@PathVariable Long clientId,
                                                                    @RequestParam LocalDate fechaInicio,
                                                                    @RequestParam LocalDate fechaFin) {
        return ResponseEntity.ok(reportService.getReportByClient(clientId, fechaInicio, fechaFin));
    }
    
}
