package repository;

import exception.DatabaseOperationException;
import model.Donation;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DonationRepository {

    public void create(Donation donation) {

        String sql = """
            INSERT INTO donations (food_item_id, organization_id)
            VALUES (?, ?)
        """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, donation.getFoodItem().getId());
            ps.setInt(2, donation.getOrganization().getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error creating donation: " + e.getMessage());
        }
    }
}
