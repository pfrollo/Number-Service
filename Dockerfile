# base image
FROM openjdk:24-slim

WORKDIR /app

COPY target/numberservice-0.0.1-SNAPSHOT.jar app.jar

# Define environment variables with default values
ENV MIN_RANGE=0
ENV MAX_RANGE=100
ENV NUM_SETS=2
ENV SET_SIZE=10

# Pass environment variables as system properties to the Java application
ENTRYPOINT ["java", \
            "-jar", "app.jar"]
