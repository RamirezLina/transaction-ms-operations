package com.transactionmgmt.operations.controller;

import com.transactionmgmt.operations.service.dto.movement.CreateMovementDto;
import com.transactionmgmt.operations.service.dto.movement.MovementDto;
import com.transactionmgmt.operations.service.dto.movement.UpdateMovementDto;
import com.transactionmgmt.operations.service.movement.MovementService;
import com.transactionmgmt.operations.service.movement.RegisterMovementService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovementControllerTest {
    @Mock
    private MovementService movementService;
    @Mock
    private RegisterMovementService registerMovementService;
    @InjectMocks
    private MovementController movementController;

  
    @Test
    @DisplayName("Register movement request returns data successfully")
    void registerMovement_returnsCreatedMovement() {
        CreateMovementDto dto = mock(CreateMovementDto.class);
        MovementDto movementDto = mock(MovementDto.class);
        
        when(registerMovementService.registerMovement(dto)).thenReturn(movementDto);

        ResponseEntity<MovementDto> response = movementController.registerMovement(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(movementDto, response.getBody());
        verify(registerMovementService, times(1)).registerMovement(dto);
    }

    @Test
    @DisplayName("Get all movements returns list successfully")
    void getAllMovements_returnsListSuccessfully() {
        MovementDto movementDto = mock(MovementDto.class);
        when(movementService.getAllMovements()).thenReturn(List.of(movementDto));

        ResponseEntity<List<MovementDto>> response = movementController.getAllMovements();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(movementService, times(1)).getAllMovements();
    }

    @Test
    @DisplayName("Get movement by id returns data successfully")
    void getMovementById_returnsDataSuccessfully() {
        Long id = 1L;
        MovementDto movementDto = mock(MovementDto.class);
        when(movementService.getMovementById(id)).thenReturn(movementDto);

        ResponseEntity<MovementDto> response = movementController.getMovementById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movementDto, response.getBody());
        verify(movementService, times(1)).getMovementById(id);
    }

    @Test
    @DisplayName("Update movement returns updated data successfully")
    void updateMovement_returnsUpdatedDataSuccessfully() {
        Long id = 1L;
        UpdateMovementDto dto = mock(UpdateMovementDto.class);
        MovementDto movementDto = mock(MovementDto.class);
        when(movementService.updateMovement(id, dto)).thenReturn(movementDto);

        ResponseEntity<MovementDto> response = movementController.updateMovement(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movementDto, response.getBody());
        verify(movementService, times(1)).updateMovement(id, dto);
    }

    @Test
    @DisplayName("Delete movement returns no content successfully")
    void deleteMovement_returnsNoContentSuccessfully() {
        Long id = 1L;
        doNothing().when(movementService).deleteMovement(id);

        ResponseEntity<Void> response = movementController.deleteMovement(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(movementService, times(1)).deleteMovement(id);
    }
}
