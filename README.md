balance-converter-service
========

![](logo.png)

# Introduction
Service to convert a balance into a specific currency denomination. The balance will be converted into the lowest possible number of notes/coins.

# Features

* REST endpoint to convert a balance
* Can be execute as a stand-alone Spring boot app or run within a Docker container
* Validation to protect from erroneous requests
* Easily extendable by adding more currencies/denominations to the JSON data file
* 88.2% code coverage

# Limitations

* Data is read in from static JSON file
* No endpoint to dynamically add/update currencies
* Balance parameter value must be between 1 and 100000000

# Prerequisites

1. Docker is installed
2. Minimum version of Java required is 17

# Execute

Can be run in 2 modes - 

## Stand-alone Spring boot

### Steps

1. Clone repo
2. Run with IDE or on cli
    1. Import into IDE (eclipse)
        1. Within the IDE, run as Spring Boot App (requires spring plugin)
    2. Run from command line
        1. Build project `mvn clean install -Ddockerfile.skip`
        2. From the */target* folder, execute `java -jar balance-converter-0.0.1-SNAPSHOT.jar`
    3. Just use the pre-built jar in the */test* folder in the repo using the same command in the previous step

## Docker

### Steps

1. Clone repo
2. Run maven install `mvn clean install`. This will build the jar and also the docker image
3. From the command line run `docker run -p 8080:8080 balance-converter:0.0.1-SNAPSHOT`

# Usage

To get the denominations value for a particular balance, browse to - *http://localhost:8080/balance/currency/{code}/convert?balance=%d*

Replace *{code}* with the currency code. Currently only **USD** and **EUR** are supported.

Replace *%d* with the balance to convert.

# Docs

OpenAPI docs available at [localhost:8080/balance/docs](http://localhost:8080/balance/docs)

Swagger docs available at [localhost:8080/balance/docs/swagger-ui/index.html](http://localhost:8080/balance/docs/swagger-ui/index.html)

# Examples

## Get denominations for USD

GET localhost:8080/balance/currency/USD/convert?balance=287

[localhost:8080/balance/currency/USD/convert?balance=287](http://localhost:8080/balance/currency/USD/convert?balance=287)

### Result

2 Dollar, 3 Quarter, 1 Dime, 2 Penny coins

# Future

* Rate limiting to protect the service from being overloaded
* Currency denominations could/should be read from an external service
* Secure REST endpoint
* Versioned API
