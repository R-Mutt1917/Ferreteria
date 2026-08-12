# Ferreteria

Proyecto backend en Spring Boot para la gestión de una ferretería.

## Resumen / Arquitectura

- Stack: Java 21 (compatible), Spring Boot 4.x, Spring Data JPA, Hibernate, H2 (tests), MySQL (producción/desarrollo local), Maven wrapper
- Arquitectura: proyecto monolítico con capas: model (entidades JPA), repository (Spring Data JPA), service (lógica de negocio), controller (REST API).
- Ingeniería: pruebas de integración básicas con SpringBootTest usando H2 en memoria; CI local mediante `./mvnw test`.

## Qué se implementó

- Entidad: Producto (model)
- Repositorio: IProductoRepository (JpaRepository)
- Servicio: IProductoService + ProductoService (lógica de creación/edición/borrado)
- Controlador REST: ProductoRestController con endpoints CRUD
- Configuraciones: H2 para tests y propiedad para desactivar Docker Compose en local (spring.docker.compose.enabled=false)

## Ejecutar localmente (XAMPP + MySQL)

1. Arrancar MySQL desde XAMPP.
2. Crear base de datos `ferreteria` y usuario (o usar root):

   CREATE DATABASE ferreteria CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   CREATE USER 'ferreteriard'@'localhost' IDENTIFIED BY 'TuPasswordSeguro';
   GRANT ALL PRIVILEGES ON ferreteria.* TO 'ferreteriard'@'localhost';
   FLUSH PRIVILEGES;

3. En `src/main/resources/application.properties` configurar la conexión:

   spring.docker.compose.enabled=false
   spring.datasource.url=jdbc:mysql://localhost:3306/ferreteria?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
   spring.datasource.username=ferreteriard
   spring.datasource.password=TuPasswordSeguro
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=update

4. Ejecutar:
   - Tests: `./mvnw test`
   - Run: `./mvnw spring-boot:run` o correr desde el IDE la clase `com.example.Ferreteria.FerreteriaApplication`

Nota: En tests se usa H2 (configuración en `src/test/resources/application.properties`).

## Endpoints REST (base: /api/productos)

- GET /api/productos — listar
- GET /api/productos/{codProd} — obtener por id
- POST /api/productos — crear
- PUT /api/productos/{codProd} — actualizar
- DELETE /api/productos/{codProd} — eliminar

## Buenas prácticas y próximos pasos

- Añadir validaciones y DTOs para separar entidad y payloads.
- Agregar pruebas unitarias para servicios y controladores (Mockito/WebMvcTest).
- Añadir manejo de errores centralizado (ControllerAdvice).
- Integrar frontend con Thymeleaf o una SPA (React/Vue) — cuando avances con el frontend, actualizar este README.

Si querés, hago el commit del README ahora y lo subo al remoto (lo puedo hacer por vos).