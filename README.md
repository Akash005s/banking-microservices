# 🏦 Banking Microservices

A **production-grade banking microservices system** built using **Spring Boot & Spring Cloud**, following real-world industry standards.  
This project demonstrates how modern banking applications are designed using **microservices architecture**, **JWT security**, **API Gateway**, and **service discovery**.

---

## 🚀 Project Overview

The Banking Microservices project is designed to simulate a real-world banking system with independent services for:

- Account Management
- Loan Processing
- Card Management
- Secure Authentication
- Centralized API Gateway
- Service Discovery

Each service is independently deployable, scalable, and follows clean architecture principles.

---

## 🧩 Microservices Overview

| Service Name | Description | Status |
|--------------|-------------|--------|
| **Account Service** | Manages customer accounts | ✅ Completed |
| **Loan Service** | Handles loan processing | ✅ Completed |
| **Card Service** | Manages debit/credit cards | ✅ Completed |
| **Auth Service** | JWT authentication | ⚙️ In Progress |
| **API Gateway** | Central entry point | ⚙️ In Progress |
| **Service Registry (Eureka)** | Service discovery | ⚙️ In Progress |
| **Config Server** | Centralized configuration | ⏳ Planned |

---

## 🧠 System Architecture

Client
|
▼
API Gateway
|

| | | |
Account Service Loan Service Card Service Auth Service


---

## 🛠️ Tech Stack

### Backend
- Java 8
- Spring Boot
- Spring Cloud
- Spring Data JPA
- Hibernate
- REST APIs

### Security
- JWT Authentication
- Role-based Authorization (Planned)

### Database
- MySQL

### Microservices Components
- Eureka Service Discovery
- API Gateway
- Feign Client (Planned)
- Circuit Breaker (Planned)

### DevOps & Tools
- Docker
- Docker Compose
- Swagger (OpenAPI)
- Postman
- Maven

---

## ✅ Implemented Features

### 🔹 Account Service
- Create account
- Update account
- Fetch account details
- Delete account
- Input validation
- Global exception handling
- Swagger documentation

### 🔹 Loan Service
- Create loan
- Fetch loan details
- Update loan
- Delete loan
- Clean layered architecture

### 🔹 Card Service
- Create card
- Fetch cards using mobile number
- Update card using cardId
- Delete card
- 12-digit card number generation
- DTO ↔ Entity mapping
- Validation & exception handling
- Swagger documentation

---

## 🔐 Authentication (In Progress)

- JWT token generation
- Request authentication filter
- Role-based authorization
- Secure endpoints

---

## 🌐 API Gateway (In Progress)

- Centralized routing
- Load balancing
- Authentication handling
- Request validation
- Rate limiting (planned)

---

## 📘 API Documentation

Swagger is enabled for all services.
http://localhost:{port}/swagger-ui.html


Each service includes:
- Proper request & response models
- Validation rules
- Error handling
- API descriptions

---

## 🐳 Docker Support (Planned)

- Dockerfile for each service
- Docker Compose for full setup
- MySQL container integration
- Service networking

---

## 📂 Project Structure (Example – Card Service)

card-service
│
├── controller
├── dto
│ ├── request
│ └── response
├── entity
├── mapper
├── repository
├── service
├── exception
├── config
└── CardServiceApplication.java


---

## 📌 Sample API – Create Card

### Endpoint
### Request Body
```json
{
  "mobileNumber": "9876543210",
  "cardType": "CREDIT",
  "totalLimit": 50000
}



