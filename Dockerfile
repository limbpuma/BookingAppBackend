# Build stage
FROM maven:3.8.1-openjdk-21 AS build
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM openjdk:21-jre-slim
COPY --from=build /home/app/target/booking-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]
