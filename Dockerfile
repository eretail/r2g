FROM maven:3.5-jdk-8-alpine
WORKDIR /r2g
RUN mvn clean install

FROM openjdk:8-jdk-alpine
VOLUME /tmp
WORKDIR /r2g
ADD target/r2g-1.0.jar target/app.jar
RUN bash -c 'touch target/app.jar'
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","target/app.jar"]