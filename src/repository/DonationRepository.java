package repository;

import utils.DatabaseConnection;
import exception.DatabaseOperationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DonationRepository {

    public void create(int foodId, int organizationId) {
        String sql = """
            INSERT INTO donations (food_item_id, organization_id)
            VALUES (?, ?)
        """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, foodId);
            ps.setInt(2, organizationId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error creating donation");
        }
    }
}
