# Start Jenkins with Docker

## Introduction

To start a Jenkins instance inside a Docker container:

1. Build a custom Jenkins image by executing the Dockerfile
```
docker build -t myjenkins-blueocean:lts-jdk17 .
```

2. Run a docker:dind Docker image and your custom Jenkins image
by executing the docker-compose.yml
```
docker compose up -d
```
Open a browser and navigate to `localhost:8080`