# Dockerfile for Spring Boot App
# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot jar file to the container
COPY target/*.jar /app/app.jar


# Expose the application port
EXPOSE 8082

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]