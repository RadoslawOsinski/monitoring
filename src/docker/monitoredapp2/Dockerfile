FROM openjdk:14-jdk-alpine
ARG JAR_PATH=monitoredApp2/target/monitoredApp2-*.jar
COPY $JAR_PATH app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

