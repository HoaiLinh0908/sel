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
or run the Docker command
```
docker run --name jenkins --restart=on-failure --detach \
  --network jenkins \
  --volume jenkins-data:/var/jenkins_home \
  --volume jdk_data:/var/jenkins_home/tools/hudson.model.JDK \
  --volume /var/run/docker.sock:/var/run/docker.sock \
  --publish 8080:8080 --publish 50000:50000 myjenkins-blueocean:lts-jdk17
```

Open a browser and navigate to `localhost:8080`.

I bind-mount socket from the host demon (Docker out of Docker) because using dind (Docker in Docker) cause many network issues.