# Construcción del .jar
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

# Creación de la imagen y el contenedor
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/superMercado-0.0.1.jar superMercado.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/superMercado.jar"]