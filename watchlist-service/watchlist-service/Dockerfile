FROM openjdk:17
LABEL maintainer="moviehub.com"
ADD target/watchlist-service-0.0.1-SNAPSHOT.jar watchlist-service-docker.jar
ENTRYPOINT ["java", "-jar", "watchlist-service-docker.jar"]