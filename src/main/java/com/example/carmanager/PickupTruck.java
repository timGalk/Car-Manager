package com.example.carmanager;

public class PickupTruck extends Vehicle {

    private double payloadCapacity;

    public PickupTruck(String model, int year, String color, int passengers, boolean status, int price, double payloadCapacity) {
        super(model, year, color, passengers, status, price);
        this.payloadCapacity = payloadCapacity;
    }

    public double getPayloadCapacity() {
        return payloadCapacity;
    }

    public void setPayloadCapacity(double payloadCapacity) {
        this.payloadCapacity = payloadCapacity;
    }
}
