package model;

import java.time.LocalDate;

public class PerishableFood extends FoodItem {

    public PerishableFood(int id, String name, LocalDate expirationDate) {
        super(id, name, expirationDate);
    }

    @Override
    public boolean canBeDonated() {
        return expirationDate.isAfter(LocalDate.now());
    }

    @Override
    public String getStatus() {
        return canBeDonated() ? "Fresh" : "Expired";
    }

    @Override
    public String getCategory() {
        return "PERISHABLE";
    }
}
