FROM openjdk:8-jdk-alpine

ARG APP=./target/app.jar
COPY ${APP} /app.jar

EXPOSE 9090

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
