package org.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entities.Empleado;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoJPA {

    // Crea un nuevo empleado en la base de datos a través del EntityManager.
    // Persiste los datos del objeto Empleado y asegura la transacción.
    public static Empleado crearEmpleado(Empleado nuevoEmpleado) {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(nuevoEmpleado);
            em.getTransaction().commit();
            System.out.println("\nEmpleados añadidos correctamente con los siguientes datos:\n" + "Nombre: " + nuevoEmpleado.getNombre() + "\nApellidos: " + nuevoEmpleado.getApellidos() + "\nCargo: " + nuevoEmpleado.getCargo() + "\nSalario: " + nuevoEmpleado.getSalario() + "\nFecha de contratación: " + nuevoEmpleado.getFechaContratacion() + "\n");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
            // Cierra el EntityManager.
        }
        return nuevoEmpleado;
    }

    // Busca un empleado por nombre y apellidos en la base de datos.
    // Si se encuentra, retorna el empleado, si retorna null devuelve un mensaje de error
    public static Empleado buscarEmpleadoPorDatos(String nombreEmpleadoBuscado, String apellidosEmpleadoBuscado) {
        // Obtiene lista de empleados por cargo
        EntityManager em = ConfigJPA.getEntityManager();
        Empleado empleado = null;
        try {
            // Crea una consulta para buscar al empleado con los parámetros nombre y apellidos.
            TypedQuery<Empleado> query = em.createQuery("SELECT e FROM Empleado e WHERE e.nombre = :nombre " + "AND e.apellidos = :apellidos", Empleado.class);
            query.setParameter("nombre", nombreEmpleadoBuscado);
            query.setParameter("apellidos", apellidosEmpleadoBuscado);
            empleado = query.getSingleResult();
            // Obtiene un único resultado.
            System.out.println("\nEl empleado " + nombreEmpleadoBuscado + " " + apellidosEmpleadoBuscado + " se encuentra en la base de datos con los siguientes datos:\nSalario: " + empleado.getSalario() + "\nCargo: " + empleado.getCargo() + "\n" + "Fecha de contratación: " + empleado.getFechaContratacion() + "\n");
        } catch (jakarta.persistence.NoResultException e) {
            System.out.println("\nEl empleado " + nombreEmpleadoBuscado + " " + apellidosEmpleadoBuscado + " no se encuentra en la base de datos.\n");
        } finally {
            em.close();
            // Cierra el EntityManager.
        }
        // Verifica si los dos parametros "nombreEmpleadoBuscado" y "apellidosEmpleadoBuscado" no son nulos.
        // Si ambos no son nulos, se retorna el objeto "empleado"
        // Si alguno de los parámetros es nulo, retorna null, indicando que no se encuentra el empleado en la base de datos.
        if (nombreEmpleadoBuscado != null && apellidosEmpleadoBuscado != null) {
            return empleado;
        } else {
            return null;
        }
    }

    public static List<Empleado> buscarEmpleadosPorCargo(String cargoABuscar) {
        EntityManager em = ConfigJPA.getEntityManager();
        List<Empleado> empleados = new ArrayList<>();
        try {
            TypedQuery<Empleado> query = em.createQuery("SELECT e FROM Empleado e WHERE e.cargo = :cargo", Empleado.class);
            query.setParameter("cargo", cargoABuscar);
            empleados = query.getResultList();
            if (empleados.isEmpty()) {
                System.out.println("\nNo se encuentra ningún empleado con el cargo " + cargoABuscar + ".\n");
            } else {
                System.out.println("\nExisten los siguientes empleados con el cargo " + cargoABuscar + ":");
                for (Empleado empleado : empleados) {
                    System.out.println("Nombre: " + empleado.getNombre() + "\nApellidos: " + empleado.getApellidos() + "\nSalario: " + empleado.getSalario() + "\n" + "Fecha de contratación: " + empleado.getFechaContratacion() + "\n");
                }
            }
        } catch (jakarta.persistence.NoResultException e) {
            System.out.println("\nNo se encuentra ningún empleado con el cargo " + cargoABuscar + ".\n");
        } finally {
            em.close();
        }
        // Si el parámetro "cargoABuscar" no es nulo retorna la lista "empleados" con los empleados que coinciden con el cargo buscado.
        // Si es nulo retorna null e indica que no se encuentra ningún empleado con el cargo buscado
        if (cargoABuscar != null) {
            return empleados;
        } else {
            return null;
        }
    }

    // Elimina un empleado de la base de datos.
    // Confirma la eliminación si el empleado existe, o indica que no se encontró.
    public static Empleado eliminarEmpleado(Empleado empleadoAEliminar) {
        EntityManager em = ConfigJPA.getEntityManager();

        try {
            em.getTransaction().begin();
            Empleado empleadoEnBD = em.find(Empleado.class, empleadoAEliminar.getId());
            System.out.println("\nBuscando empleado " + empleadoAEliminar.getNombre() + " " + empleadoAEliminar.getApellidos());
            if (empleadoEnBD != null) {
                System.out.println("Empleado encontrado.");
                em.remove(empleadoEnBD);
                System.out.println("Empleado " + empleadoAEliminar.getNombre() + " " + empleadoAEliminar.getApellidos() + " eliminado correctamente.\n");
            } else {
                System.out.println("\nNo se encontró ningún empleado los datos proporcionados.\n");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("\nError al eliminar el empleado " + empleadoAEliminar.getNombre() + " " + empleadoAEliminar.getApellidos() + ":\n" + e.getMessage() + "\n");
            throw e;
        } finally {
            em.close();
        }
        return empleadoAEliminar;
    }

    // Actualiza los datos de un empleado en la base de datos.
    // Permite modificar su cargo o su salario.
    public static Empleado actualizarDatos(Empleado empleadoAActualizar) {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            Empleado empleadoEnBBDD = em.find(Empleado.class, empleadoAActualizar.getId());
            System.out.println("\nBuscando empleado " + empleadoAActualizar.getNombre() + " " + empleadoAActualizar.getApellidos() + "... ");
            if (empleadoEnBBDD != null) {
                System.out.println("Empleado encontrado.\n");
                // Menú para actualizar los datos del empleado (cargo o salario).
                int opcion;
                do {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Elija una de las siguientes opciones:\n" + "1.- Actualizar cargo\n" + "2.- Actualizar salario\n" + "3.- Volver al menu principal");
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    if (opcion == 1) {
                        System.out.println("Introduzca el nuevo cargo del empleado:");
                        String nuevoCargo = scanner.nextLine();
                        empleadoAActualizar.setCargo(nuevoCargo);
                        empleadoEnBBDD.setCargo(nuevoCargo);
                        System.out.println("Cargo actualizado correctamente.\n");
                        break;
                    } else if (opcion == 2) {
                        System.out.println("Introduzca el nuevo salario del empleado:");
                        float nuevoSalario = 0;
                        boolean salarioValido = false;
                        while (!salarioValido) {
                            try {
                                nuevoSalario = scanner.nextFloat();
                                salarioValido = true;
                            } catch (NumberFormatException e) {
                                System.out.println("*****El salario debe contener dos decimales*****\n " + "Introduzca el nuevo salario del empleado:");
                                scanner.nextLine();
                            }
                        }
                        empleadoAActualizar.setSalario(nuevoSalario);
                        empleadoEnBBDD.setSalario(nuevoSalario);
                        System.out.println("Salario actualizado correctamente.\n");
                        break;
                    } else if (opcion == 3) {
                        System.out.println("Volviendo al menú anterior...\n");
                        return empleadoAActualizar;
                    } else {
                        System.out.println("Debe seleccionar un numero de 1 al 3\n");
                        return empleadoAActualizar;
                    }
                } while (opcion != 3);
            } else {
                System.out.println("\nNo se encontró ningún empleado los datos proporcionados.\n");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                // Revierte los cambios en caso de error.
            }
            System.err.println("\nError al actualizar los datos de " + empleadoAActualizar.getNombre() + " " + empleadoAActualizar.getApellidos() + ":\n" + e.getMessage() + "\n");
            throw e;
        } finally {
            em.close();
        }
        return empleadoAActualizar;
    }

    // Obtiene una lista de todos los empleados en la base de datos.
    // Retorna una lista de objetos Empleado.
    public List<Empleado> obtenerListaEmpleados() {
        EntityManager em = ConfigJPA.getEntityManager();
        List<Empleado> empleados = null;
        try {
            // Consulta para obtener todos los empleados.
            TypedQuery<Empleado> query = em.createQuery("SELECT e FROM Empleado e", Empleado.class);
            empleados = query.getResultList();
            // Obtiene los resultados.
        } catch (Exception e) {
            System.err.println("\nError al obtener el listado de empleados: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
        return empleados;
    }
}



