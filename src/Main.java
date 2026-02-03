import model.*;
import repository.DonationRepository;
import repository.FoodItemRepository;
import service.DonationService;
import utils.ReflectionUtils;
import utils.SortingUtils;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        FoodItemRepository foodRepo = new FoodItemRepository();
        DonationRepository donationRepo = new DonationRepository();
        DonationService donationService =
                new DonationService(foodRepo, donationRepo);

        FoodItem milk = new PerishableFood(0, "Milk", LocalDate.now().plusDays(3));
        FoodItem rice = new NonPerishableFood(0, "Rice", LocalDate.now().plusMonths(6));

        foodRepo.create(milk);
        foodRepo.create(rice);

        Organization org = new Organization(1, "City Food Bank");
        donationService.donateFood(1, org);

        SortingUtils.sortByExpiration(foodRepo.getAll()).forEach(f -> System.out.println(f.getName()));

        ReflectionUtils.inspectClass(PerishableFood.class);
    }
}
