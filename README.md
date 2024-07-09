balance-converter-api
========

![](logo.png)

# Intro
TODO...

# Prerequisites

1. Docker is installed
2. Minimum version of Java required is 17

# Execute

Can be run in 2 flavours - 
## Stand-alone Spring boot

### Steps

1. Clone repo
2. Run with IDE or on cli
    1. Import into IDE
        1. Within the IDE, run as Spring Boot App
    2. Run from command line
        1. Build project `mvn clean install -Ddockerfile.skip`
        2. From the */target* folder, execute `java -jar balance-converter-0.0.1-SNAPSHOT.jar`
    3. Just use the pre-built jar in the */test* folder in the repo using the same command in the previous step

## Docker

### Steps

1. Clone repo
2. Run maven install - `mvn clean install`. This will build the jar and also the docker image
3. From the command line run - `docker run -p 8080:8080 balance-converter:0.0.1-SNAPSHOT`
