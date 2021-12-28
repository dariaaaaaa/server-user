FROM openjdk:17-alpine

WORKDIR /opt/server-user
COPY ./target/server-user-0.0.1-SNAPSHOT.jar server-user.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "server-user.jar"]