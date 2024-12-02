# Stage 1: Build the Spring Boot application using Gradle
FROM gradle:8.5-jdk21 AS build

# Set the working directory
WORKDIR /app

# Copy Gradle wrapper and necessary files for dependency resolution
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Copy the source code
COPY src src

# Build the application and generate the JAR file
RUN ./gradlew clean bootJar --no-daemon

# Stage 2: Create a minimal image to run the Spring Boot JAR
FROM eclipse-temurin:21-jre

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

COPY src/main/resources/application.properties /app/application.properties

# Expose the default Spring Boot port
EXPOSE 8081

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]