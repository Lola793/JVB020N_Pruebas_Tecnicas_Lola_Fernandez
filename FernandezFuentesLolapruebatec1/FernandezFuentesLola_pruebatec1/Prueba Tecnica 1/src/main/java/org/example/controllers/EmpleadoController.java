package org.example.controllers;

import org.example.entities.Empleado;
import org.example.persistence.EmpleadoJPA;

import java.util.List;

public class EmpleadoController {

    // Crea un nuevo empleado en la base de datos a través de EmpleadoJPA
    // y devuelve los datos del empleado creado
    public static Empleado crearEmpleado(Empleado nuevoEmpleado) {
        return EmpleadoJPA.crearEmpleado(nuevoEmpleado);
    }

    // Busca un empleado en la base de datos utilizando nombre y apellidos.
    // Si se encuentra, devuelve los datos del empleado, si no existe, devuelve un null.
    public static Empleado buscarEmpleadoPorDatos(String nombreEmpleadoBuscado, String apellidosEmpleadoBuscado) {
        return EmpleadoJPA.buscarEmpleadoPorDatos(nombreEmpleadoBuscado, apellidosEmpleadoBuscado);
    }

    // Busca por cargo en la base de datos y obtiene una lista de todos los empleados que tienen ese cargo.
    // Recibe como parámetro de entrada el cargo a buscar.
    public static List<Empleado> buscarEmpleadosPorCargo(String cargoABuscar) {
        return EmpleadoJPA.buscarEmpleadosPorCargo(cargoABuscar);
    }

    // Busca un empleado en la base de datos utilizando nombre y apellidos, y lo elimina si se encuentra.
    // Si no se encuentra, muestra un mensaje por consola indicando el problema.
    // Retorna el empleado eliminado o null si no fue encontrado.
    public static Empleado empleadoAEliminar(String nombreEmpleado, String apellidosEmpleado) {
        Empleado empleadoAEliminar = buscarEmpleadoPorDatos(nombreEmpleado, apellidosEmpleado);
        if (empleadoAEliminar != null) {
            // Llama a EmpleadoJPA para eliminar al empleado encontrado.
            EmpleadoJPA.eliminarEmpleado(empleadoAEliminar);
        } else {
            // Si es null muestra un mensaje indicando que el empleado no fue encontrado.
            System.out.println("No se encontró al empleado: " + nombreEmpleado + " " + apellidosEmpleado + "");
        }
        return empleadoAEliminar;
    }

    // Busca un empleado en la base de datos utilizando su nombre y apellidos.
    // Actualiza los datos del empleado si fue encontrado.
    // Retorna el empleado que se ha actualizado o null si no se encuentra.
    public static Empleado empleadoAActualizar(String nombreEmpleado, String apellidosEmpleado) {
        Empleado empleadoAActualizar = buscarEmpleadoPorDatos(nombreEmpleado, apellidosEmpleado);
        if (empleadoAActualizar != null) {
            // Llama a EmpleadoJPA para actualizar los datos del empleado encontrado.
            EmpleadoJPA.actualizarDatos(empleadoAActualizar);
        } else {
            // Si es null muestra un mensaje indicando que el empleado no fue encontrado.
            System.out.println("No se encontró al empleado: " + nombreEmpleado + " " + apellidosEmpleado + "");
        }
        return empleadoAActualizar;
    }

    // Obtiene una lista de todos los empleados registrados en la base de datos.
    // Retorna una lista con los empleados encontrados.
    public static List<Empleado> obtenerListaDeEmpleados() {
        return new EmpleadoJPA().obtenerListaEmpleados();
    }
}