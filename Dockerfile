FROM adoptopenjdk/openjdk11:jre-nightly
WORKDIR /app
ADD /target/b2c-api.jar b2c-api.jar
EXPOSE 8081
CMD ["java", "-jar", "b2c-api.jar"]