FROM openjdk:17-jdk-alpine
MAINTAINER charapadev@gmail.com
COPY target/poketavern-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]