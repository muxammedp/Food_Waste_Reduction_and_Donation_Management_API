package model;

import java.time.LocalDate;

public abstract class FoodItem {

    private int id;
    private String name;
    private LocalDate expirationDate;

    protected FoodItem(int id, String name, LocalDate expirationDate) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public abstract boolean isEligibleForDonation();
    public abstract FoodType getType();

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
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
