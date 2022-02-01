FROM openjdk:11
ADD target/websocket-docker.jar websocket-docker.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","websocket-docker.jar"]