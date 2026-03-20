# Library Management System

A digital library management system developed for the **Talento Digital Bootcamp**. The application allows managing books, students and librarians, including book loans and user administration.

## Tech Stack

- Java 21
- Jakarta EE 10 (Servlets, JSP, JSTL)
- Apache Tomcat 10.1
- MySQL 8
- Maven 3

## Architecture

The project follows **Screaming Architecture** principles with a domain-driven structure:

```
src/main/java/com/untec/
├── shared/                  ← cross-cutting concerns (DB connection)
├── user/
│   ├── domain/              ← entities, value objects, repository interfaces
│   ├── application/         ← services, DTOs, factories
│   ├── infrastructure/      ← repository implementations (JDBC)
│   └── web/                 ← servlets
└── book/
    ├── domain/
    ├── application/
    ├── infrastructure/
    └── web/
```

## Requirements

- Java 21
- Maven 3.8+
- MySQL 8
- Apache Tomcat 10.1

## Database Setup

1. Create a MySQL database:
```sql
CREATE DATABASE library;
```

2. Run the schema script:
```bash
mysql -u root -p library < sql/library.sql
```

3. Configure the database credentials in `src/main/resources/db.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/library
db.username=<db_user>
db.password=<db_password>
```

## Running the Application

**Using Maven with Cargo plugin:**
```bash
mvn cargo:run
```

**Using Eclipse:**
1. Right-click the project → Run As → Run on Server
2. Select Apache Tomcat 10.1
3. Click Finish

The application will be available at:
```
http://localhost:8080/library
```

## Generating Documentation

Generate Javadoc with Maven:
```bash
mvn javadoc:javadoc
```

The documentation will be generated at `target/site/apidocs/index.html`.

## User Roles

| Role | Access Level | Permissions |
|---|---|---|
| Student | - | Browse books, manage own loans (max 5) |
| Librarian | 1 | Manage students, books and loans |
| Librarian | 2 | All level 1 permissions + manage librarians |

## License

This project was developed for educational purposes as part of the Talento Digital Bootcamp.
