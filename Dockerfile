FROM anapsix/alpine-java:8

RUN apk update && apk add curl

WORKDIR /opt/
RUN curl -O http://apache.lauf-forum.at/cassandra/3.0.16/apache-cassandra-3.0.16-bin.tar.gz
RUN tar -xvzf apache-cassandra-3.0.16-bin.tar.gz && rm apache-cassandra-3.0.16-bin.tar.gz
RUN ./apache-cassandra-3.0.16/bin/cassandra &
ADD target/CassandraSpringBootAngular.jar CassandraSpringBootAngular.jar
ADD start.sh start.sh
ENTRYPOINT ["./start.sh"]

#docker build -t cassandra_spring_boot:1.0.0 .
#docker run -d -p 8080:8080 cassandra_spring_boot:1.0.0