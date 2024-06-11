package com.example.carmanager;

public class BEVCar extends Vehicle {

    private double batteryCapacity;
    private double range;

    public BEVCar(String model, int year, String color, int passengers, double batteryCapacity, double range, boolean status) {
        super(model, year, color, passengers,status);
        this.batteryCapacity = batteryCapacity;
        this.range = range;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }
}
