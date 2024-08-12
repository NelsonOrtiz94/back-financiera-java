# Aplicación Spring Boot para Entidad Financiera

### Descripción:

- Esta aplicación Spring Boot está diseñada para gestionar clientes, cuentas, productos y transacciones en una entidad financiera.

## Funcionalidades:

### Administración de Clientes:
- Crear un nuevo cliente
- Modificar la información de un cliente existente
- Eliminar un cliente
- Listar todos los clientes
- Encontrar un cliente por ID

### Administración de Cuentas:
- Crear una nueva cuenta
- Encontrar una cuenta por ID
- Listar todas las cuentas
- Actualizar la información de una cuenta
- Eliminar una cuenta

### Administración de Productos:
- Crear un nuevo producto (cuenta corriente, cuenta de ahorros)
- Encontrar un producto por ID
- Listar todos los productos
- Actualizar la información de un producto (No implementado)
- Eliminar un producto (No implementado)

### Administración de Transacciones:
- Crear una nueva transacción (No implementado)
- Encontrar una transacción por ID
- Listar todas las transacciones
- Actualizar la información de una transacción (No implementado)
- Eliminar una transacción (No implementado)

### Manejo de Errores:

- Se lanza una `ClienteNotFoundException` cuando no se encuentra un cliente.
- Las implementaciones del servicio probablemente arrojen `IllegalArgumentException` para datos no válidos.

### Funcionalidad Pendiente:

La creación, actualización y eliminación de transacciones no se han implementado en `TransaccionServiceImpl`.

### Estructura del Paquete:

- `com.entidad.financiera.config`: Contiene clases de configuración como `WebConfig` para la configuración CORS.
- `com.entidad.financiera.controller`: Contiene controladores para manejar solicitudes HTTP para Cliente, Cuenta, Producto y Transaccion.
- `com.entidad.financiera.dto`: Contiene `TransaccionRequest` DTO para crear transacciones.
- `com.entidad.financiera.entity`: Contiene clases de entidad para Cliente, Cuenta, Producto y Transaccion que representan datos en la base de datos.
- `com.entidad.financiera.exception`: Contiene `ClienteNotFoundException` para manejar casos donde no se encuentra un cliente.
- `ClienteNotFoundException`: Contiene implementaciones de servicio para ClienteServiceI, CuentaServiceI, ProductoServiceI y TransaccionServiceI. Estos manejan la lógica empresarial para cada entidad.

### Tecnologías Utilizadas:

- Spring Boot
- JPA
- Controladores REST
- Servicios
- Manejo de excepciones

### Conclusión:

Esta aplicación proporciona una estructura básica para gestionar clientes, cuentas y productos en una aplicación de entidad financiera. Demuestra el uso de `Spring Boot`con `Java`, entidades JPA, controladores y servicios.

### Nota:

Este README es una descripción general de la aplicación. Se recomienda consultar el código fuente para obtener más detalles sobre la implementación.