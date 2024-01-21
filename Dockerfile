# Build stage
FROM maven:3.8.5-openjdk-17-slim AS build
COPY . /
RUN mvn -f /pom.xml clean package -Dmaven.test.skip

# Runtime stage
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /target/cartoon-app-0.0.1-SNAPSHOT.jar /app/cartoon-app.jar
EXPOSE 8080
CMD ["java", "-jar", "cartoon-app.jar"]
