# Super Mercado — Solución prueba técnica

Repositorio con la solución a una prueba técnica real de **Java Spring Boot** denominada _Super Mercado_.

## Descripción del caso

Una reconocida cadena de supermercados desea digitalizar su sistema de control de ventas. Para ello se desarrolló una API REST que permite:

- Registrar productos con sus respectivos precios y stock.
- Gestionar las sucursales donde se venden los productos.
- Registrar ventas realizadas en una sucursal, especificando los productos vendidos y sus cantidades.
- Consultar rankings de productos más vendidos y sucursales con mayor número de transacciones.
- Obtener total de ingresos.

### Requisitos técnicos

- Utilizar Spring Boot con JPA para manejo de bases de datos
  Base de datos relacional (por ejemplo H2 o MySql).
- Exponer endpoints RESTful para realizar CRUDS (GET, POST, PUT, DELETE o los métodos que se consideren necesarios).
- Utilizar DTOs para separar modelo de dominio y representación externa.
- Manejo adecuado de errores con Response Entity, códigos HTTP correctos (status code) y mensajes claros.
- Uso de lambdas o streams en al menos una operación del backend.
- Organización modular del proyecto (service, repository, controller).

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- MapStruct (mapeo entre entidades y DTOs)
- Lombok
- PostgreSQL
- Maven

## Endpoints principales

### Productos

| Método | Endpoint              | Descripción                |
| ------ | --------------------- | -------------------------- |
| GET    | `/api/productos`      | Listar todos los productos |
| GET    | `/api/productos/{id}` | Obtener producto por ID    |
| POST   | `/api/productos`      | Crear producto             |
| PUT    | `/api/productos/{id}` | Actualizar producto        |
| DELETE | `/api/productos/{id}` | Eliminar producto          |

### Sucursales

| Método | Endpoint               | Descripción                 |
| ------ | ---------------------- | --------------------------- |
| GET    | `/api/sucursales`      | Listar todas las sucursales |
| GET    | `/api/sucursales/{id}` | Obtener sucursal por ID     |
| POST   | `/api/sucursales`      | Crear sucursal              |
| PUT    | `/api/sucursales/{id}` | Actualizar sucursal         |
| DELETE | `/api/sucursales/{id}` | Eliminar sucursal           |

### Ventas

| Método | Endpoint                                            | Descripción                                           |
| ------ | --------------------------------------------------- | ----------------------------------------------------- |
| GET    | `/api/ventas`                                       | Listar todas las ventas                               |
| GET    | `/api/ventas/filtrar?sucursalId=1&fecha=2026-05-03` | Listar todas las ventas por id de la sucursal y fecha |
| GET    | `/api/ventas/{id}`                                  | Obtener venta por ID                                  |
| POST   | `/api/ventas`                                       | Registrar venta                                       |
| PUT    | `/api/ventas/{id}`                                  | Actualizar venta                                      |
| DELETE | `/api/ventas/{id}`                                  | Eliminar venta                                        |

## Estadisticas

| Método | Endpoint                                               | Descripción                                    |
| ------ | ------------------------------------------------------ | ---------------------------------------------- |
| GET    | `/api/estadisticas/producto-mas-vendido`               | Producto con más unidades vendidas             |
| GET    | `/api/estadisticas/top-producto-mas-vendido?limite=10` | Top N productos más vendidos                   |
| GET    | `/api/estadisticas/sucursal-mas-ventas`                | Sucursal con más transacciones completadas     |
| GET    | `/api/estadisticas/top-sucursal-mas-ventas?limite=10`  | Top N sucursales con más ventas completadas    |
| GET    | `/api/estadisticas/resumen-ventas`                     | Total de ventas realziadas y total de ingresos |

## Decisiones de diseño

- **MapStruct** se eligió como librería de mapeo por su generación de código en tiempo de compilación, lo que elimina el uso de reflection en runtime y garantiza que los errores de mapeo se detecten antes de ejecutar la aplicación.
- **Spring Projections** se utilizan para recibir los resultados de las consultas JPQL de rankings, evitando casteos manuales desde `Object[]` y permitiendo que MapStruct mapee directamente desde la interfaz al DTO.
- El ranking de sucursales y producto considera únicamente ventas con estado `PAGADA`, reflejando únicamente transacciones cerradas exitosamente.

## Créditos

Prueba técnica propuesta por la ingeniera informática y creadora de contenido **Luisina** en su canal de YouTube [TodoCode](https://youtube.com/@TodoCode).

Video original: [Ver aquí](https://www.youtube.com/watch?v=l-Bl45I6UEY)
