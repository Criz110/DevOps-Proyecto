FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Instalar netcat (nc)
RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*

COPY target/*.jar app.jar
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

EXPOSE 8080

ENTRYPOINT ["./wait-for-it.sh", "eureka-server", "8761", "java", "-jar", "app.jar", "--spring.profiles.active=docker"]
