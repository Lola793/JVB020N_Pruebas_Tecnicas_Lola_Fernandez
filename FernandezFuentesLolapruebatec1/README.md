# Documentación del Proyecto Empleado

## Propósito
El proyecto "Empleado" permite gestionar información de empleados mediante operaciones CRUD (Crear, Leer, Actualizar, Eliminar). Utiliza JPA para interactuar con una base de datos MySQL y organiza su arquitectura para facilitar la escalabilidad y mantenibilidad.

---

## Clases y Métodos

### 1. **`Empleado`** (Entidad)
Ubicación: `org.example.entities`

**Propósito:** Representa un empleado en la base de datos.

**Atributos:**
- `id`: Identificador único generado automáticamente.
- `nombre`: Nombre del empleado.
- `apellidos`: Apellidos del empleado.
- `cargo`: Cargo desempeñado.
- `salario`: Salario anual del empleado.
- `fechaContratacion`: Fecha de contratación del empleado.

**Métodos:**
- Getters y setters para cada atributo.
- `toString()`: Devuelve una representación en texto de un empleado.

---

### 2. **`EmpleadoController`** (Controlador)
Ubicación: `org.example.controllers`

**Propósito:** Gestiona las solicitudes desde la interfaz de usuario hacia la capa de persistencia.

**Métodos:**
1. **`crearEmpleado(Empleado nuevoEmpleado)`**
   - Crea un nuevo empleado en la base de datos.
   - Uso: Llamado tras validar los datos.

2. **`buscarEmpleadoPorDatos(String nombre, String apellidos)`**
   - Busca un empleado por nombre y apellidos.
   - Retorna el empleado o `null` si no se encuentra.

3. **`buscarEmpleadosPorCargo(String cargo)`**
   - Devuelve una lista de empleados que coinciden con el cargo proporcionado.

4. **`empleadoAEliminar(String nombre, String apellidos)`**
   - Elimina un empleado existente basado en nombre y apellidos.

5. **`empleadoAActualizar(String nombre, String apellidos)`**
   - Permite actualizar los datos de un empleado.

6. **`obtenerListaDeEmpleados()`**
   - Devuelve una lista de todos los empleados registrados.

---

### 3. **`EmpleadoJPA`** (Persistencia)
Ubicación: `org.example.persistence`

**Propósito:** Realiza operaciones directamente sobre la base de datos.

**Métodos:**
1. **`crearEmpleado(Empleado nuevoEmpleado)`**
   - Persiste un nuevo empleado en la base de datos.

2. **`buscarEmpleadoPorDatos(String nombre, String apellidos)`**
   - Busca un empleado por nombre y apellidos.

3. **`buscarEmpleadosPorCargo(String cargo)`**
   - Devuelve empleados que coinciden con el cargo proporcionado.

4. **`eliminarEmpleado(Empleado empleadoAEliminar)`**
   - Elimina un empleado de la base de datos.

5. **`actualizarDatos(Empleado empleadoAActualizar)`**
   - Actualiza información de un empleado existente.

6. **`obtenerListaEmpleados()`**
   - Obtiene todos los empleados de la base de datos.

---

### 4. **`ConfigJPA`** (Configuración de JPA)
Ubicación: `org.example.persistence`

**Propósito:** Proporciona acceso al `EntityManager` para realizar operaciones con JPA.

**Métodos:**
- **`getEntityManager()`**: Devuelve una instancia de `EntityManager`.
- **`close()`**: Cierra el `EntityManagerFactory`.

---

### 5. **`EmpleadoValidacionDatos`** (Validaciones)
Ubicación: `org.example.utilies`

**Propósito:** Valida los datos de los empleados antes de interactuar con el controlador o la base de datos.

**Métodos:**
1. **`validarNombre(String nombre)`**: Verifica que el nombre no sea nulo ni vacío.
2. **`validarApellidos(String apellidos)`**: Verifica que los apellidos no sean nulos ni vacíos.
3. **`validarCargo(String cargo)`**: Verifica que el cargo no sea nulo ni vacío.
4. **`validarSalario(Float salario)`**: Valida que el salario sea positivo.
5. **`validarFechaContratacion(LocalDate fechaContratacion)`**: Verifica que la fecha no sea posterior a la actual.
6. **`validarEmpleadoCompleto(Empleado empleado)`**: Valida todos los campos del empleado.

**Justificación del paquete `utilies`:**
El paquete `utilies` centraliza las validaciones para:
- **Reutilización:** Se evita duplicar código de validación.
- **Escalabilidad:** Permite agregar nuevas validaciones de manera sencilla.
- **Modularidad:** Mantiene la lógica de validación separada del controlador.

---

## Ejecución de la Aplicación

### Requisitos
1. Java 17 o superior.
2. MySQL con la base de datos llamada `empleado`.
3. Configuración del archivo `persistence.xml` para la conexión a la base de datos. 
4. Maven para gestionar las dependencias.

### Pasos para Ejecutar
1. Crear la base de datos usando el archivo `empleado.sql`
2. Compilar el proyecto usando Maven:
3. Ejecutar la clase principal `Main`
   
### Interacción y Pruebas desde la Terminal
La aplicación `Main` funciona mediante un menú interactivo en la terminal. A continuación, se detalla cada paso con ejemplos:

1. **Al iniciar la aplicación:**
   - Aparece un menú con las opciones disponibles: Crear empleado, Buscar empleado, Eliminar empleado, Actualizar empleado, Obtener listado de empleados, y Cerrar sesión.

2. **Opciones del menú:**
   - **Crear empleado:**
     - Introduzca los datos solicitados:
       - Ejemplo:
         ```
         Introduzca el nombre del empleado: Juan
         Introduzca los apellidos del empleado: Pérez
         Introduzca el cargo del empleado: Administrador
         Introduzca el salario anual del empleado: 30000
         Año de contratación del empleado YYYY: 2023
         Mes de contratación del empleado: 5
         Día de contratación del empleado: 20
         ```
       - Resultado: "El empleado es válido. Se ha añadido correctamente."
   - **Buscar empleado:**
     - Seleccione una sub-opción:
       - Buscar por nombre y apellidos:
         ```
         Introduzca el nombre del empleado: Juan
         Introduzca los apellidos del empleado: Pérez
         ```
         Resultado: "Empleado encontrado: Administrador, Salario: 30000, Fecha de contratación: 2023-05-20."
       - Buscar por cargo:
         ```
         Introduzca el cargo del empleado: Administrador
         ```
         Resultado: "Empleado encontrado: Juan Pérez, Salario: 30000, Fecha de contratación: 2023-05-20."
   - **Eliminar empleado:**
     ```
     Introduzca el nombre del empleado: Juan
     Introduzca los apellidos del empleado: Pérez
     ```
     Resultado: "Empleado eliminado correctamente."
   - **Actualizar empleado:**
     ```
     Introduzca el nombre del empleado: Juan
     Introduzca los apellidos del empleado: Pérez
     Elija una de las siguientes opciones:
     1.- Actualizar cargo
     2.- Actualizar salario
     Opción: 1
     Introduzca el nuevo cargo del empleado: Gerente
     ```
     Resultado: "Cargo actualizado correctamente."
   - **Obtener listado de empleados:**
     ```
     Resultado:
     Empleado{id=1, nombre='Juan', apellidos='Pérez', cargo='Gerente', salario=30000, fechaContratacion=2023-05-20}
     ```
   - **Cerrar sesión:**
     - Finaliza la ejecución de la aplicación.

### Ejemplo Completo
1. Seleccione "1" para crear un empleado y siga las instrucciones del ejemplo de "Crear empleado".
2. Introduzca "5" para listar todos los empleados y confirmar que el nuevo empleado fue registrado.
3. Use "2" para buscar un empleado por su nombre o cargo.
4. Elimine un empleado introduciendo "3" y sus datos.
5. Actualice el cargo o salario de un empleado con "4" y confirme los cambios con "5".

### Pruebas
1. Crear un empleado con datos válidos.
2. Intentar crear un empleado con datos inválidos (p. ej., salario negativo) y verificar que las validaciones lo impiden.
3. Buscar empleados por nombre y apellidos o por cargo.
4. Actualizar un empleado existente.
5. Eliminar un empleado.
6. Obtener el listado completo de empleados.

---

## Supuestos
1. **Validaciones:**
   - Los nombres y apellidos deben ser cadenas no vacías.
   - El salario debe ser positivo y con dos decimales.
   - La fecha de contratación no puede ser futura.

2. **Base de Datos:**
   - La base de datos `empleado_db` ya está creada y accesible con el usuario "root" y sin contraseña.

3. **Paquete `utilies` para validaciones:**
   - Diseñado para centralizar y reutilizar la lógica de validación.
   - Justificado por la necesidad de escalabilidad y modularidad en proyectos futuros.

---

## Conclusión
El proyecto "Empleado" está estructurado para ser escalable, modular y fácil de mantener. La separación de responsabilidades entre entidades, controladores, persistencia y validaciones permite un desarrollo más eficiente y robusto.
