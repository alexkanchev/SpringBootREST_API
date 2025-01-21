# Course Registration API

A Spring Boot REST API for managing student registrations, courses, grades, and class enrollments.

---

## 🚀 Features
- Manage **students**: create, update, view, and delete.
- Handle **classes**: add, update, deactivate, and retrieve.
- Track **grades**: manage student grades with ease.
- Organize **class enrollments**: create and update enrollments seamlessly.

---

## 🛠️ Technologies
- **Java 8**
- **Spring Boot 2.1.6**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

---

## 📋 Prerequisites
- **JDK 1.8**
- **Maven 3.x**
- **PostgreSQL** installed and running.

---

## ⚙️ Configuration

Update the database configuration in `application.properties`:

``properties
spring.datasource.url=jdbc:postgresql://localhost:XXXX/Register_db
spring.datasource.username=XXXX
spring.datasource.password=XXXX

## 📂 API Endpoints

### 📚 Students
- **GET** `/student/id/{id}`  
  Retrieve student details by their unique ID.  
- **POST** `/student/email`  
  Retrieve student details using their email address.  
- **GET** `/student/view/{id}`  
  Fetch a detailed view of a student by ID.  
- **POST** `/student/create`  
  Add a new student to the system.  
- **PUT** `/student/update`  
  Update an existing student's details.  
- **DELETE** `/student/delete/{id}`  
  Remove a student from the system using their ID.  

### 🏫 Classes
- **POST** `/clas/create`  
  Create a new class in the system.  
- **PUT** `/clas/update`  
  Update details of an existing class.  
- **DELETE** `/clas/delete/{id}`  
  Deactivate a class by its ID.  
- **GET** `/clas/id/{id}`  
  Retrieve class details using its unique ID.  

### 🎓 Grades
- **GET** `/grade/studentId/{studentId}`  
  Retrieve grades for a specific student.  
- **POST** `/grade/create`  
  Add a new grade entry.  
- **PUT** `/grade/update`  
  Update an existing grade record.  
- **DELETE** `/grade/delete/{id}`  
  Remove a grade entry by its ID.  

### 👥 Class Enrollments
- **POST** `/generation/create`  
  Add a new class enrollment.  
- **PUT** `/generation/update`  
  Update details of an existing enrollment.  

---

## ▶️ Running the Application

To start the application, use the following Maven command:  

```bash
mvn spring-boot:run

The application will run on port 8089.
