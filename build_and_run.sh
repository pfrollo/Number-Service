#!/bin/bash

# Exit immediately if a command exits with a non-zero status.
set -e

# Build the application
./mvnw clean package

# Build the Docker image
docker build -t numberservice .

# Run the Docker container
docker run numberservice
