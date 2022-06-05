FROM openjdk:17-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} common-api-auth.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/common-api-auth.jar"]