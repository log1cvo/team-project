# Issue Manager
#양호신(20195#98)
엽준성(2021541)
허호(20225#58)

Issue Manager is an issue tracking and management system built with Spring Boot. This project provides functionalities to manage issues, projects, comments, and users.

## Features

- User authentication and registration
- Issue management (create, update, delete, search)
- Project management (create, update, delete)
- Comment management (create, update, delete)
- User management (create, update, delete)
- Statistics and reports

## Technologies Used

- Java 11
- Spring Boot 2.6.2
- Spring Security
- Spring Data JPA
- MySQL
- Lombok
- JUnit 5
- Mockito

## Prerequisites

- Java 11
- Maven
- MySQL

## Getting Started

### Database Setup

1. Create a MySQL database:

    ```sql
    CREATE DATABASE issue_manager_db;
    ```

2. Create a MySQL user and grant privileges:

    ```sql
    CREATE USER 'issue_user'@'localhost' IDENTIFIED BY 'issue_password';
    GRANT ALL PRIVILEGES ON issue_manager_db.* TO 'issue_user'@'localhost';
    FLUSH PRIVILEGES;
    ```

### Clone the Repository

```bash


   git clone https://github.com/log1cvo/team-project01_21.git
   cd issue-management-system
