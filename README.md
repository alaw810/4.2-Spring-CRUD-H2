# 🍎 REST API with Spring Boot and H2

## 📄 Description

This project consists of developing a **REST API** using **Spring Boot** to manage a fruit store’s stock.  
It allows users to **create, read, update, and delete** fruits, each of which has a name and a weight in kilograms.

The data is persisted in an **H2 in-memory database**, and all operations are implemented following the **TDD approach**.  
A **Global Exception Handler** ensures consistent error responses, and the project includes a **multi-stage Dockerfile** for production deployment.

---

## 💻 Technologies Used

- **Java 21**
- **Spring Boot 3.x**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Jakarta Bean Validation**
- **Lombok**
- **JUnit 5**
- **Spring MockMvc**
- **Docker (multi-stage build)**

---

## 📋 Requirements

To run the project locally, make sure you have:

| Software | Version |
|-----------|----------|
| Java | 21 (LTS) |
| Maven | 3.9.x |
| Spring Boot | 3.x (latest stable) |
| Docker | 24+ (optional, for containerization) |

---

## 🛠️ Installation

1. Clone this repository:
   ```bash
   git clone https://github.com/alaw810/4.2-Spring-CRUD-H2.git
   cd 4.2-Spring-CRUD-H2
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application locally:
   ```bash
   mvn spring-boot:run
   ```

4. The API will be available at:
   ```
   http://localhost:9000
   ```

---

## ▶️ Execution

### 🌐 Endpoints Overview

| Method | Endpoint | Description | Status Code |
|--------|-----------|--------------|--------------|
| `POST` | `/fruits` | Create a new fruit | `201 Created` |
| `GET` | `/fruits` | Retrieve all fruits | `200 OK` |
| `GET` | `/fruits/{id}` | Retrieve a fruit by ID | `200 OK / 404 Not Found` |
| `PUT` | `/fruits/{id}` | Update an existing fruit | `200 OK / 400 Bad Request / 404 Not Found` |
| `DELETE` | `/fruits/{id}` | Delete a fruit by ID | `204 No Content / 404 Not Found` |

### 🧪 Example Request (POST)
```json
{
  "name": "Apple",
  "weightInKilos": 5
}
```

### 🧾 Example Response
```json
{
  "id": 1,
  "name": "Apple",
  "weightInKilos": 5
}
```

### 🧪 Testing
All endpoints are tested using **JUnit 5** and **MockMvc**, following the **Test-Driven Development (TDD)** approach:
```bash
mvn test
```

---

## 🌐 Deployment

### 🐳 Run with Docker (multi-stage build)

1. Build the Docker image:
   ```bash
   docker build -t fruit-api-h2 .
   ```

2. Run the container:
   ```bash
   docker run -p 9000:9000 fruit-api-h2
   ```

3. The API will be accessible at:
   ```
   http://localhost:9000
   ```

4. Access the H2 console at:
   ```
   http://localhost:9000/h2-console
   ```
    - **JDBC URL:** `jdbc:h2:mem:fruitdb`
    - **User:** `sa`
    - **Password:** *(leave empty)*

---

### 🧱 Build and run the JAR locally (without Docker)

1. Package the application:
   ```bash
   mvn clean package -DskipTests
   ```

2. Run the JAR file:
   ```bash
   java -jar target/fruit-api-h2-0.0.1-SNAPSHOT.jar
   ```

3. The application will start at:
   ```
   http://localhost:9000
   ```

---

## 🧠 Author

**Adrià Lorente**  
📍 IT Academy – Java Back-End Development  
📚 Exercise: *S04.T02.N01 – API REST with Spring Boot (Level 1 - H2)*  
