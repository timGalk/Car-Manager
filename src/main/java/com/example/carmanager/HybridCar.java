package com.example.carmanager;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HybridCar extends Vehicle {

    private double engineSize;
    private String fuelType;
    private double electricRange;

    @JsonCreator
    public HybridCar(@JsonProperty("model") String model,
                     @JsonProperty("year") int year,
                     @JsonProperty("color") String color,
                     @JsonProperty("passengers") int passengers,
                     @JsonProperty("status") boolean status,
                     @JsonProperty("price") int price,
                     @JsonProperty("engineSize") double engineSize,
                     @JsonProperty("fuelType") String fuelType,
                     @JsonProperty("electricRange") double electricRange) {
        super(model, year, color, passengers, status, price);
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
