package com.example.carmanager;

public class ICECar extends Vehicle {

    private double engineSize;
    private String range;

    public ICECar(String model, int year, String color, int passengers, double engineSize, String fuelType) {
        super(model, year, color, passengers);
        this.engineSize = engineSize;
        this.range = range;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public String getFuelType() {
        return range;
    }

    public void setFuelType(String fuelType) {
        this.range = fuelType;
    }
}
