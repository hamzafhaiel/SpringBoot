# AutoLoc — Vehicle Rental Management

AutoLoc is a Java and Spring Boot application for managing the core information of a vehicle-rental company. It is developed in stages as part of course workshops.

## Project overview

The project models the main concepts of a rental agency:

- **Agency and employees**: agency information and employee roles (`AGENT`, `MANAGER`).
- **Vehicles**: registration number, make, model, category, daily rate, and status.
- **Customers and reservations**: customer details, driving-licence information, reservation dates, and status.
- **Contracts and payments**: contract details and totals, payment dates, amounts, and methods.
- **Maintenance and equipment**: vehicle maintenance records and equipment labels.

The domain package contains entities for `Agence`, `Client`, `Contrat`, `Employe`, `Equipement`, `Maintenance`, `Paiement`, `Reservation`, and `Vehicule`. Enums represent vehicle categories and statuses, reservation status, employee roles, and payment methods.

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
- Each domain entity has a Spring Data repository, a service interface, a concrete service implementation, and a REST controller with CRUD operations.
- Controllers depend on service interfaces; implementations contain the business logic.
- Interfaces and service classes share the `service` package. For example, `ClientService implements IClientService`; its methods use `@Override` to identify interface implementations. Spring injects the `@Service` implementation into the controller through its constructor.
- An employee can be assigned to an agency through a JPA many-to-one association.
- Vehicles can be assigned equipment, and maintenance records can be assigned to vehicles through JPA associations.
- Clients are saved first, then each reservation is linked to the client and saved in a loop. Reservations cascade all JPA operations to their contract, including deletion. Each contract can reference several existing payments.
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

Additional operations are available through these routes:

| Method | Path | Operation |
| --- | --- | --- |
| POST | `/clients/ajouterreservationclient` | Create a client with a `reservations` array in the JSON body. |
| POST | `/reservations/ajouterreservationcontrat` | Create a reservation with a nested `contrat` object. |
| PUT | `/employes/affecterAgence/{idAgence}/{idEmploye}` | Assign an existing employee to an existing agency. |
| PUT | `/vehicules/affecterEquipement/{idv}/{ideq}` | Add existing equipment to an existing vehicle. |
| PUT | `/maintenances/affecterMaintenanceVehicule/{idm}/{idV}` | Assign an existing maintenance record to an existing vehicle. |

Omit generated IDs when creating new objects. Assignment routes use existing IDs and require no request body. The two additional POST routes also accept their original camel-case paths (`ajouterReservationClient` and `ajouterReservationContrat`).

## Verification

Run `./mvnw verify`, or `.\mvnw.cmd verify` in Windows PowerShell. The integration test uses a temporary H2 database to check Swagger, CRUD operations, associations, and contract deletion through the reservation cascade. It does not use the application’s MySQL database.

## Project structure

```text
src/main/java/tn/esprit/spring/
├── AutoLocationHamzaFhaielApplication.java  # Spring Boot entry point
├── domain/                                  # JPA entities and enums
├── repository/                              # Spring Data repositories
├── service/                                 # Service interfaces and their @Service implementations
└── web/controller/                          # REST controllers and API error handling
src/main/resources/
├── application.properties                  # Application and database settings
└── application-dev.properties               # Development profile settings
sql/
└── autoloc_db.sql                           # Optional database creation script
```

## Current scope

The REST endpoints manage the entities and their fields. Employee-to-agency, vehicle-to-equipment, maintenance-to-vehicle, client-to-reservations, reservation-to-contract, and contract-to-payments associations are implemented. Other entity relationships can be added as they are introduced in the course.
