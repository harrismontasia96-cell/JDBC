package com.pluralsight;

public class SalesContract extends Contract {

    private int id;
    private int vehicleId;
    private String customerName;
    private String customerAddress;
    private double salesTax;
    private double processingFee;
    private double totalPrice;
    private String dateOfSale;


    private static final double SALES_TAX_RATE = 0.05;
    private static final double RECORDING_FEE = 100;
    private static final double PROCESSING_FEE_UNDER_10K = 295;
    private static final double PROCESSING_FEE_OVER_10K = 495;

    private boolean finance;
    private Vehicle vehicleSold;

    public SalesContract(String date, String customerName, String customerEmail,
                         Vehicle vehicleSold, boolean finance) {
        super(
                "SALE",
                date,
                customerName,
                customerEmail,
                vehicleSold.getVin(),
                vehicleSold.getYear(),
                vehicleSold.getMake(),
                vehicleSold.getModel(),
                vehicleSold.getVehicleType(),
                vehicleSold.getColor(),
                vehicleSold.getOdometer(),
                vehicleSold.getMake() + " " + vehicleSold.getModel()
        );
        this.finance = finance;
        this.vehicleSold = vehicleSold;
    }

    @Override
    public double getTotalPrice() {
        double price = vehicleSold.getPrice();
        double salesTax = price * SALES_TAX_RATE;
        double processingFee = price < 10000 ? PROCESSING_FEE_UNDER_10K : PROCESSING_FEE_OVER_10K;

        return price + salesTax + RECORDING_FEE + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) {
            return 0.0;
        }

        double principal = getTotalPrice();
        double rate;
        int months;

        if (vehicleSold.getPrice() >= 10000) {
            rate = 0.0425;
            months = 48;
        } else {
            rate = 0.0525;
            months = 24;
        }

        double monthlyRate = rate / 12.0;
        return (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public String getDateOfSale() {
        return dateOfSale;
    }
}
