FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY target/wordspark-0.0.1-SNAPSHOT-microbundle.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "wordspark-0.0.1-SNAPSHOT-microbundle.jar"]
