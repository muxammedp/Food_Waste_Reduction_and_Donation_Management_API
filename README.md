# Food Waste Reduction & Donation Management API

## A. Project Overview

This project is a Java-based multi-layer API designed to address a **global and socially relevant problem — food waste**. Large amounts of edible food are discarded daily due to poor tracking of expiration dates and the absence of structured donation workflows.

The system automates food item management, validates expiration dates, and ensures that **only safe, eligible food** is donated to charitable organizations. All operations are logged and handled through a clean, layered architecture following **SOLID principles**.

This project was initially implemented in Assignment 3 and **refactored and improved in Assignment 4** to apply SOLID architecture and advanced OOP features.

### Primary Users

* Food suppliers (stores, warehouses)
* Charitable organizations
* System administrators

---

## B. SOLID Architecture & OOP Design

### Abstract Base Class

**FoodItem (abstract)**

* Fields:

  * `id`
  * `name`
  * `expirationDate`

* Abstract methods:

  * `isEligibleForDonation()`
  * `getType()`

* Concrete method:

  * `isExpired()`

This design follows the **Open–Closed Principle (OCP)** — new food types can be added without modifying existing logic.

---

### Subclasses (LSP-compliant)

* **PerishableFood** — food with limited shelf life
* **NonPerishableFood** — food with long shelf life

Both subclasses:

* Override abstract methods
* Behave correctly when accessed via `FoodItem` reference
* Do not break substitutability (**LSP**)

**Polymorphism example:**

```java
FoodItem item = new PerishableFood(...);
item.isEligibleForDonation();
```

---

### Interfaces (ISP)

The project uses small, focused interfaces to avoid unnecessary dependencies:

* `CrudRepository<T>` — generic CRUD operations (Generics)
* Service interfaces (used for dependency inversion)

Interfaces demonstrate **Interface Segregation Principle (ISP)** and **Dependency Inversion Principle (DIP)**.

---

### Composition / Aggregation

* **Donation** → contains references to:

  * `FoodItem`
  * `Organization`

This relationship is implemented both:

* In OOP (object references)
* In the database (foreign keys)

---

## C. Advanced Java Features

### Generics

* Generic CRUD repository:

```java
CrudRepository<FoodItem>
```

This allows reuse of repository logic and improves type safety.

### Lambdas

Used for sorting food items by expiration date:

```java
items.stream()
     .sorted(Comparator.comparing(FoodItem::getExpirationDate))
     .toList();
```

### Reflection (RTTI)

A utility class demonstrates runtime inspection:

* Class name
* Fields
* Methods

This is shown during application execution and documented as part of advanced OOP usage.

---

## D. Database Description

### Database Technology

* PostgreSQL
* JDBC with PreparedStatements
* Auto-generated IDs using `SERIAL`

### Schema

```sql
CREATE TABLE organizations (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE food_items (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    expiration_date DATE,
    type VARCHAR(20)
        CHECK (type IN ('PERISHABLE', 'NON_PERISHABLE'))
);

CREATE TABLE donations (
    id SERIAL PRIMARY KEY,
    food_item_id INT,
    organization_id INT,

    CONSTRAINT fk_food
        FOREIGN KEY (food_item_id)
            REFERENCES food_items(id)
            ON DELETE CASCADE,

    CONSTRAINT fk_org
        FOREIGN KEY (organization_id)
            REFERENCES organizations(id)
            ON DELETE CASCADE
);
```

### Sample Data

```sql
INSERT INTO organizations (name) VALUES
('City Food Bank'),
('Helping Hands Charity');
```

---

## E. Layered Architecture

The application strictly follows:

**Controller → Service → Repository → Database**

* **Controller layer**: handles CLI input only
* **Service layer**: business rules, validation, exception handling
* **Repository layer**: JDBC logic only, no business rules

This separation ensures **SRP** and improves testability and maintainability.

---

## F. Execution Instructions

1. Install **Java 17+**
2. Create PostgreSQL database and run `schema.sql`
3. Configure DB credentials in `DatabaseConnection.java`
4. Compile the project:

```bash
javac -d out src/**/*.java
```

5. Run the application:

```bash
java -cp out Main
```

---

## G. Screenshots

Screenshots demonstrating:

* Successful CRUD operations
* Validation failures
* Donation logic
* Reflection utility output
* Sorted lists using lambdas

Location:

```
docs/screenshots/
```

---

## H. Reflection

### What Was Changed in Assignment 4

* Refactored entities to use an abstract base class
* Introduced SOLID-compliant layered architecture
* Added generics to repositories
* Moved validation and exceptions into the service layer
* Reworked database interaction for PostgreSQL
* Added reflection and lambda-based utilities

### What I Learned

* How SOLID principles apply in real-world Java projects
* Why business logic must not be placed in repositories
* How generics improve flexibility and reduce duplication
* How JDBC interacts with auto-generated database keys
* The value of clean architecture for long-term scalability

### Challenges Faced

* Correctly separating responsibilities between layers
* Refactoring without breaking existing functionality
* Adapting JDBC logic for PostgreSQL constraints

### Value of SOLID Architecture

* Clear separation of concerns
* Easier debugging and refactoring
* Better extensibility for future features
* More professional, maintainable codebase
