FROM openjdk:19-jdk-slim
ARG JAR_FILE=target/accounts-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} accounts-service.jar
EXPOSE 8280
ENTRYPOINT ["java", "-jar", "accounts-service.jar"]