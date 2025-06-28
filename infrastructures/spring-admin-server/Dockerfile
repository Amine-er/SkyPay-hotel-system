FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/spring-admin-server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 7070

ENTRYPOINT ["java", "-jar", "app.jar"]