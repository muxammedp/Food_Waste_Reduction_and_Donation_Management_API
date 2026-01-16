# Food Waste Reduction & Donation Management API

## A. Project Overview

This project is a Java-based REST-like API designed to address the global problem of food waste. Large amounts of edible food are discarded daily due to poor tracking and management. This system automates the process of managing food items, checking expiration dates, and safely donating suitable food to charitable organizations.

The main goal of the system is to reduce food waste by ensuring that only valid, non-expired food items are donated, while maintaining a clear audit trail of all operations.

### Primary Users

* Food suppliers (stores, warehouses)
* Charitable organizations
* System administrators

---

## B. OOP Design Documentation

### Abstract Class

**FoodItem (abstract)**

* Fields: id, name, expirationDate
* Methods:

    * canBeDonated() (abstract)
    * getCategory() (abstract)
    * getDisplayInfo() (concrete)

### Subclasses

* **PerishableFood** – food with a limited shelf life
* **NonPerishableFood** – food with a long shelf life

Both subclasses override abstract methods and demonstrate polymorphism when accessed via a FoodItem reference.

### Interfaces

* **Donatable** – defines whether an item can be donated
* **Trackable** – provides status information about food items

### Composition / Aggregation

* **Donation** contains references to FoodItem and Organization

### Polymorphism Example

FoodItem item = new PerishableFood(...);
item.canBeDonated();

---

## C. Database Description

### Database Schema

The project uses PostgreSQL with JDBC.

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
```

### Sample INSERT Statements

```sql
INSERT INTO organizations (id, name) VALUES (1, 'Food Bank');
INSERT INTO food_items (name, expiration_date, type)
VALUES (1, 'Milk', '2026-01-20', 'Perishable');
```

---

## D. Controller / API Demonstration

The application demonstrates CRUD operations through a command-line interface:

* Creating food items
* Retrieving food items
* Donating food
* Handling invalid or expired food

All business logic is handled in the service layer.

---

## E. Instructions to Compile and Run

1. Ensure Java 17+ is installed
2. Create the SQLite database using schema.sql
3. Compile the project:

```
javac -d out src/**/*.java
```

4. Run the application:

```
java -cp out Main
```

---

## F. Screenshots

Screenshots demonstrating successful CRUD operations and exception handling are located in:

```
docs/screenshots/
```

---

## G. Reflection

### What I Learned

* Applying advanced OOP principles in real-world scenarios
* Designing multi-layer architectures
* Working with JDBC and PreparedStatements
* Implementing custom exception hierarchies

### Challenges Faced

* Handling database connections correctly
* Designing clean abstractions

### Benefits of JDBC and Multi-layer Design

* Clear separation of concerns
* Easier maintenance and scalability
* Improved code readability
