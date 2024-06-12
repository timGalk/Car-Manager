package com.example.carmanager;

public class Camper extends Vehicle {

    private int sleepingCapacity;

    public Camper(String model, int year, String color, int passengers, int sleepingCapacity) {
        super(model, year, color,passengers);
        this.sleepingCapacity = sleepingCapacity;
    }

    public int getSleepingCapacity() {
        return sleepingCapacity;
    }

    public void setSleepingCapacity(int sleepingCapacity) {
        this.sleepingCapacity = sleepingCapacity;
    }
}
