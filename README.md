
# User Management REST API

A simple CRUD REST API built with Spring Boot, demonstrating a layered architecture (Controller → Service → Repository), DTO-based request validation, and centralized exception handling.

## Features

- Create, read, update, and delete users
- Request validation using Jakarta Bean Validation (`@NotBlank`, `@Email`)
- DTO pattern to separate API contracts from the database entity
- Centralized error handling via `@RestControllerAdvice`
- Proper HTTP status codes (`200`, `201`, `204`, `404`, `400`)
- Unit tests for the service layer using JUnit 5 and Mockito

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Jakarta Bean Validation
- JUnit 5 / Mockito
- Maven

## Project Structure

```
src/main/java/com/example/.../
├── Controller/
│   └── UserController.java
├── Service/
│   ├── UserService.java
│   └── UserServiceImpl.java
├── Repository/
│   └── UserRepository.java
├── Entity/
│   └── User.java
├── DTO/
│   ├── CreateUserRequest.java
│   └── UpdateUserRequest.java
└── Exception/
    ├── UserNotFoundException.java
    └── GlobalExceptionHandler.java
```

## API Endpoints

| Method | Endpoint          | Description              | Success Response |
|--------|-------------------|---------------------------|-------------------|
| GET    | `/api/users`      | Get all users              | `200 OK`          |
| GET    | `/api/users/{id}` | Get a user by id           | `200 OK` / `404 Not Found` |
| POST   | `/api/users`      | Create a new user           | `201 Created`     |
| PUT    | `/api/users/{id}` | Update an existing user    | `200 OK` / `404 Not Found` |
| DELETE | `/api/users/{id}` | Delete a user               | `204 No Content` / `404 Not Found` |

### Example Request — Create User

```http
POST /api/users
Content-Type: application/json

{
  "firstName": "Rommel",
  "lastName": "Macapobre",
  "email": "rommel@example.com"
}
```

### Example Error Response — Validation Failure

```json
{
  "firstName": "First name is required"
}
```

### Example Error Response — User Not Found

```json
{
  "error": "User with id 99 was not found"
}
```

## Running the Project

### Prerequisites
- Java 17+
- Maven

### Steps

```bash
# Clone the repository
git clone <your-repo-url>
cd <project-folder>

# Run the application
./mvnw spring-boot:run
```

The API will start on `http://localhost:8080`.

### Running Tests

```bash
./mvnw test
```

## What I Learned

This project was built to practice:
- Structuring a Spring Boot application with clean separation of concerns
- Using DTOs to control what data enters and leaves the API
- Writing a global exception handler instead of scattering error handling across controllers
- Unit testing a service layer with Mockito (mocking a repository, asserting on return values, and verifying void method calls)

## Possible Future Improvements

- Add a `UserResponse` DTO to fully decouple API responses from the entity
- Add pagination to the "get all users" endpoint
- Add MockMvc tests for the controller layer
- Handle duplicate email conflicts with a dedicated exception
