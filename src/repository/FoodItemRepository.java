package repository;

import exception.DatabaseOperationException;
import exception.ResourceNotFoundException;
import model.*;
import repository.interfaces.CrudRepository;
import utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FoodItemRepository implements CrudRepository<FoodItem> {

    @Override
    public void create(FoodItem item) {

        String sql = """
            INSERT INTO food_items (name, expiration_date, type)
            VALUES (?, ?, ?)
        """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps =
                     c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, item.getName());
            ps.setDate(2, Date.valueOf(item.getExpirationDate()));
            ps.setString(3, item.getType().name());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                System.out.println("FoodItem created with id = " + keys.getInt(1));
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error creating food item: " + e.getMessage());
        }
    }

    @Override
    public FoodItem getById(int id) {

        String sql = "SELECT * FROM food_items WHERE id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new ResourceNotFoundException("FoodItem not found");
            }

            FoodType type = FoodType.valueOf(rs.getString("type"));
            LocalDate date = rs.getDate("expiration_date").toLocalDate();
            int dbId = rs.getInt("id");
            String name = rs.getString("name");

            return type == FoodType.PERISHABLE
                    ? new PerishableFood(dbId, name, date)
                    : new NonPerishableFood(dbId, name, date);

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching food item: " + e.getMessage());
        }
    }

    @Override
    public List<FoodItem> getAll() {

        List<FoodItem> list = new ArrayList<>();
        String sql = "SELECT * FROM food_items";

        try (Connection c = DatabaseConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                FoodType type = FoodType.valueOf(rs.getString("type"));
                LocalDate date = rs.getDate("expiration_date").toLocalDate();
                int id = rs.getInt("id");
                String name = rs.getString("name");

                FoodItem item = type == FoodType.PERISHABLE
                        ? new PerishableFood(id, name, date)
                        : new NonPerishableFood(id, name, date);

                list.add(item);
            }

            return list;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching food list: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM food_items WHERE id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error deleting food item: " + e.getMessage());
        }
    }
}
