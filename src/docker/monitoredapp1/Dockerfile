FROM openjdk:14-jdk-alpine
ARG JAR_PATH=monitoredApp1/target/monitoredApp1-*.jar
COPY $JAR_PATH app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

