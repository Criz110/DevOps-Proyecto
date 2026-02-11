# -------- STAGE 1: BUILD --------
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn -B -ntp dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests


# -------- STAGE 2: RUNTIME --------
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Instalar netcat
RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*

# Copiar el jar compilado desde el stage builder
COPY --from=builder /app/target/*.jar app.jar

COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

EXPOSE 8080

ENTRYPOINT ["./wait-for-it.sh", "eureka-server", "8761", "java", "-jar", "app.jar", "--spring.profiles.active=docker"]