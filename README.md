# AutoLoc — Vehicle Rental Management

AutoLoc is a Java and Spring Boot application for managing the core information of a vehicle-rental company. It is developed in stages as part of course workshops.

## Project overview

The project models the main concepts of a rental agency:

- **Agency and employees**: agency information and employee roles (`AGENT`, `MANAGER`).
- **Vehicles**: registration number, make, model, category, daily rate, and status.
- **Customers and reservations**: customer details, driving-licence information, reservation dates, and status.
- **Contracts and payments**: contract details and totals, payment dates, amounts, and methods.
- **Maintenance and equipment**: vehicle maintenance records and equipment labels.

The domain package contains entities for `Agence`, `Client`, `Contrat`, `Employe`, `Equipement`, `Maintenance`, `Paiement`, `Reservation`, and `Vehicule`. Enums represent vehicle categories and statuses, reservation status, employee roles, and payment methods. Entity relationships are planned for a later development stage.

## Technologies

- Java 17
- Spring Boot 4.1.1
- Maven and Maven Wrapper
- Spring Web
- Spring Data JPA and Hibernate
- SpringDoc OpenAPI and Swagger UI
- MySQL Connector/J
- Lombok
- Jakarta Bean Validation
- Spring Boot DevTools

## Current implementation

- `AutoLocationHamzaFhaielApplication` is the Spring Boot entry point.
- Each domain entity has a Spring Data repository, a concrete service class, and a REST controller with CRUD operations.
- Controllers follow the request flow: controller → service → repository → database.
- SpringDoc generates the OpenAPI description and Swagger UI from the REST controllers. `@Tag` and `@Operation` annotations describe API groups and operations.
- The project exposes REST APIs; it does not include browser pages.

## Requirements

- JDK 17
- IntelliJ IDEA with Maven support, or a terminal
- A running MySQL or MariaDB database server

## Database and persistence

The project is configured to use a MySQL database named `autoloc_db`. Database credentials can be supplied through the `DB_USERNAME` and `DB_PASSWORD` environment variables. The JDBC configuration can create the database if it does not exist, depending on the permissions of the configured database user. An optional SQL script is available at `sql/autoloc_db.sql`.

Hibernate is configured with `spring.jpa.hibernate.ddl-auto=update`, so it creates or updates tables from the mapped entities when the application starts. This is intended for development and learning.

## Run the application

1. Start the MySQL or MariaDB database server.
2. Open the project in IntelliJ IDEA and load `pom.xml` as a Maven project.
3. Run `tn.esprit.spring.AutoLocationHamzaFhaielApplication` from IntelliJ IDEA.

Alternatively, run the Maven Wrapper from the project root:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

## API documentation

After starting the application, open Swagger UI at `/swagger-ui/index.html` and the generated OpenAPI document at `/v3/api-docs`. If a servlet context path is configured, prepend it to either path. Swagger UI lists the CRUD operations for the project entities and lets you send requests to them.

## Project structure

```text
src/main/java/tn/esprit/spring/
├── AutoLocationHamzaFhaielApplication.java  # Spring Boot entry point
├── domain/                                  # JPA entities and enums
├── repository/                              # Spring Data repositories
├── service/                                 # Business and CRUD service methods
└── web/controller/                          # REST controllers and API error handling
src/main/resources/
├── application.properties                  # Application and database settings
└── application-dev.properties               # Development profile settings
sql/
└── autoloc_db.sql                           # Optional database creation script
```

## Current scope

The REST endpoints manage the entities and their fields. Entity relationships and related cascade/fetch behavior can be added as the corresponding JPA associations are introduced in the course.
