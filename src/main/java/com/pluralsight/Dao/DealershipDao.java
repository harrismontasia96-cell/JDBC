package com.pluralsight.Dao;

import com.pluralsight.Dealership;
import java.sql.*;


public class DealershipDao { private Connection connection;

    public DealershipDao(Connection connection) {
        this.connection = connection;
    }

    public Dealership getDealershipByName(String name) {
        String sql = "SELECT * FROM dealerships WHERE name = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Dealership d = new Dealership(
                        rs.getInt("dealership_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone")
                );
                return d;
            }
        } catch (SQLException e) { e.printStackTrace(); }

        return null;
    }
}

