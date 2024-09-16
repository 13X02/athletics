FROM maven:3.9.0-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
# Use a lighter base image for running the application
FROM eclipse-temurin:17

# Set the working directory
WORKDIR /app

# Copy the jar file from the previous build step
COPY --from=0 /app/target/usermanagementservice-0.0.1-SNAPSHOT.jar /app/usermanagementservice.jar

# Expose the port the application will run on
EXPOSE 8900

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/usermanagementservice.jar"]
