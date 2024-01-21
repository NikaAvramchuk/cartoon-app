# Cartoon App

## Overview
The Cartoon App is a Spring Boot application designed to process information about animated sitcoms. It uses the Rick and Morty API to fetch details about characters.

## Features
- **Get Character Information**: Retrieve information about animated characters.

## Technologies Used
- Java 17
- Spring Boot
- Swagger (for API documentation)
- Mockito, JUnit (for testing)

## Setup
1. **Clone the repository:**
    ```bash
    git clone <repository-url>
    cd cartoon-app
    ```

2. **Build the Docker image::**
    ```bash
    docker build -t cartoon-app .
    ```

3. **Run the Docker container:**
    ```bash
    docker run -p 8080:8080 cartoon-app
    ```

4. **Access the application:**
   Open your web browser and navigate to [http://localhost:8080](http://localhost:8080)

## API Documentation (Swagger)
- Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- Documentation: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs) http://localhost:8080/v3/api-docs 

## Usage
1. **Fetch Character Information:**
    - Endpoint: `/api/rickandmorty`
    - Method: `GET`
    - Query Parameter: `name` (Name of the character)

   Example:
   ```bash
   curl -X GET "http://localhost:8080/api/rickandmorty?name=Rick Sanchez" -H "accept: */*"
   
2. Enjoy and hire me for more!
