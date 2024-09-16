# Perseo Technical Test

## Table of Contents
- [Project Description](#project-description)
- [Technologies](#technologies)
- [Features](#features)
- [Installation](#installation)
- [Running the Project](#running-the-project)
- [API Endpoints](#api-endpoints)
- [License](#license)

## Project Description
Perseo Technical Test is a backend service for a technical job portal. The system allows users to manage their profiles, including their experience, education, and courses. The service provides endpoints to handle CRUD operations for user data while securing them with JWT-based authentication and role-based authorization.

This project uses Spring Boot for the backend and MySQL for database management.

## Technologies
- **Java 17**
- **Spring Boot 3.x**
- **Spring Security** (JWT-based authentication)
- **MySQL**
- **Hibernate** (JPA for database persistence)
- **Lombok**
- **Docker** (for containerization)
- **GitHub Actions** (CI/CD integration)

## Features
- **User Management**: Create, update, and delete user profiles.
- **Education Management**: Add and manage education records for users.
- **Experience Management**: Add and manage work experiences.
- **Course Management**: Manage user course enrollments.
- **JWT Authentication**: Secure API endpoints with JWT and role-based access control.

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/perseo-technical-test.git
   cd perseo-technical-test
