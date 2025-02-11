FROM eclipse-temurin:21
LABEL authors="jjr"

RUN mkdir /opt/app
COPY japp.jar /opt/app


CMD ["java", "-jar", "/opt/app/japp.jar"]
#ENTRYPOINT ["top", "-b"]