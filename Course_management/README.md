
# Course Management API

Welcome to the Course Management API! This API allows you to manage students, courses, and enrollments for a university or educational platform. You can easily interact with students, courses, and enrollments via a set of RESTful endpoints.

## 🚀 Project Setup

### Requirements

Before you can run the project, make sure you have the following installed:

* **Java 11 or higher**
* **Maven** (or any Java build tool you prefer)
* **Spring Boot** (for automatic setup)
* **An IDE** (such as IntelliJ IDEA, Eclipse, or Visual Studio Code)

### How to Run the Project

1. **Clone the repository:**

   ```bash
   git clone https://github.com/ss31354/DistributedSystem.git
   ```

2. **Navigate to the project directory:**

   ```bash
   cd course-management-api
   ```

3. **Build and run the project using Maven:**

   ```bash
   mvn spring-boot:run
   ```

   Alternatively, if you prefer running a packaged JAR file:

   ```bash
   java -jar target/coursemanagement-api.jar
   ```

4. The application will start running on `http://localhost:8080`.

---

## 📚 API Overview

This API enables the following functionalities:

* **Manage Students**: Create, update, delete, and view students.
* **Manage Courses**: Create, update, delete, and view courses.
* **Enroll Students in Courses**: Add and remove students from courses.

### **Endpoints**

#### 1. **Students**

* **GET /students**: Retrieve a list of all students.
* **GET /students/{id}**: Retrieve details of a student by ID.
* **POST /students**: Create a new student.
* **PUT /students/{id}**: Update an existing student.
* **DELETE /students/{id}**: Delete a student.

#### 2. **Courses**

* **GET /courses**: Retrieve a list of all courses.
* **GET /courses/{id}**: Retrieve details of a course by ID.
* **POST /courses**: Create a new course.
* **PUT /courses/{id}**: Update an existing course.
* **DELETE /courses/{id}**: Delete a course.

#### 3. **Enrollments**

* **POST /students/{id}/courses/{courseId}**: Enroll a student in a course.
* **DELETE /students/{id}/courses/{courseId}**: Remove a student from a course.

---

## 🎓 Sample Requests and Responses

### **Create a New Student**

**POST /students**

**Request:**

```json
{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

**Response:**

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

---

### **Get All Courses**

**GET /courses**

**Response:**

```json
[
  {
    "id": 1,
    "title": "Java Programming",
    "description": "Learn Java from scratch.",
    "instructor": "Jane Smith"
  },
  {
    "id": 2,
    "title": "Spring Boot Basics",
    "description": "Learn Spring Boot framework.",
    "instructor": "John Doe"
  }
]
```

---

### **Enroll a Student in a Course**

**POST /students/1/courses/1**

**Response:**

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "courses": [
    {
      "id": 1,
      "title": "Java Programming",
      "description": "Learn Java from scratch.",
      "instructor": "Jane Smith"
    }
  ]
}
```

---

## 🛠️ Technology Stack

* **Java**: The primary programming language used for backend development.
* **Spring Boot**: Used for rapid development and setting up RESTful APIs.
* **JPA (Java Persistence API)**: Used to interact with a relational database (H2 for this project).
* **Maven**: Used for dependency management and project build.
* **Swagger UI** (Optional): For API documentation and testing (available at `/swagger-ui.html`).

---

## 🔄 Database Setup

This project uses **H2** in-memory database by default. You can use it for development purposes. For production, you can configure other databases (e.g., MySQL, PostgreSQL).

### Sample Data

The database is populated with sample data during application startup. You can modify the `data.sql` file for custom data seeding.

---

## 📑 OpenAPI Documentation Using Swagger UI

The API is documented using **Swagger UI**. To view the API documentation and test the endpoints interactively, visit:

```
http://localhost:8080/swagger-ui.html
```

Swagger provides an easy way to test the endpoints directly from the browser.
