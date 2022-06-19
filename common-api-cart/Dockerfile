FROM openjdk:17-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} common-api-cart.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/common-api-cart.jar"]