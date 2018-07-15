FROM anapsix/alpine-java:8

RUN apk update && apk add curl

WORKDIR /opt/
RUN curl -O http://apache.lauf-forum.at/cassandra/3.0.16/apache-cassandra-3.0.16-bin.tar.gz
RUN tar -xvzf apache-cassandra-3.0.16-bin.tar.gz && rm apache-cassandra-3.0.16-bin.tar.gz
RUN ./apache-cassandra-3.0.16/bin/cassandra &
RUN curl -O http://www-eu.apache.org/dist/tomcat/tomcat-9/v9.0.10/bin/apache-tomcat-9.0.10.tar.gz
RUN tar -xvzf apache-tomcat-9.0.10.tar.gz && rm apache-tomcat-9.0.10.tar.gz
ADD target/CassandraSpringBootAngular.war apache-tomcat-9.0.10/webapps/CassandraSpringBootAngular.war
ADD startTomcat.sh startTomcat.sh
EXPOSE 8080
CMD ["./startTomcat.sh"]

#docker build -t cassandra_spring_boot:1.0.0 .
#docker run -d -p 8080:8080 cassandra_spring_boot:1.0.0
#localhost:8080/CassandraSpringBootAngular/index.html