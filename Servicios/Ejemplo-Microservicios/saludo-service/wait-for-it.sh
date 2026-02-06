COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh", "eureka-server:8761", "--", "java", "-jar", "app.jar", "--spring.profiles.active=docker"]
