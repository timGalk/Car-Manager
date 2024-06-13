package com.example.carmanager;

public class Motorcycle extends Vehicle {
    private String motorcycleType;

    public Motorcycle(String model, int year, String color, int passengers, boolean status, int price, String motorcycleType) {
        super(model, year, color, passengers, status, price);
        this.motorcycleType = motorcycleType;
    }

    public String getMotorcycleType() {
        return motorcycleType;
    }

    public void setMotorcycleType(String motorcycleType) {
        this.motorcycleType = motorcycleType;
    }
}
