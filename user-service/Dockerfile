FROM openjdk:17
LABEL maintainer="moviehub.com"
ADD target/user-service-0.0.1-SNAPSHOT.jar user-service-docker.jar
ENTRYPOINT ["java", "-jar", "user-service-docker.jar"]