package model;

import java.time.LocalDate;

public class PerishableFood extends FoodItem {

    public PerishableFood(int id, String name, LocalDate expirationDate) {
        super(id, name, expirationDate);
    }

    @Override
    public boolean isEligibleForDonation() {
        return !isExpired();
    }

    @Override
    public FoodType getType() {
        return FoodType.PERISHABLE;
    }
}
