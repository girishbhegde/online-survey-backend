FROM ubuntu:16.04

RUN apt-get -qq -y update && \
    apt-get -qq -y install openjdk-8-jdk maven gradle curl && \
    rm -rf /var/cache/apt /var/lib/apt/lists/*

EXPOSE 8080

WORKDIR /libs
ENV CLASSPATH /libs/*

ENTRYPOINT ["/bin/bash", "/start.sh"]

COPY start.sh /
COPY libs/ /libs/