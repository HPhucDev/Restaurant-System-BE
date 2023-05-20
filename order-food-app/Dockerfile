FROM maven:3.8.1-jdk-11 AS build

# set working directory in container
WORKDIR /app

# copy source code into container
COPY ./dao/pom.xml ./dao/
COPY ./business/pom.xml ./business/
COPY ./web/pom.xml ./web/
COPY ./pom.xml ./

RUN mvn -B dependency:go-offline

COPY ./dao ./dao
COPY ./business ./business
COPY ./web ./web

# build project using maven
RUN mvn clean package -DskipTests 

WORKDIR /app
# FROM adoptopenjdk/openjdk11:alpine-jre
FROM adoptopenjdk/openjdk11:latest

COPY --from=build ./app/web/target/*.war order-food-app-ws.war
ENTRYPOINT ["java","-jar","order-food-app-ws.war"]