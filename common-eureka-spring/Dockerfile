FROM openjdk:17-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} common-eureka-spring.jar
ENTRYPOINT ["java","-jar","/common-eureka-spring.jar"]