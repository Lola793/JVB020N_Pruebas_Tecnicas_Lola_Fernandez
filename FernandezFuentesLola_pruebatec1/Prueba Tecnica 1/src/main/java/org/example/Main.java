import org.example.controllers.EmpleadoController;
import org.example.entities.Empleado;
import org.example.utilies.EmpleadoValidacionDatos;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public static void main(String[] args) {

    // Se crea un objeto Scanner para obtener la entrada del usuario desde la consola
    Scanner scanner = new Scanner(System.in);
    int opcion = 0;

    // Bucle principal para mostrar el menú y procesar opciones hasta que el usuario decida salir
    do {
        // Muestra el menú principal con las opciones disponibles
        System.out.println("\nElija una de las siguientes opciones:" +
                "\n1.- Crear empleado\n" +
                "2.- Buscar empleado\n" +
                "3.- Eliminar empleado\n" +
                "4.- Actualizar empleado\n" +
                "5.- Obtener listado actual de los empleados\n" +
                "6.- Cerrar sesión");
        // Limpia el buffer del Scanner
        opcion = scanner.nextInt();
        scanner.nextLine();
        // Procesa la opción seleccionada por el usuario
        if (opcion == 1) {
            // Solicita los datos necesarios de entrada para la opción elegida
            System.out.println("Introduzca el nombre del empleado:");
            String nombreEmpleado = scanner.nextLine();
            System.out.println("Introduzca los apellidos del empleado:");
            String apellidosEmpleado = scanner.nextLine();
            System.out.println("Introduzca el cargo del empleado:");
            String cargoEmpleado = scanner.nextLine();
            System.out.println("Introduzca el salario anual del empleado:");
            float salarioEmpleado = scanner.nextFloat();
            System.out.println("Año de contratación del empleado YYYY:");
            int agnoContratacionEmpleado = scanner.nextInt();
            System.out.println("Mes de contratación del empleado:");
            int mesContratacionEmpleado = scanner.nextInt();
            System.out.println("Día de contratación del empleado:");
            int diaContratacionEmpleado = scanner.nextInt();
            // Se crea un nuevo objeto empleado con los datos introducidos y se envía al controlador
            Empleado nuevoEmpleado = new Empleado(null, nombreEmpleado, apellidosEmpleado,
                    cargoEmpleado, salarioEmpleado, LocalDate.of(agnoContratacionEmpleado,
                    mesContratacionEmpleado, diaContratacionEmpleado));
            try {
                EmpleadoValidacionDatos.validarEmpleadoCompleto(nuevoEmpleado);
                System.out.println("El empleado es válido.");
                EmpleadoController.crearEmpleado(nuevoEmpleado);
            } catch (IllegalArgumentException e) {
                System.err.println("Error en los datos: " + e.getMessage());
            }
        } else if (opcion == 2) {
            System.out.println("Elija una de las siguientes opciones:" +
                    "\n1.- Buscar por nombre y apellidos\n" +
                    "2.- Buscar por cargo\n" +
                    "3.- Volver al menú principal");
            // Limpia el buffer del Scanner
            opcion = scanner.nextInt();
            scanner.nextLine();
            // Sub-opción 1: Buscar empleado por nombre y apellidos
            if (opcion == 1) {
                // Solicita los datos necesarios de entrada para la opción elegida
                System.out.println("Introduzca el nombre del empleado:");
                String nombreEmpleadoBuscado = scanner.nextLine().toLowerCase();
                System.out.println("Introduzca los apellidos del empleado:");
                String apellidosEmpleadoBuscado = scanner.nextLine().toLowerCase();
                try {
                    // Valida los datos antes de buscar
                    EmpleadoValidacionDatos.validarNombre(nombreEmpleadoBuscado);
                    EmpleadoValidacionDatos.validarApellidos(apellidosEmpleadoBuscado);
                    // Llama al metodo del controlador si los datos son válidos
                    EmpleadoController.buscarEmpleadoPorDatos(nombreEmpleadoBuscado, apellidosEmpleadoBuscado);
                } catch (IllegalArgumentException e) {
                    // Captura errores de validación y muestra el mensaje
                    System.err.println("Error en los datos introducidos: " + e.getMessage());
                }
                // Sub-opción 2: Buscar empleado por cargo
            } else if (opcion == 2) {
                // Solicita los datos necesarios de entrada para la opción elegida
                System.out.println("Introduzca el cargo del empleado:");
                String cargoABuscar = scanner.nextLine().toLowerCase();
                try {
                    // Validar el cargo antes de buscar
                    EmpleadoValidacionDatos.validarCargo(cargoABuscar);
                    // Llama al controlador si la validación pasa
                    EmpleadoController.buscarEmpleadosPorCargo(cargoABuscar);
                } catch (IllegalArgumentException e) {
                    // Muestra un mensaje de error si la validación falla
                    System.err.println("Error en los datos introducidos: " + e.getMessage());
                }
                // Si la opción ingresada no es válida, se muestra un mensaje de error
            } else if (opcion != 3) {
                System.out.println("Debe seleccionar un número de 1 al 3");
                scanner.nextLine();
            }
        } else if (opcion == 3) {
            // Solicita los datos necesarios de entrada para la opción elegida
            System.out.println("Introduzca el nombre del empleado:");
            String nombreEmpleado = scanner.nextLine().toLowerCase();
            System.out.println("Introduzca los apellidos del empleado:");
            String apellidosEmpleado = scanner.nextLine().toLowerCase();
            try {
                // Valida los datos antes de buscar
                EmpleadoValidacionDatos.validarNombre(nombreEmpleado);
                EmpleadoValidacionDatos.validarApellidos(apellidosEmpleado);
                // Llama al metodo del controlador si los datos son válidos
                EmpleadoController.empleadoAEliminar(nombreEmpleado, apellidosEmpleado);
            } catch (IllegalArgumentException e) {
                // Captura errores de validación y muestra el mensaje
                System.err.println("Error en los datos introducidos: " + e.getMessage());
            }
        } else if (opcion == 4) {
            // Solicita los datos necesarios de entrada para la opción elegida
            System.out.println("Introduzca el nombre del empleado:");
            String nombreEmpleadoAActualizar = scanner.nextLine().toLowerCase();
            System.out.println("Introduzca los apellidos del empleado:");
            String apellidosEmpleadoAActualizar = scanner.nextLine().toLowerCase();
            try {
                // Valida los datos antes de buscar
                EmpleadoValidacionDatos.validarNombre(nombreEmpleadoAActualizar);
                EmpleadoValidacionDatos.validarApellidos(apellidosEmpleadoAActualizar);
                // Llama al metodo del controlador si los datos son válidos
                EmpleadoController.empleadoAActualizar(nombreEmpleadoAActualizar, apellidosEmpleadoAActualizar);
            } catch (IllegalArgumentException e) {
                // Captura errores de validación y muestra el mensaje
                System.err.println("Error en los datos introducidos: " + e.getMessage());
            }
        } else if (opcion == 5) {
            System.out.println("Listado actual de empleados:");
            // Obtenemos la lista de empleados desde el controlador y la imprimimos
            List<Empleado> empleados = EmpleadoController.obtenerListaDeEmpleados();
            // Imprime cada empleado
            for (Empleado empleado : empleados) {
                System.out.println("\n" + empleado);
            }
            // Si la opción ingresada no es válida y no es 6, se muestra un mensaje de error
        } else {
            System.out.println("Debe seleccionar un número de 1 al 6");
        }
        // El programa sigue ejecutándose mientras no se elija la opción para salir
    } while (opcion != 6);
    // Al salir del bucle, cerramos el Scanner y mostramos un mensaje de despedida
    scanner.close();
    System.out.println("****Good Bye****");
}

