package com.pluralsight;

public class Vehicle {
    private int id;
    private int vin;
    private int dealershipId;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;

    // Constructor
    public Vehicle(int vin, int dealershipId, int year, String make, String model,
                   String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.dealershipId = dealershipId;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    // Getters
    public double getPrice() { return price; }
    public int getId() { return id; }
    public int getVin() { return vin; }
    public int getDealershipId() { return dealershipId; }
    public int getYear() { return year; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public String getVehicleType() { return vehicleType; }
    public String getColor() { return color; }
    public int getOdometer() { return odometer; }

    @Override
    public String toString() {
        return String.format("%d | %d | %s | %s | %s | %s | %d miles | $%.2f",
                vin, year, make, model, vehicleType, color, odometer, price);
    }
}