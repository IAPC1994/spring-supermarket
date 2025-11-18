# Supermarket API

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-green.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## 📝 Description

This is a REST API for managing supermarket branches, sales and products. It allows performing CRUD operations on all the entities, including:

- Create
- List all
- Get by ID
- Update 
- Delete 

Built with **Spring Boot**, **Spring Data JPA**, and using an in-memory H2 database for testing. It can also connect to any relational database.

---

## 🚀 Features

- **Spring Boot REST API** architecture
- DTOs to separate presentation and persistence layers
- Error handling with `ResponseStatusException`
- Input validation using `@Valid` and `jakarta.validation`
- Unit tests with **JUnit 5**, **Mockito**, and **AssertJ**
- Integration tests for repository using `@DataJpaTest`

---

## 🛠 Technologies

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (for tests)
- Maven / Gradle
- JUnit 5 + Mockito + AssertJ
- Lombok
