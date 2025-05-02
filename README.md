# Backend WebFlux Service

Este proyecto es un **servicio backend** basado en **Spring WebFlux** que gestiona:

* CRUD de productos.
* Asociación de productos a sucursales con stock.
* Consulta de stock máximo por sucursal.
* Creación y gestión de franquicias y sucursales.
* Documentación OpenAPI (Swagger).

---

## 🛠️ Tecnologías

* Java 17
* Spring Boot 3.4.5
* Spring WebFlux
* Spring Data R2DBC (PostgreSQL)
* MapStruct
* Jakarta Validation
* Springdoc OpenAPI (Swagger UI)
* Reactor Core
* Docker
* Maven

---

## ⚙️ Configuración

El archivo `application.yml` contiene la configuración principal:

```yaml
spring:
  webflux:
    base-path: /api/v1
  r2dbc:
    url: r2dbc:postgresql://pg-flex-prueba-tecnica-backend.postgres.database.azure.com:5432/postgres?ssl=true&sslmode=require&schema=prueba_tecnica
    username: adminpg
    password: ComplexP@ssw0rd!

  application:
    name: backend

server:
  port: 8001

springdoc:
  api-docs:
    path: /api/v1/v3/api-docs
  swagger-ui:
    path: /api/v1/swagger-ui
    url: /api/v1/v3/api-docs
  info:
    title: Acme Airlines API
    version: 1.0.0
```

---

## 📦 Empaquetado y dependencias

El proyecto usa **Maven**. El `pom.xml` incluye:

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>3.4.5</version>
</parent>
<properties>
  <java.version>17</java.version>
  <org.mapstruct.version>1.5.5.Final</org.mapstruct.version>
</properties>

<dependencies>
  <!-- R2DBC + WebFlux + Validation -->
  <dependency>spring-boot-starter-data-r2dbc</dependency>
  <dependency>spring-boot-starter-webflux</dependency>
  <dependency>spring-boot-starter-validation</dependency>
  <!-- PostgreSQL R2DBC runtime -->
  <dependency>r2dbc-postgresql (runtime)</dependency>
  <!-- MapStruct -->
  <dependency>mapstruct</dependency>
  <!-- OpenAPI UI -->
  <dependency>springdoc-openapi-starter-webflux-ui</dependency>
  <!-- Testing -->
  <dependency>spring-boot-starter-test (test)</dependency>
  <dependency>reactor-test (test)</dependency>
</dependencies>
```

---

## 🐳 Docker

### Dockerfile multinodo

```dockerfile
# Stage 1: Build
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests -B

# Stage 2: Runtime
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar ./backend-ms.jar
EXPOSE 8001
ENTRYPOINT ["java", "-jar", "backend-ms.jar"]
```

**Construcción y ejecución:**

```bash
# Build image
docker build -t backend-ms .
# Run container
docker run -p 8001:8001 backend-ms
```

---

## 📚 Documentación API

* Swagger UI: `http://localhost:8001/api/v1/swagger-ui`
* OpenAPI JSON: `http://localhost:8001/api/v1/v3/api-docs`

---

## 🚀 Ejecución local

1. Configura tu base de datos PostgreSQL con R2DBC.
2. Actualiza credenciales en `application.yml`.
3. Ejecuta:

   ```bash
   mvn spring-boot:run
   ```
4. Accede a `http://localhost:8001/api/v1/swagger-ui` para probar endpoints.
