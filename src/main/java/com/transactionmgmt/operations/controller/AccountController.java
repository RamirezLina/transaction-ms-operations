package com.transactionmgmt.operations.controller;

import com.transactionmgmt.operations.service.dto.account.AccountDto;
import com.transactionmgmt.operations.service.dto.account.CreateAccountDto;
import com.transactionmgmt.operations.service.dto.account.UpdateAccountDto;
import com.transactionmgmt.operations.service.account.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class AccountController {
    
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Void> createAccount(@Valid @RequestBody CreateAccountDto dto) {
        accountService.createAccount(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @Valid @RequestBody UpdateAccountDto dto) {
        return ResponseEntity.ok(accountService.updateAccount(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
