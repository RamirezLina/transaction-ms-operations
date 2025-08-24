package com.transactionmgmt.operations.controller;

import com.transactionmgmt.operations.service.dto.movement.MovementDto;
import com.transactionmgmt.operations.service.dto.movement.CreateMovementDto;
import com.transactionmgmt.operations.service.dto.movement.UpdateMovementDto;
import com.transactionmgmt.operations.service.movement.MovementService;
import com.transactionmgmt.operations.service.movement.RegisterMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovementController {
    private final MovementService movementService;
    private final RegisterMovementService registerMovementService;

    @PostMapping()
    public ResponseEntity<MovementDto> registerMovement(@RequestBody CreateMovementDto dto) {
        MovementDto movement = registerMovementService.registerMovement(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movement);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MovementDto> updateMovement(@PathVariable Long id, @RequestBody UpdateMovementDto dto) {
        return ResponseEntity.ok(movementService.updateMovement(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementDto> getMovementById(@PathVariable Long id) {
        return ResponseEntity.ok(movementService.getMovementById(id));
    }

    @GetMapping
    public ResponseEntity<List<MovementDto>> getAllMovements() {
        return ResponseEntity.ok(movementService.getAllMovements());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovement(@PathVariable Long id) {
        movementService.deleteMovement(id);
        return ResponseEntity.noContent().build();
    }
}
