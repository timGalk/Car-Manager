package com.example.carmanager;

public class HybridCar extends Vehicle {

    private double engineSize;
    private String fuelType;
    private double electricRange;

    public HybridCar(String model, int year, String color, int passengers, double engineSize, String fuelType, double electricRange, boolean status) {
        super(model, year, color,passengers,status);
        this.engineSize = engineSize;
        this.fuelType = fuelType;
        this.electricRange = electricRange;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getElectricRange() {
        return electricRange;
    }

    public void setElectricRange(double electricRange) {
        this.electricRange = electricRange;
    }
}
