-- Crear bases de datos
CREATE DATABASE IF NOT EXISTS `ms-users`;
CREATE DATABASE IF NOT EXISTS `ms-operations`;

-- Usar la base de datos ms-users
USE `ms-users`;

CREATE TABLE IF NOT EXISTS client (
    client_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    age INT NOT NULL,
    identification VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE
);

USE `ms-operations`;

-- Crear tabla cuenta
CREATE TABLE IF NOT EXISTS cuenta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta BIGINT NOT NULL UNIQUE,
    tipo_cuenta VARCHAR(50) NOT NULL,
    saldo_inicial DOUBLE NOT NULL,
    estado BOOLEAN NOT NULL,
    client_id BIGINT NOT NULL
);

-- Crear tabla movimiento
CREATE TABLE IF NOT EXISTS movimiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    tipo_movimiento VARCHAR(50) NOT NULL,
    valor DOUBLE NOT NULL,
    saldo_inicial DOUBLE NOT NULL,
    saldo_final DOUBLE NOT NULL,
    estado BOOLEAN NOT NULL,
    activo BOOLEAN NOT NULL,
    cuenta_id BIGINT NOT NULL,
    CONSTRAINT fk_movimiento_cuenta FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);
