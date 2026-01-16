package model;

import interfaces.Donatable;
import interfaces.Trackable;
import exception.InvalidInputException;

import java.time.LocalDate;

public abstract class FoodItem implements Donatable, Trackable {

    protected int id;
    protected String name;
    protected LocalDate expirationDate;

    public FoodItem(int id, String name, LocalDate expirationDate) {
        if (name == null || name.isBlank()) {
            throw new InvalidInputException("Food name cannot be empty");
        }
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public abstract String getCategory();

    public String getDisplayInfo() {
        return id + " | " + name + " | expires: " + expirationDate;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
