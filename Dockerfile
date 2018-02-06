FROM fabric8/java-alpine-openjdk8-jdk

LABEL maintainer 'Dionatan Ribeiro'

COPY ./target/swagger-sample-0.0.1-SNAPSHOT.jar /opt

WORKDIR /opt

CMD java -jar swagger-sample-0.0.1-SNAPSHOT.jar

EXPOSE 8080
