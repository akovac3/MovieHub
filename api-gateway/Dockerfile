FROM openjdk:17
LABEL maintainer="moviehub.com"
ADD target/springcloud-gateway-0.0.1-SNAPSHOT.jar springcloud-gateway-docker.jar
ENTRYPOINT ["java", "-jar", "springcloud-gateway-docker.jar"]