FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN mvn clean install
ADD target/r2g-1.0.jar target/app.jar
RUN bash -c 'touch target/app.jar'
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","target/app.jar"]