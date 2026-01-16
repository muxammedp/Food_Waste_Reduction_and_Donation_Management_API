package model;

import exception.InvalidInputException;

public class Organization {

    private int id;
    private String name;

    public Organization(int id, String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidInputException("Organization name cannot be empty");
        }
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
