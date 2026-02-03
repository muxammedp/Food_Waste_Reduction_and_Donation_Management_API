package model;

import java.time.LocalDate;

public class Donation {

    private int id;
    private FoodItem foodItem;
    private Organization organization;
    private LocalDate donationDate;

    public Donation(int id, FoodItem foodItem, Organization organization) {
        this.id = id;
        this.foodItem = foodItem;
        this.organization = organization;
        this.donationDate = LocalDate.now();
    }

    public FoodItem getFoodItem() { return foodItem; }
    public Organization getOrganization() { return organization; }
    public LocalDate getDonationDate() { return donationDate; }
}
