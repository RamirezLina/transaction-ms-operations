package com.transactionmgmt.operations.persistence.adapters.account;

import com.transactionmgmt.operations.domain.account.Account;
import com.transactionmgmt.operations.persistence.entities.AccountEntity;
import com.transactionmgmt.operations.persistence.mappers.AccountEntityMapper;
import com.transactionmgmt.operations.persistence.repositories.AccountDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountRepositoryAdapterTest {
    @Mock
    private AccountDataRepository accountDataRepository;
    @Mock
    private AccountEntityMapper accountEntityMapper;
    @InjectMocks
    private AccountRepositoryAdapter accountRepositoryAdapter;

    @Test
    void saveAccount_returnsMappedModel() {
        Account account = mock(Account.class);
        AccountEntity entity = mock(AccountEntity.class);
        AccountEntity savedEntity = mock(AccountEntity.class);
        Account mappedModel = mock(Account.class);
        
        when(accountEntityMapper.toEntity(account)).thenReturn(entity);
        when(accountDataRepository.save(entity)).thenReturn(savedEntity);
        when(accountEntityMapper.toModel(savedEntity)).thenReturn(mappedModel);
        
        Account result = accountRepositoryAdapter.saveAccount(account);
        
        assertEquals(mappedModel, result);
        verify(accountEntityMapper).toEntity(account);
        verify(accountDataRepository).save(entity);
        verify(accountEntityMapper).toModel(savedEntity);
    }

    @Test
    void getAccountById_returnsOptionalModelIfActive() {
        Long id = 1L;
        AccountEntity entity = mock(AccountEntity.class);
        
        when(entity.isEstado()).thenReturn(true);
        when(accountDataRepository.findById(id)).thenReturn(Optional.of(entity));
        Account model = mock(Account.class);
        when(accountEntityMapper.toModel(entity)).thenReturn(model);
        
        Optional<Account> result = accountRepositoryAdapter.getAccountById(id);
        
        assertTrue(result.isPresent());
        assertEquals(model, result.get());
        verify(accountDataRepository).findById(id);
        verify(accountEntityMapper).toModel(entity);
    }

    @Test
    void getAccountById_returnsEmptyIfNotActive() {
        Long id = 2L;
        AccountEntity entity = mock(AccountEntity.class);
        when(entity.isEstado()).thenReturn(false);
        when(accountDataRepository.findById(id)).thenReturn(Optional.of(entity));
        
        Optional<Account> result = accountRepositoryAdapter.getAccountById(id);
        
        assertTrue(result.isEmpty());
        verify(accountDataRepository).findById(id);
    }

    @Test
    void getAllAccounts_returnsMappedActiveModels() {
        AccountEntity entity1 = mock(AccountEntity.class);
        AccountEntity entity2 = mock(AccountEntity.class);
        when(entity1.isEstado()).thenReturn(true);
        when(entity2.isEstado()).thenReturn(false);
        when(accountDataRepository.findAll()).thenReturn(List.of(entity1, entity2));
        Account model1 = mock(Account.class);
        when(accountEntityMapper.toModel(entity1)).thenReturn(model1);
        
        List<Account> result = accountRepositoryAdapter.getAllAccounts();
        
        assertEquals(1, result.size());
        assertEquals(model1, result.get(0));
        verify(accountDataRepository).findAll();
        verify(accountEntityMapper).toModel(entity1);
    }

    @Test
    void existsByNumeroCuenta_returnsCorrectValue() {
        long numeroCuenta = 12345L;
        when(accountDataRepository.existsByNumeroCuenta(numeroCuenta)).thenReturn(true);
        
        boolean exists = accountRepositoryAdapter.existsByNumeroCuenta(numeroCuenta);
        
        assertTrue(exists);
        verify(accountDataRepository).existsByNumeroCuenta(numeroCuenta);
    }
}

