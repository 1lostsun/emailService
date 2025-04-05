FROM amazoncorretto:21.0.6

WORKDIR / email-service

COPY target/microservice-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]