package com.example.carmanager;

public class Car extends Vehicle {

    private String fuelType;

    public Car(String model, int year, String color, int passengers, String fuelType) {
        super(model, year, color, passengers);
        this.fuelType = fuelType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
