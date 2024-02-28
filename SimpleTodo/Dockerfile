# Use an OpenJDK runtime as the base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
# WORKDIR /app

# Copy the Spring Boot application JAR file into the container at the defined working directory
COPY target/spring-boot-docker.jar spring-boot-docker.jar

# Expose the port on which your Spring Boot application will run (adjust if necessary)
EXPOSE 8080

# Define the command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "/spring-boot-docker.jar"]
