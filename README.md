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
1. Java 
2. MySQL con una base de datos llamada `empleado_db`.
3. Configuración del archivo `persistence.xml` para la conexión a la base de datos.
4. Maven para gestionar las dependencias.

### Pasos para Ejecutar
1. Compilar el proyecto usando Maven:
2. Ejecutar la clase principal `Main`:
3. Seguir las instrucciones del menú para realizar operaciones CRUD.

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
   - La base de datos `empleado_db` ya está creada y accesible.

3. **Paquete `utilies` para validaciones:**
   - Diseñado para centralizar y reutilizar la lógica de validación.
   - Justificado por la necesidad de escalabilidad y modularidad en proyectos futuros.

---

## Conclusión
El proyecto "Empleado" está estructurado para ser escalable, modular y fácil de mantener. La separación de responsabilidades entre entidades, controladores, persistencia y validaciones permite un desarrollo más eficiente y robusto.

