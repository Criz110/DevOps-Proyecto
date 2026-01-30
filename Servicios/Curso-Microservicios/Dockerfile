FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /workspace/app
COPY  . .
RUN ["mvn","clean","package"]

FROM eclipse-temurin:17-jre
COPY --from=builder /workspace/app/microservicio-web/target/microservicio-web-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]