package org.example.utilies;

import org.example.entities.Empleado;

import java.time.LocalDate;

public class EmpleadoValidacionDatos {

    // Metodo para validar el nombre
    public static void validarNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
        }
        if (nombre.length() > 50) {
            throw new IllegalArgumentException("El nombre no puede exceder 50 caracteres.");
        }
    }

    // Metodo para validar los apellidos
    public static void validarApellidos(String apellidos) {
        if (apellidos == null || apellidos.isEmpty()) {
            throw new IllegalArgumentException("Los apellidos son obligatorios.");
        }
        if (!apellidos.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new IllegalArgumentException("Los apellidos solo pueden contener letras y espacios.");
        }
        if (apellidos.length() > 100) {
            throw new IllegalArgumentException("Los apellidos no pueden exceder 100 caracteres.");
        }
    }

    // Metodo para validar el cargo
    public static void validarCargo(String cargo) {
        if (cargo == null || cargo.isEmpty()) {
            throw new IllegalArgumentException("El cargo es obligatorio.");
        }
        if (!cargo.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new IllegalArgumentException("El cargo solo puede contener letras y espacios.");
        }
        if (cargo.length() > 50) {
            throw new IllegalArgumentException("El cargo no puede exceder 50 caracteres.");
        }
    }

    // Metodo para validar el salario
    public static void validarSalario(Float salario) {
        if (salario == null || salario <= 0) {
            throw new IllegalArgumentException("El salario debe ser un número positivo.");
        }
        if (String.valueOf(salario).contains(".") && String.valueOf(salario).split("\\.")[1].length() > 2) {
            throw new IllegalArgumentException("El salario solo puede tener hasta 2 decimales.");
        }
    }

    // Metodo para validar la fecha de contratación
    public static void validarFechaContratacion(LocalDate fechaContratacion) {
        if (fechaContratacion == null) {
            throw new IllegalArgumentException("La fecha de contratación es obligatoria.");
        }
        if (fechaContratacion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de contratación no puede ser superior a la fecha actual.");
        }
    }

    // Metodo para validar todos los datos del empleado
    public static void validarEmpleadoCompleto(Empleado empleado) {
        validarNombre(empleado.getNombre());
        validarApellidos(empleado.getApellidos());
        validarCargo(empleado.getCargo());
        validarSalario(empleado.getSalario());
        validarFechaContratacion(empleado.getFechaContratacion());
    }
}