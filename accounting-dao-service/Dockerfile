FROM openjdk:19-jdk-slim
ARG JAR_FILE=target/accounting-dao-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} accounting-dao-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "accounting-dao-service.jar"]