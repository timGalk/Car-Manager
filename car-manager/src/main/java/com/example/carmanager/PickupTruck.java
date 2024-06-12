package com.example.carmanager;

public class PickupTruck extends Vehicle {

    private double payloadCapacity;

    public PickupTruck(String model, int year, String color, int passengers, double payloadCapacity) {
        super(model, year, color, passengers);
        this.payloadCapacity = payloadCapacity;
    }

    public double getPayloadCapacity() {
        return payloadCapacity;
    }

    public void setPayloadCapacity(double payloadCapacity) {
        this.payloadCapacity = payloadCapacity;
    }
}
