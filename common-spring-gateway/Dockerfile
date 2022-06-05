FROM openjdk:17-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} common-spring-gateway.jar
ENTRYPOINT ["java","-jar","/common-spring-gateway.jar"]