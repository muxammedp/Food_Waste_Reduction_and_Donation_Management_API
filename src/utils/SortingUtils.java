package utils;

import model.FoodItem;
import java.util.Comparator;
import java.util.List;

public class SortingUtils {

    public static List<FoodItem> sortByExpiration(List<FoodItem> items) {
        return items.stream().sorted(Comparator.comparing(FoodItem::getExpirationDate)).toList();
    }
}
