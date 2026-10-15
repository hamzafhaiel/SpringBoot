# AutoLoc API

Spring Boot and Spring Data JPA project for a vehicle-rental management system.

## Requirements
- Java 17 or newer
- MySQL or MariaDB running locally (XAMPP is suitable)
- IntelliJ IDEA with Maven support

## Run in IntelliJ IDEA
1. Open this repository and load `pom.xml` as a Maven project.
2. Start MySQL in XAMPP.
3. Run `tn.esprit.spring.AutoLocationHamzaFhaielApplication`.

The application connects to `autoloc_db` on `localhost:3306`. It uses `root` with an empty password by default, matching a typical local XAMPP setup. Set `DB_USERNAME` and `DB_PASSWORD` environment variables if your database credentials differ.

Hibernate uses `ddl-auto=update` for this development project and creates or updates tables from the JPA entities at startup. The `CommandLineRunner` inserts three sample vehicles if the `vehicule` table is empty.

## Current scope
The project contains the domain entities and enums, plus a Spring Data repository for vehicles. Associations, services, controllers, and REST endpoints are planned for later course ateliers, so the root URL currently has no page and may return 404.

`sql/autoloc_db.sql` contains an optional SQL snippet to create the database manually. The JDBC URL can also create it automatically when the MySQL user has permission.
