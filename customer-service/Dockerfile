FROM openjdk:19-jdk-slim
ARG JAR_FILE=target/customer-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} customer-service.jar
EXPOSE 8180
ENTRYPOINT ["java", "-jar", "customer-service.jar"]