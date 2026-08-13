# Ferreteria

Proyecto full-stack Java/Spring Boot para gestionar herramientas y productos de una ferretería.

## Descripción del reto

Una ferretería necesita una API REST desarrollada con Spring Boot para administrar las herramientas y productos disponibles en el negocio.

La solución contempla una arquitectura backend con persistencia relacional y un frontend web adicional desarrollado con Thymeleaf para administrar los productos desde una interfaz gráfica.

El objetivo es implementar un CRUD completo de herramientas o productos, aplicando correctamente la arquitectura de una aplicación Spring Boot, utilizando Spring Data JPA y exponiendo los datos mediante una API REST.

## Objetivo del proyecto

Permitir:

- Registrar una nueva herramienta
- Visualizar todas las herramientas registradas
- Consultar una herramienta específica
- Modificar los datos de una herramienta existente
- Eliminar una herramienta del sistema

Cada herramienta debe tener los datos principales:

- Nombre
- Marca
- Categoría
- Precio
- Cantidad disponible en stock
- Descripción

## Arquitectura implementada

El proyecto sigue una arquitectura por capas, típica de Spring Boot:

- Model: entidades JPA
- Repository: interfaces de acceso a datos con Spring Data JPA
- Service: lógica de negocio
- Controller: endpoints REST y vistas web

### Capas principales

- `model/Producto.java`
  - Entidad JPA que representa un producto/herramienta.
  - Campos: `codProducto`, `nombre`, `marca`, `categoria`, `precio`, `stock`, `descripcion`.

- `repository/IProductoRepository.java`
  - Extiende `JpaRepository<Producto, Long>`.
  - Permite operaciones CRUD básicas de forma automática.

- `service/IProductoService.java`
  - Define contratos del servicio.

- `service/ProductoService.java`
  - Implementa la lógica del negocio.
  - Valida datos mínimos: nombre, marca, categoría, precio y stock.

- `controller/ProductoRestController.java`
  - Expose endpoints REST para consumir desde frontend o clientes externos.

- `controller/ProductoWebController.java`
  - Controlador MVC para Thymeleaf.
  - Permite gestionar los productos desde vistas HTML y formularios web.

- `templates/productos/`
  - Vistas con Thymeleaf para listar productos y registrar/editar productos.

- `static/css/styles.css`
  - Estilos visuales del frontend web.

## Tecnologías utilizadas

- Java 21
- Spring Boot 4.x
- Spring Web MVC
- Spring Data JPA
- Hibernate ORM
- MySQL (entorno local de desarrollo)
- H2 Database (para ejecutar tests)
- Thymeleaf (frontend web)
- Maven Wrapper

## Base de datos

La aplicación está preparada para trabajar con MySQL en entorno local y con H2 en memoria para pruebas automáticas.

### Configuración local con MySQL (XAMPP)

1. Iniciar MySQL desde XAMPP.
2. Crear la base de datos:

   CREATE DATABASE ferreteria CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

3. Configurar `src/main/resources/application.properties`:

   #### Configuración de la base de datos
   
   spring.docker.compose.enabled=false
   spring.datasource.url=jdbc:mysql://localhost:3306/ferreteria?connectionTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
      
   #### Configuración de JPA
   spring.jpa.hibernate.ddl-auto=update

> La propiedad `spring.docker.compose.enabled=false` evita que Spring intente levantar Docker Compose al iniciar la app.

### Configuración para tests con H2

Se usa una base de datos H2 en memoria para correr pruebas sin depender de MySQL.

Archivo de prueba:

- `src/test/resources/application.properties`

## API REST

Base URL:

- `/api/productos`

Endpoints implementados:

- GET `/api/productos` → listar todos los productos
- GET `/api/productos/{codProd}` → obtener un producto por id
- POST `/api/productos` → crear un producto
- PUT `/api/productos/{codProd}` → editar un producto existente
- DELETE `/api/productos/{codProd}` → eliminar un producto

## Frontend Thymeleaf

Se implementó una interfaz web con Thymeleaf para administrar herramientas desde el navegador.

### Vistas disponibles

- `/productos` → lista de productos
- `/productos/nuevo` → formulario para registrar un producto
- `/productos/editar/{codProd}` → formulario para editar
- `/productos/eliminar/{codProd}` → eliminación por formulario POST

## Validaciones implementadas

La lógica del servicio valida que:

- Nombre no sea nulo ni vacío
- Marca no sea nula ni vacía
- Categoría no sea nula ni vacía
- Precio sea mayor a 0
- Stock sea mayor o igual a 0

## Cómo levantar el proyecto

### Prerrequisitos

- Java 21 instalado
- Maven o Maven Wrapper
- MySQL en XAMPP o base de datos equivalente

### Ejecutar tests

```bash
./mvnw test
```

### Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

Luego abrir:

- API REST: `http://localhost:8080/api/productos`
- Frontend Thymeleaf: `http://localhost:8080/productos`

## Estructura del proyecto

```text
Ferreteria/
├── src/
│   ├── main/
│   │   ├── java/com/example/Ferreteria/
│   │   │   ├── controller/
│   │   │   │   ├── ProductoRestController.java
│   │   │   │   └── ProductoWebController.java
│   │   │   ├── model/
│   │   │   │   └── Producto.java
│   │   │   ├── repository/
│   │   │   │   └── IProductoRepository.java
│   │   │   ├── service/
│   │   │   │   ├── IProductoService.java
│   │   │   │   └── ProductoService.java
│   │   │   └── FerreteriaApplication.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   ├── static/css/styles.css
│   │   │   └── templates/productos/
│   │   │       ├── formulario.html
│   │   │       └── lista.html
│   │   └── test/
│   │       └── resources/application.properties
│   └── pom.xml
├── README.md
├── mvnw
├── mvnw.cmd
├── compose.yaml
└── .gitignore
```

## Observaciones finales

Este proyecto cumple con el reto técnico propuesto: implementación de un CRUD completo para productos/herramientas en Spring Boot con base de datos relacional, API REST, y extra opcional de frontend con Thymeleaf.

La solución está pensada para ser escalable y para servir de base para una futura evolución hacia un frontend independiente y una API más robusta con DTOs, validaciones avanzadas y manejo centralizado de errores.

Futuras mejoras podrían incluir: seguridad con Spring Security, roles de usuario, paginación y filtrado de productos, integración con un frontend moderno (React, Angular), crear servicios de autenticación y autorización, otros escenarios de negocio como ventas, clientes, proveedores, etc.