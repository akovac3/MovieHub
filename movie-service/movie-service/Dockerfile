FROM openjdk:17
LABEL maintainer="moviehub.com"
ADD target/movie-service-0.0.1-SNAPSHOT.jar movie-service-docker.jar
ENTRYPOINT ["java", "-jar", "movie-service-docker.jar"]