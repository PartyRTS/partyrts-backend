FROM openjdk:15-buster
RUN apt update; apt install ffmpeg -y
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]