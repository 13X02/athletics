FROM maven:3.9.0-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
# Runtime stage
FROM eclipse-temurin:17

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/eventservice-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8901

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
