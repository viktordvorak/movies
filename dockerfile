FROM openjdk:17-jdk
COPY target/myapp.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
