FROM dockerfile/java:oracle-java8
VOLUME /tmp
ADD target/core-0.1.0-RELEASE.jar target/app.jar
RUN bash -c 'touch target/app.jar'
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","target/app.jar"]