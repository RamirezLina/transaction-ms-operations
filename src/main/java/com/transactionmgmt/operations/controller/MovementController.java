package com.transactionmgmt.operations.controller;

import com.transactionmgmt.operations.dto.movement.MovementDto;
import com.transactionmgmt.operations.dto.movement.CreateMovementDto;
import com.transactionmgmt.operations.dto.movement.UpdateMovementDto;
import com.transactionmgmt.operations.service.movement.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovementController {
    private final MovementService movementService;

    @PostMapping
    public ResponseEntity<MovementDto> createMovement(@RequestBody CreateMovementDto dto) {
        return ResponseEntity.ok(movementService.createMovement(dto));
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
