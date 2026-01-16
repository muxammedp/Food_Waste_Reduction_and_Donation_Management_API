package repository;

import model.*;
import utils.DatabaseConnection;
import exception.DatabaseOperationException;
import exception.ResourceNotFoundException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FoodItemRepository {

    public void create(FoodItem item) {
        String sql = """
            INSERT INTO food_items (name, expiration_date, type)
            VALUES (?, ?, ?)
        """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, item.getName());
            ps.setDate(2, Date.valueOf(item.getExpirationDate()));
            ps.setString(3, item.getCategory());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error creating food item");
        }
    }

    public FoodItem getById(int id) {
        String sql = "SELECT * FROM food_items WHERE id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new ResourceNotFoundException("Food item not found");
            }

            String type = rs.getString("type");
            LocalDate date = rs.getDate("expiration_date").toLocalDate();

            if (type.equals("Perishable")) {
                return new PerishableFood(id, rs.getString("name"), date);
            } else {
                return new NonPerishableFood(id, rs.getString("name"), date);
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching food item");
        }
    }

    public List<FoodItem> getAll() {
        List<FoodItem> items = new ArrayList<>();
        String sql = "SELECT * FROM food_items";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate date = rs.getDate("expiration_date").toLocalDate();
                String type = rs.getString("type");

                FoodItem item = type.equals("Perishable")
                        ? new PerishableFood(id, name, date)
                        : new NonPerishableFood(id, name, date);

                items.add(item);
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching food items");
        }

        return items;
    }

    public void delete(int id) {
        String sql = "DELETE FROM food_items WHERE id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error deleting food item");
        }
    }
}
