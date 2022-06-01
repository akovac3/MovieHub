FROM openjdk:17
LABEL maintainer="moviehub.com"
ADD target/eureka-0.0.1-SNAPSHOT.jar eureka-docker.jar
ENTRYPOINT ["java", "-jar", "eureka-docker.jar"]