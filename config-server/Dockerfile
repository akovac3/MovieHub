FROM openjdk:17
LABEL maintainer="moviehub.com"
ADD target/config-server-0.0.1-SNAPSHOT.jar config-server-docker.jar
ENTRYPOINT ["java", "-jar", "config-server-docker.jar"]