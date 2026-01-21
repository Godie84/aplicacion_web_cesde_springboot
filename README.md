# API REST - Gestión de Cursos CESDE

Esta es una API REST desarrollada con **Spring Boot**, para gestionar cursos y docentes de CESDE.

## Funcionalidades

### Cursos
- Crear curso
- Listar cursos
- Actualizar curso
- Eliminar curso
- Filtros de búsqueda:
    - Por nombre
    - Por duración
    - Por precio máximo
    - Por docente

### Docentes
- Crear docente
- Listar docentes
- Actualizar docente
- Eliminar docente

---

## Tecnologías

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- MySQL
- Spring Validation
- Swagger (OpenAPI)
- Lombok

---

## Requisitos

- Java 17 instalado
- MySQL instalado
- Maven instalado

---

## Configuración de la base de datos (MySQL)

Crea una base de datos:

```sql
CREATE DATABASE cursos_db;
```

Archivo application.properties

Configura tu conexión a MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cursos_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Ejecutar el proyecto

Desde la terminal:
```bash
mvn clean install
mvn spring-boot:run
```

Documentación Swagger (OpenAPI)

Swagger UI está disponible en:
```bash
http://localhost:8080/swagger-ui/index.html
```