FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/b2c-api.jar b2c-api.jar
EXPOSE 8081
CMD ["java", "-jar", "b2c-api.jar"]
