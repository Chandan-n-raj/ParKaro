#STAGE 1
# Use Maven with Java 17 to build the project
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory inside the container for the build process
WORKDIR /build

# Copy Maven config file first to cache dependencies
COPY pom.xml .

# Copy the application source code
COPY src ./src

# Build the application JAR (skipping tests for faster build)
RUN mvn clean package -DskipTests

#STAGE 2
# Use a smaller Java 17 image for running the app
FROM openjdk:17-jdk-slim

# Set working directory inside the container for runtime
WORKDIR /app

# Copy the built JAR from the builder stage to the runtime stage
COPY --from=builder /build/target/*.jar app.jar

# Run the application when the container starts
CMD ["java", "-jar", "app.jar"]
