# Online Learning Management System

A microservices-based Online Learning Management System developed using Java, Spring Boot, and Spring Cloud. The application is designed to manage users, courses, and enrollments through independent services.

## Technologies Used

- Java
- Spring Boot
- Spring Cloud
- Netflix Eureka Server and Eureka Client
- OpenFeign
- Spring Cloud API Gateway
- REST APIs
- MySQL
- Maven
- Git and GitHub

## Architecture

- **Eureka Server:** Registers and discovers microservices.
- **API Gateway:** Provides a single entry point for client requests and routes them to the appropriate services.
- **User Service:** Manages user-related operations.
- **Course Service:** Manages course-related operations.
- **Enrollment Service:** Manages course enrollment operations.
- **OpenFeign:** Enables communication between services through declarative HTTP clients.
- **MySQL:** Stores application data.

*Note: The services listed above should match the services implemented in this project.*

## Key Features

- RESTful API development
- Service discovery using Eureka
- Inter-service communication using OpenFeign
- Centralized request routing through API Gateway
- Persistent data storage using MySQL
- Independent microservice architecture

## Getting Started

1. Clone this repository.
2. Configure the database connection for each service.
3. Start the Eureka Server.
4. Start the required microservices.
5. Start the API Gateway.
6. Test the APIs using Postman.

## Configuration

Configure database credentials and service URLs using local configuration or environment variables. Do not commit passwords or other secrets.

## Future Enhancements

- Authentication and authorization using Spring Security
- JWT-based authentication
- Docker-based deployment
- API documentation using Swagger/OpenAPI

## Author

Java Backend Developer

## License

This project is licensed under the MIT License, if included in the repository.
