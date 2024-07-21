# B2C API Integration

## Prerequisites
- Java 17
- Docker
- Docker Compose
- Apache Kafka
- MongoDB

## Setup
1. Clone the repository.
```bash
   git clone <repository-url>
   cd <repository-directory>
```
2. Run the Docker Compose command:
```bash
   docker-compose up
```
Once the Docker services are up and running, you can begin testing the endpoints.

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


