package model;

import java.time.LocalDate;

public class NonPerishableFood extends FoodItem {

    public NonPerishableFood(int id, String name, LocalDate expirationDate) {
        super(id, name, expirationDate);
    }

    @Override
    public boolean isEligibleForDonation() {
        return true;
    }

    @Override
    public FoodType getType() {
        return FoodType.NON_PERISHABLE;
    }
}
