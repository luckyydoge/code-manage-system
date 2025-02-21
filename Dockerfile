FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

RUN ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN apt-get update && apt-get install -y \
     default-mysql-client \
     default-libmysqlclient-dev

RUN mkdir ./docs/sql -p
RUN touch ./docs/sql/backup.sql

ENTRYPOINT ["java","-jar","app.jar"]