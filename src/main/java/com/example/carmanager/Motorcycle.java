package com.example.carmanager;

public class Motorcycle extends Vehicle {
    private String type;

    public Motorcycle(String model, int year, String color, int passengers, boolean status, int price, String type) {
        super(model, year, color, passengers, status, price);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
