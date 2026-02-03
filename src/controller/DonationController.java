package controller;

import service.DonationService;
import exception.InvalidInputException;
import exception.ResourceNotFoundException;

public class DonationController {


    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

}

