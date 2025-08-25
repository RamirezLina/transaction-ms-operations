package com.transactionmgmt.operations.integration.rabbitmq;

import com.transactionmgmt.operations.integration.rabbitmq.command.CreateAccountCommand;
import com.transactionmgmt.operations.service.account.AccountService;
import com.transactionmgmt.operations.service.dto.account.CreateAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandListener {
    
    private final AccountService accountService;
    
    @RabbitListener(queues = "${app.queue}")
    public void onCreateAccount (CreateAccountCommand evt) {
        System.out.println("🟢 [ms-operations] - Generando cuenta para el cliente con Id" + evt.clienteId());
        CreateAccountDto dto = new CreateAccountDto(
                evt.numeroCuenta(), 
                evt.tipoCuenta(), 
                evt.saldoInicial(), 
                evt.clienteId());
        accountService.createAccount(dto);
        System.out.println("🟢 [ms-operations] - Cuenta creada");
        
        
    }
}
