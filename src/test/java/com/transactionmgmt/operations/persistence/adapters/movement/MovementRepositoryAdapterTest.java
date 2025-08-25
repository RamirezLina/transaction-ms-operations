package com.transactionmgmt.operations.persistence.adapters.movement;

import com.transactionmgmt.operations.domain.movement.Movement;
import com.transactionmgmt.operations.persistence.entities.MovementEntity;
import com.transactionmgmt.operations.persistence.mappers.MovementEntityMapper;
import com.transactionmgmt.operations.persistence.repositories.MovementDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovementRepositoryAdapterTest {
    @Mock
    private MovementDataRepository movementDataRepository;
    @Mock
    private MovementEntityMapper movementEntityMapper;
    @InjectMocks
    private MovementRepositoryAdapter movementRepositoryAdapter;

    @Test
    void saveMovement_returnsMappedModel() {
        Movement movement = mock(Movement.class);
        MovementEntity entity = mock(MovementEntity.class);
        MovementEntity savedEntity = mock(MovementEntity.class);
        Movement mappedModel = mock(Movement.class);
        
        when(movementEntityMapper.toEntity(movement)).thenReturn(entity);
        when(movementDataRepository.save(entity)).thenReturn(savedEntity);
        when(movementEntityMapper.toModel(savedEntity)).thenReturn(mappedModel);
        
        Movement result = movementRepositoryAdapter.saveMovement(movement);
        
        assertEquals(mappedModel, result);
        verify(movementEntityMapper).toEntity(movement);
        verify(movementDataRepository).save(entity);
        verify(movementEntityMapper).toModel(savedEntity);
    }

    @Test
    void getMovementById_returnsOptionalModelIfActive() {
        Long id = 1L;
        MovementEntity entity = mock(MovementEntity.class);
        
        when(entity.isActivo()).thenReturn(true);
        when(movementDataRepository.findById(id)).thenReturn(Optional.of(entity));
        Movement model = mock(Movement.class);
        when(movementEntityMapper.toModel(entity)).thenReturn(model);
        
        Optional<Movement> result = movementRepositoryAdapter.getMovementById(id);
        
        assertTrue(result.isPresent());
        assertEquals(model, result.get());
        verify(movementDataRepository).findById(id);
        verify(movementEntityMapper).toModel(entity);
    }

    @Test
    void getMovementById_returnsEmptyIfNotActive() {
        Long id = 2L;
        MovementEntity entity = mock(MovementEntity.class);
        when(entity.isActivo()).thenReturn(false);
        when(movementDataRepository.findById(id)).thenReturn(Optional.of(entity));
        
        Optional<Movement> result = movementRepositoryAdapter.getMovementById(id);
        
        assertTrue(result.isEmpty());
        verify(movementDataRepository).findById(id);
    }

    @Test
    void getAllMovements_returnsMappedActiveModels() {
        MovementEntity entity1 = mock(MovementEntity.class);
        MovementEntity entity2 = mock(MovementEntity.class);
        
        when(entity1.isActivo()).thenReturn(true);
        when(entity2.isActivo()).thenReturn(false);
        when(movementDataRepository.findAll()).thenReturn(List.of(entity1, entity2));
        Movement model1 = mock(Movement.class);
        when(movementEntityMapper.toModel(entity1)).thenReturn(model1);
        
        List<Movement> result = movementRepositoryAdapter.getAllMovements();
        
        assertEquals(1, result.size());
        assertEquals(model1, result.get(0));
        verify(movementDataRepository).findAll();
        verify(movementEntityMapper).toModel(entity1);
    }

    @Test
    void getMovementsByClient_returnsMappedActiveModels() {
        Long clientId = 1L;
        LocalDate start = LocalDate.now().minusDays(5);
        LocalDate end = LocalDate.now();
        MovementEntity entity1 = mock(MovementEntity.class);
        MovementEntity entity2 = mock(MovementEntity.class);
        
        when(entity1.isActivo()).thenReturn(true);
        when(entity2.isActivo()).thenReturn(false);
        when(movementDataRepository.findAllByCuentaClienteIdAndFechaBetween(clientId, start, end)).thenReturn(List.of(entity1, entity2));
        Movement model1 = mock(Movement.class);
        when(movementEntityMapper.toModel(entity1)).thenReturn(model1);
        
        List<Movement> result = movementRepositoryAdapter.getMovementsByClient(clientId, start, end);
        
        assertEquals(1, result.size());
        assertEquals(model1, result.get(0));
        verify(movementDataRepository).findAllByCuentaClienteIdAndFechaBetween(clientId, start, end);
        verify(movementEntityMapper).toModel(entity1);
    }
}

