# Food Ordering System API

## Description

The Food Ordering System API is a RESTful web service designed to manage dishes, orders, users, and restaurants for a food ordering application. It provides endpoints for performing CRUD operations on various entities, allowing clients to interact with the system programmatically.

## Features

- **Dish Management**: CRUD operations for managing dishes.
- **Order Management**: CRUD operations for managing orders, including updating order details and status.
- **User Management**: CRUD operations for managing users, along with functionality to associate orders with users.
- **Restaurant Management**: CRUD operations for managing restaurants, including adding and removing dishes from the restaurant's menu.

## Technologies Used

- **Java**: Programming language used for developing the API.
- **Spring Boot**: Framework for building and deploying Java-based applications.
- **Spring Data JPA**: Library for simplifying database access and manipulation.
- **H2 Database (for development)**: Lightweight, in-memory database engine used for development and testing purposes.
- **PostgreSQL (for production)**: Open-source relational database management system used for production deployments.
- **Lombok**: Library to reduce boilerplate code in Java classes, such as getters, setters, and constructors.
- **MapStruct**: Code generation library for mapping between Java beans.
- **Swagger**: Tool for documenting and testing APIs.

## Installation

1. **Clone the Repository**
```bash
git clone https://github.com/kyzzhibek1/Spring-Project.git
```

2. **Navigate to the Project Directory**
```bash
- cd spring-project
```
3. **Build the Project**
```bash
- mvn clean package
```
4. **Run the Application**
```bass
 java -jar target/spring-project.jar
```
5. **Access the API**
- The API will be accessible at `http://localhost:8080`.

## Configuration

- **Database Configuration**: 
- For development and testing, the application uses an H2 in-memory database by default. 
- For production deployments, you can configure the application to use PostgreSQL by updating the database configuration properties in `application.properties`.

## Usage

- Detailed API documentation with examples of requests and responses can be found in the SwaggerUI link.
