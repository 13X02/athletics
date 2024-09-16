FROM maven:3.9.0-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
# Stage 2: Runtime
FROM eclipse-temurin
# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/wellness-0.0.1-SNAPSHOT.jar /app/wellness.jar

# Expose the port the application will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/wellness.jar"]
