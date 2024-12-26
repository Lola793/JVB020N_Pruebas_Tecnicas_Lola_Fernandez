package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column
    private String apellidos;

    @Column
    private String cargo;

    @Column(columnDefinition = "DECIMAL(10, 2)")
    private Float salario;

    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion;

    public Empleado() {
    }

    public Empleado(Integer id, String nombre, String apellidos, String cargo, Float salario, LocalDate fechaContratacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre='" + nombre + '\'' + ", apellidos='" + apellidos + '\'' + ", cargo='" + cargo + '\'' + ", salario=" + salario + ", fechaContratacion=" + fechaContratacion + '}';
    }
}


