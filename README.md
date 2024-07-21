# B2C API Integration

## Prerequisites
- Java 17
- Maven
- Docker
- Docker Compose
- Apache Kafka
- MongoDB

> The above pre-requisites should all be installed before cloning the repository

## Setup
1. Clone the repository.
```bash
   git clone <repository-url>
   cd <repository-directory>
```
- Open using your preferred IDE and update the application.yml with your correct credentials for MongoDB in order to run.
2. Next go to the terminal run the command below in order to run tests, and package the application into a JAR file.
```bash
   mvn clean install
```
3. In order to the application use the command below:
```bash
   mvn spring-boot:run
```
Once the application is are up and running, you can begin testing the endpoints.

## API Endpoints
## Receive B2C Request
- Endpoint:
```bash
  POST http://localhost:8081/api/v1/b2c/request
```
- RequestBody
```bash
{
"id": "123e4567-e89b-12d3-a456-426614174000",
"amount": 100.0,
"mobileNumber": "2547123456789"
}
```
## Fetch Payment Status
- Endpoint:
```bash
 GET http://localhost:8081/api/v1/b2c/status/{id}
```

## Update Payment Status
- Endpoint:
```bash
  POST http://localhost:8081/api/v1/b2c/request
```
- RequestBody
```bash
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "status": "Completed",
  "ref": "MPESA123456"
}
```


