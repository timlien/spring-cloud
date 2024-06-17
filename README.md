# spring-cloud

This is a simple project to demonstrate how to use Spring Cloud to build a microservices architecture.

## Prerequisites

- Java 17
- Gradle
- Docker-Compose

## Running the project

To run the project, you need to start the services using Docker-Compose. To do that, run the following command:

```shell
cd docker-compose/dev
docker-compose up
```

This will start the following services:

- Eureka Server: http://localhost:8070
- Config Server: http://localhost:8071
- Gateway: http://localhost:8072
- Account Service: http://localhost:8080
- Loan Service: http://localhost:8090
- Card Service: http://localhost:9000

