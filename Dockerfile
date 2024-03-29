FROM adoptopenjdk/openjdk11:latest

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=develop", "/app.jar"]