package com.transactionmgmt.operations.integration.restclient.users;


public record ClientDto(
        Long clientId,
        String name,
        String gender,
        int age,
        String identification,
        String address,
        String phoneNumber) {
}
