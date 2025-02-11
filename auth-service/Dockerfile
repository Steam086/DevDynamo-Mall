FROM eclipse-temurin:17
LABEL authors="jjr"

RUN mkdir /opt/app
COPY target/auth-service-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/auth-service-0.0.1-SNAPSHOT.jar"]