package service;

import exception.InvalidInputException;
import model.Donation;
import model.FoodItem;
import model.Organization;
import repository.DonationRepository;
import repository.interfaces.CrudRepository;

public class DonationService {

    private final CrudRepository<FoodItem> foodRepository;
    private final DonationRepository donationRepository;

    public DonationService(CrudRepository<FoodItem> foodRepository,
                           DonationRepository donationRepository) {
        this.foodRepository = foodRepository;
        this.donationRepository = donationRepository;
    }

    public void donateFood(int foodId, Organization organization) {

        FoodItem item = foodRepository.getById(foodId);

        if (!item.isEligibleForDonation()) {
            throw new InvalidInputException("Food is expired and cannot be donated");
        }

        Donation donation = new Donation(0, item, organization);
        donationRepository.create(donation);

        System.out.println("Donation successful: " + item.getName()
                + " → " + organization.getName());
    }
}
