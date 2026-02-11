# ================================
# STAGE 1: BUILD
# ================================
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiar solo el pom primero (mejora cache)
COPY pom.xml .

# Descargar dependencias (cacheable)
RUN mvn -B -ntp dependency:go-offline

# Copiar código fuente
COPY src ./src

# Compilar aplicación
RUN mvn -B -ntp clean package -DskipTests


# ================================
# STAGE 2: RUNTIME
# ================================
FROM eclipse-temurin:17-jre-jammy

# Crear usuario no-root (mejor práctica de seguridad)
RUN useradd -m spring

WORKDIR /app

# Copiar solo el jar generado
COPY --from=builder /app/target/*.jar app.jar

# Cambiar ownership
RUN chown -R spring:spring /app

# Cambiar a usuario no-root
USER spring

# Puerto de tu microservicio
EXPOSE 8080

# Variables optimizadas de JVM para contenedores
ENV JAVA_OPTS="-XX:+UseContainerSupport \
               -XX:MaxRAMPercentage=75.0 \
               -XX:+UseG1GC \
               -Djava.security.egd=file:/dev/./urandom"

# Ejecutar aplicación
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar --spring.profiles.active=docker"]