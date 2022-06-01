FROM openjdk:17
LABEL maintainer="moviehub.com"
ADD target/system-events-0.0.1-SNAPSHOT.jar system-events-docker.jar
ENTRYPOINT ["java", "-jar", "system-events-docker.jar"]