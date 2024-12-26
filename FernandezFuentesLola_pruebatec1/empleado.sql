-- Crear la base de datos
CREATE DATABASE empleado_db;

-- Usar la base de datos
USE empleado_db;

-- Crear la tabla de empleados
CREATE TABLE empleado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    salario FLOAT (10, 2) NOT NULL,
    fecha_contratacion DATE NOT NULL
);

-- Insertar datos en la tabla
INSERT INTO empleado (nombre, apellidos, cargo, salario, fecha_contratacion)
VALUES
('Laura', 'Martínez Gómez', 'Analista de ADN', 30000.00, '2021-03-15'),
('Carlos', 'Pérez López', 'Investigador Principal', 45000.00, '2019-07-01'),
('Ana', 'García Fernández', 'Bioinformática', 35000.00, '2020-11-10'),
('David', 'Ruiz Sánchez', 'Técnico de Laboratorio', 28000.00, '2022-01-05'),
('María', 'Hernández Morales', 'Jefa de Proyecto', 50000.00, '2018-09-20');