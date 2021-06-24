FROM openjdk:15-buster
RUN apt update; apt install ffmpeg -y
RUN pwd
RUN mkdir /root/videos
RUN mkdir /root/streams
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]