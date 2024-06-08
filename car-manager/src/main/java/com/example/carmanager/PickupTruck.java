package com.example.carmanager;

public class PickupTruck extends Vehicle {

    private double payloadCapacity;

    public PickupTruck(String model, int year, String color, int passengers, double payloadCapacity, boolean bookingStatus) {
        super(model, year, color, passengers,bookingStatus);
        this.payloadCapacity = payloadCapacity;
    }

    public double getPayloadCapacity() {
        return payloadCapacity;
    }

    public void setPayloadCapacity(double payloadCapacity) {
        this.payloadCapacity = payloadCapacity;
    }
}
