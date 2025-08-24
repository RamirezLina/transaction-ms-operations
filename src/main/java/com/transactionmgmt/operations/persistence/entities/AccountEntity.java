package com.transactionmgmt.operations.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cuenta")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_cuenta", nullable = false, unique = true)
    private long numeroCuenta;

    @Column(name = "tipo_cuenta", nullable = false)
    private String tipoCuenta;

    @Column(name = "saldo_inicial", nullable = false)
    private Double saldoInicial;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "client_id", nullable = false)
    private Long clienteId;

    
}
