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
- MySQL Connector/J
- Lombok
- Jakarta Bean Validation
- Spring Boot DevTools

## Current implementation

- `AutoLocationHamzaFhaielApplication` is the Spring Boot entry point.
- `Vehicule` is mapped as a JPA entity.
- `VehiculeRepository` extends `JpaRepository<Vehicule, Long>` and provides standard vehicle persistence operations.
- `EmployeRepository` and the `IEmployeService` / `EmployeServiceImpl` pair provide employee creation, lookup, listing, update, and deletion through the service layer.
- `EmployeController` exposes employee operations through REST endpoints. `GET /employes/test` is a simple route for checking the controller, and `GET /employes` lists employees.
- At startup, a `CommandLineRunner` adds three sample vehicles if the vehicle table is empty.
- No browser pages are implemented yet. The application currently focuses on the domain model, persistence, and employee REST operations.

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

## Project structure

```text
src/main/java/tn/esprit/spring/
├── AutoLocationHamzaFhaielApplication.java  # Spring Boot entry point and sample-data setup
├── domain/                                  # JPA entities and enums
├── repository/                              # Spring Data repositories
├── service/                                 # Employee service interface and implementation
└── web/controller/                          # Employee REST controller
src/main/resources/
├── application.properties                  # Application and database settings
└── application-dev.properties               # Development profile settings
sql/
└── autoloc_db.sql                           # Optional database creation script
```

## Possible next steps

Future development stages can add entity relationships, repositories for the other entities, service-layer business rules, and REST controllers for vehicle, customer, reservation, contract, and payment operations.
