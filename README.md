# 🔐 Spring Security Project with JWT (Asymmetric)

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.5-green?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17.4-blue?style=for-the-badge&logo=postgresql)
![JWT](https://img.shields.io/badge/JWT-JSON_Web_Token-purple?style=for-the-badge&logo=jsonwebtokens)
![Status](https://img.shields.io/badge/Status-Development-yellow?style=for-the-badge)

> **Goal:**  
> A hands-on learning project to master **Spring Security** with **JWT (Asymmetric RSA keys)**, following best practices and preparing the codebase for **production-level deployment**.

---

## 📚 Table of Contents
- [About](#-about)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [Environment Variables](#-environment-variables)
- [API Documentation](#-api-documentation)
- [Production Readiness](#-production-readiness)
- [Future Plans](#-future-plans)

---

## 📝 About
This project is built to **learn, explore, and implement security** in modern web applications using:
- **Spring Boot 3.x**
- **Spring Security**
- **JWT with asymmetric key pair** (`RS256` for signing & verification)

The final goal is to have a **production-ready authentication & authorization system**, including:
- Secure handling of environment variables
- Clean and scalable architecture
- REST API documentation with Swagger
- Database integration with PostgreSQL
- CI/CD pipelines and deployment best practices

---

## ✨ Features
✅ **User Registration & Login** – Secure endpoints for sign-up and sign-in.  
✅ **Asymmetric JWT Authentication** – Private key to sign tokens, public key to verify them.  
✅ **Role-Based Authorization** – Enforce roles like `ADMIN` and `USER`.  
✅ **Password Hashing** – BCrypt password encoder.  
✅ **Token Refresh Mechanism** – Renew expired access tokens securely.  
✅ **Swagger API Documentation** – Clear API interface for developers.  
✅ **PostgreSQL Integration** – Persistent data layer with Spring Data JPA.  
✅ **MapStruct Mappers** – Clean and decoupled DTO mapping.  
✅ **Environment Variables** – Secure `.env` handling for secrets.  

---

## 🛠 Tech Stack
| **Technology**    | **Usage**                  |
|--------------------|----------------------------|
| **Java 17**       | Core language              |
| **Spring Boot 3.5.5** | Application framework     |
| **Spring Security** | Authentication & Authorization |
| **JWT (RS256)**    | Token-based authentication |
| **PostgreSQL**     | Database                   |
| **Swagger / OpenAPI** | API documentation        |
| **MapStruct**      | DTO mapping                |
| **Lombok**         | Boilerplate code reduction |

---

## 📂 Project Structure
