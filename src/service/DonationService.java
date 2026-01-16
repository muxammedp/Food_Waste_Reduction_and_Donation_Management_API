package service;

import model.FoodItem;
import repository.DonationRepository;
import repository.FoodItemRepository;
import exception.InvalidInputException;

public class DonationService {

    private final FoodItemRepository foodRepo = new FoodItemRepository();
    private final DonationRepository donationRepo = new DonationRepository();

    public void donateFood(int foodId, int organizationId) {
        FoodItem item = foodRepo.getById(foodId);

        if (!item.canBeDonated()) {
            throw new InvalidInputException("Food item is expired and cannot be donated");
        }

        donationRepo.create(foodId, organizationId);
        System.out.println("Donation successful: " + item.getDisplayInfo());
    }
}
