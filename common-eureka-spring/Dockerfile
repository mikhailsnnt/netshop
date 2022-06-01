FROM openjdk:17-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} common-eureka-spring.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/common-eureka-spring.jar"]