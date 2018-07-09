FROM anapsix/alpine-java:8

RUN apk update && apk add curl

WORKDIR /opt/
RUN curl -O http://apache.lauf-forum.at/cassandra/3.0.16/apache-cassandra-3.0.16-bin.tar.gz
RUN tar -xvzf apache-cassandra-3.0.16-bin.tar.gz && rm apache-cassandra-3.0.16-bin.tar.gz
RUN ./apache-cassandra-3.0.16/bin/cassandra &