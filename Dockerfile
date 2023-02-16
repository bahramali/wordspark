FROM openjdk-17-jre

EXPOSE 8080

COPY target/wordspark-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "wordspark-0.0.1-SNAPSHOT.jar"]
