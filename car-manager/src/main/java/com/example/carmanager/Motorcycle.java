package com.example.carmanager;

public class Motorcycle extends Vehicle {
    private String type;

    public Motorcycle(String model, int year, int passengers, String color, String type) {
        super(model, year, color, passengers);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
