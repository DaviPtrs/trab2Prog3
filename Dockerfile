FROM openjdk:8

WORKDIR /usr/src/myapp

RUN apt update && apt install -y ant
