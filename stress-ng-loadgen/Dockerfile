# Use a base image with both stress-ng and a lightweight web server
FROM ubuntu:latest

# Install necessary packages: stress-ng, curl, and a simple web server (like python3 http.server)
RUN apt-get update && apt-get install -y \
    stress-ng \
    python3 \
    curl \
    && apt-get clean

# Create a directory for the web server
WORKDIR /app
RUN echo "Welcome to the Stress Test Server" > index.html

# Start a simple HTTP server and stress-ng in the background
# This script runs both stress-ng and the webserver
CMD bash -c "python3 -m http.server 8080 & stress-ng -c 10 -l 100 -v "


