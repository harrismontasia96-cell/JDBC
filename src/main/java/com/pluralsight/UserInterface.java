package com.pluralsight;

import java.util.List;
import java.util.Scanner;
import com.pluralsight.Dao.DealershipDao;
import com.pluralsight.Dao.VehicleDao;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        loadDealership();
        if (dealership == null) {
            System.out.println("Cannot continue without a valid dealership.");
            return;
        }

        int choice;
        do {
            System.out.println("\n--- Dealership Menu (" + dealership.getName() + ") ---");
            System.out.println("1 - Find vehicles by price range");
            System.out.println("2 - Find vehicles by make/model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("6 - Find vehicles by type");
            System.out.println("7 - List ALL vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("10 - Switch Dealership");
            System.out.println("99 - Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            processUserInput(choice);

        } while (choice != 99);

        System.out.println("Application closed.");
    }

    private void loadDealership() {
        System.out.print("Enter dealership name: ");
        String name = scanner.nextLine();


        DealershipDao dealershipDao = new DealershipDao(DbConnection.getConnection());
        dealership = dealershipDao.getDealershipByName(name);

        if (dealership == null) {
            System.out.println("Dealership not found in database!");
            return;
        }


        VehicleDao vehicleDao = new VehicleDao(DbConnection.getConnection());
        List<Vehicle> vehicles = vehicleDao.getAllVehicles(dealership.getId());
        dealership.setAllVehicles(vehicles);

        System.out.println("Loaded dealership: " + dealership.getName());
    }

    private void processUserInput(int choice) {
        switch (choice) {
            case 7:
                displayVehicles(dealership.getAllVehicles());
                break;

            case 10:
                loadDealership();
                break;

            case 99:
                System.out.println("Goodbye!");
                break;

            default:
                System.out.println("Option not implemented yet.");
        }
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }
}