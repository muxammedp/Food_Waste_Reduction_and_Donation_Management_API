package model;

import java.time.LocalDate;

public class NonPerishableFood extends FoodItem {

    public NonPerishableFood(int id, String name, LocalDate expirationDate) {
        super(id, name, expirationDate);
    }

    @Override
    public boolean canBeDonated() {
        return true;
    }

    @Override
    public String getStatus() {
        return "Safe for donation";
    }

    @Override
    public String getCategory() {
        return "NON_PERISHABLE";
    }

}
