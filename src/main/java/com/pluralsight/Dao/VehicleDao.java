package com.pluralsight.Dao;

import com.pluralsight.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private Connection connection;

    public VehicleDao(Connection connection) {
        this.connection = connection;
    }

    public List<Vehicle> getAllVehicles(int dealershipId) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE dealership_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, dealershipId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vehicle v = new Vehicle(
                        rs.getInt("vin"),
                        rs.getInt("dealership_id"),
                        rs.getInt("year"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("vehicle_type"),
                        rs.getString("color"),
                        rs.getInt("odometer"),
                        rs.getDouble("price")
                );
                vehicles.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
}