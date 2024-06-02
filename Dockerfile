FROM eclipse-temurin:17-jdk-alpine

ADD target/socializer-v1.jar app.jar
ADD config/ config/

ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8900
