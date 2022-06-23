FROM openjdk:17-jdk-alpine
COPY build/libs/common-api-product-1.0.0.jar common-api-product.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/common-api-product.jar"]