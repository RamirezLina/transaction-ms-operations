package com.transactionmgmt.operations.integration.restclient.users;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-users", url = "${client.users.url}")
public interface RestClientUsers {
    
    @GetMapping("/{clienteId}")
    ClientDto getClientById(@PathVariable("clienteId") Long clienteId);
}
