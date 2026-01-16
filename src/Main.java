import controller.DonationController;
import model.*;
import repository.FoodItemRepository;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        FoodItemRepository foodRepo = new FoodItemRepository();
        DonationController controller = new DonationController();

        foodRepo.create(new PerishableFood(1, "Milk", LocalDate.now().plusDays(3)));
        foodRepo.create(new PerishableFood(2, "Bread", LocalDate.now().plusDays(1)));
        foodRepo.create(new PerishableFood(3, "Yogurt", LocalDate.now().plusDays(5)));
        foodRepo.create(new PerishableFood(4, "Cheese", LocalDate.now().plusDays(10)));
        foodRepo.create(new PerishableFood(5, "Chicken", LocalDate.now().plusDays(2)));
        foodRepo.create(new PerishableFood(6, "Apples", LocalDate.now().plusDays(7)));
        foodRepo.create(new PerishableFood(7, "Tomatoes", LocalDate.now().plusDays(4)));

        foodRepo.create(new NonPerishableFood(8, "Rice", LocalDate.now().plusMonths(12)));
        foodRepo.create(new NonPerishableFood(9, "Pasta", LocalDate.now().plusMonths(18)));
        foodRepo.create(new NonPerishableFood(10, "Canned Beans", LocalDate.now().plusYears(2)));
        foodRepo.create(new NonPerishableFood(11, "Flour", LocalDate.now().plusMonths(10)));
        foodRepo.create(new NonPerishableFood(12, "Sugar", LocalDate.now().plusYears(3)));
        foodRepo.create(new NonPerishableFood(13, "Salt", LocalDate.now().plusYears(5)));
        foodRepo.create(new NonPerishableFood(14, "Cooking Oil", LocalDate.now().plusYears(1)));
        System.out.println("Food items successfully added.");

        controller.donateFood(1, 1);   // Milk
        controller.donateFood(3, 1);   // Yogurt
        controller.donateFood(8, 2);   // Rice

        System.out.println(foodRepo.getById(1).getDisplayInfo() + " | Status: " + foodRepo.getById(1).getStatus());
        System.out.println(foodRepo.getById(5).getDisplayInfo() + " | Status: " + foodRepo.getById(5).getStatus());
        System.out.println(foodRepo.getById(10).getDisplayInfo() + " | Status: " + foodRepo.getById(10).getStatus());

        System.out.println("\n--- All Food Items in Database ---");
        for (FoodItem item : foodRepo.getAll()) {
            System.out.println(item.getDisplayInfo() + " | " + item.getCategory() + " | " + item.getStatus());
        }
        controller.donateFood(1, 1);
    }
}
