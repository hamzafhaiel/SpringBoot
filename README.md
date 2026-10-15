# AutoLoc — Vehicle Rental Management

AutoLoc is a Java/Spring Boot project for managing the core data of a vehicle-rental company. It is being developed in stages for course workshops. The current version focuses on the domain model, JPA persistence, and an initial vehicle repository.

## Project overview

The model represents the main concepts of a rental agency:

- **Agency and employees**: agencies and their staff, with employee roles (`AGENT`, `MANAGER`).
- **Vehicles**: registration number, make, model, category, daily rate, and availability/maintenance status.
- **Customers and reservations**: customer contact and licence information, plus reservation dates and status.
- **Contracts and payments**: contract signature and total, payment amount, date, and payment method.
- **Maintenance and equipment**: vehicle maintenance records and equipment labels.

The Java domain package contains entities for `Agence`, `Client`, `Contrat`, `Employe`, `Equipement`, `Maintenance`, `Paiement`, `Reservation`, and `Vehicule`, together with enums for vehicle category/status, reservation status, employee role, and payment method. The entity classes currently define fields and database mappings; entity relationships are planned for a later workshop stage.

## Technologies

- Java 17
- Spring Boot 4.1.1
- Maven (the Maven Wrapper is included)
- Spring Web
- Spring Data JPA / Hibernate
- MySQL Connector/J
- Lombok
- Jakarta Bean Validation
- Spring Boot DevTools

## Current implementation

- `AutoLocationHamzaFhaielApplication` is the Spring Boot entry point.
- `Vehicule` is mapped as a JPA entity, and `VehiculeRepository` extends `JpaRepository<Vehicule, Long>` for standard persistence operations.
- At startup, a `CommandLineRunner` inserts three example vehicles if the vehicle table is empty.
- There are **no service classes, REST controllers, or web pages yet**. Therefore, opening `/` can return HTTP 404; this does not by itself mean that the application failed to start.

## Requirements

- JDK 17
- IntelliJ IDEA with Maven support, or a terminal
- A running MySQL or MariaDB server. XAMPP can provide the local MySQL-compatible server.

## Database configuration

The default configuration uses the database `autoloc_db` on `localhost:3306`:

```text
URL:      jdbc:mysql://localhost:3306/autoloc_db?createDatabaseIfNotExist=true
Username: root
Password: (empty)
```

These default credentials match a typical local XAMPP setup. If your MySQL credentials differ, set the `DB_USERNAME` and `DB_PASSWORD` environment variables before starting the application. The JDBC URL requests database creation if it does not exist, provided the configured MySQL user has permission. You can also use the optional SQL script at `sql/autoloc_db.sql` to create the database manually.

Hibernate is configured with `spring.jpa.hibernate.ddl-auto=update`, so it creates or updates tables from the mapped entities when the application starts. This setting is intended for development and learning; review schema migration tools before using this configuration for production data.

## Run the application

1. Start the MySQL service in XAMPP (or start your MySQL/MariaDB server).
2. Open this repository in IntelliJ IDEA and allow Maven to load `pom.xml`.
3. Run `tn.esprit.spring.AutoLocationHamzaFhaielApplication` from IntelliJ.

Alternatively, run the Maven Wrapper from the project root:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

The server listens on **http://localhost:8081**. Since no controller currently handles `/`, there is not yet a browser page or REST endpoint to visit. Check the application logs in IntelliJ to confirm startup and database connection.

## Project structure

```text
src/main/java/tn/esprit/spring/
├── AutoLocationHamzaFhaielApplication.java  # Spring Boot entry point and sample-data setup
├── domain/                                  # JPA entities and enums
└── repository/                              # Spring Data repositories
src/main/resources/
├── application.properties                  # Application and database settings
└── application-dev.properties               # Development profile settings
sql/
└── autoloc_db.sql                           # Optional database creation script
```

## Next steps

The next development stages can add entity relationships, repositories for the other entities, service-layer business rules, and REST controllers for vehicle, customer, reservation, contract, and payment operations.
