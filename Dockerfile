FROM openjdk:8

WORKDIR /usr/src/

COPY launcher.sh /launcher.sh

RUN chmod +x /launcher.sh

EXPOSE 8080

CMD /launcher.sh
