package controller;

import service.DonationService;
import exception.InvalidInputException;
import exception.ResourceNotFoundException;

public class DonationController {

    private final DonationService donationService;

    public DonationController() {
        this.donationService = new DonationService();
    }

    public void donateFood(int foodItemId, int organizationId) {
        try {
            donationService.donateFood(foodItemId, organizationId);
            System.out.println("Donation processed successfully.");
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (ResourceNotFoundException e) {
            System.out.println("Not found: " + e.getMessage());

        }
    }
}
