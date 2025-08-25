package com.transactionmgmt.operations.controller;

import com.transactionmgmt.operations.service.dto.report.AccountReportDto;
import com.transactionmgmt.operations.service.report.ReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportControllerTest {
    @Mock
    private ReportService reportService;
    @InjectMocks
    private ReportController reportController;
    
    @Test
    void getReportByClient_returnsReportList() {
        Long clientId = 1L;
        LocalDate start = LocalDate.now().minusDays(10);
        LocalDate end = LocalDate.now();
        AccountReportDto dto = mock(AccountReportDto.class);
        when(reportService.getReportByClient(clientId, start, end)).thenReturn(List.of(dto));

        ResponseEntity<List<AccountReportDto>> response = reportController.getReportByClient(clientId, start, end);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(reportService).getReportByClient(clientId, start, end);
    }
}

