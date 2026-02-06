FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

RUN apt-get update \
 && apt-get install -y netcat \
 && rm -rf /var/lib/apt/lists/*

COPY target/auth-service-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

ENTRYPOINT ["./wait-for-it.sh", "mysql", "3306", "--", "java", "-jar", "app.jar", "--spring.profiles.active=docker"]
