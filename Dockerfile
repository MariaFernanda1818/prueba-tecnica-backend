# Stage 1: Build with Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos el POM y descargamos dependencias (cache layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el código fuente y compilamos
COPY src ./src
RUN mvn package -DskipTests -B

# Stage 2: Runtime
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiamos sólo el JAR compilado
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar ./backend-ms.jar

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "backend-ms.jar"]
