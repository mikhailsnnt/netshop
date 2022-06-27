FROM openjdk:17-jdk-alpine
ARG JAR_FILE
COPY build/libs/common-api-auth-1.0.0.jar common-api-auth.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/common-api-auth.jar"]