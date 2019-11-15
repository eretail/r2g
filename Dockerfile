FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/eretail/r2g.git

FROM maven:3.5-jdk-8-alpine as build
WORKDIR /app
COPY --from=clone /app/r2g /app
RUN mvn install

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/r2g-1.0.jar /app
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","r2g-1.0.jar"]

