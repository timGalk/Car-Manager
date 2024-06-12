package com.example.carmanager;

public class Camper extends Vehicle {

    private int sleepingCapacity;

    public Camper(String model, int year, String color, int passengers, boolean status, int price, int sleepingCapacity) {
        super(model, year, color, passengers, status, price);
        this.sleepingCapacity = sleepingCapacity;
    }


    public int getSleepingCapacity() {
        return sleepingCapacity;
    }

    public void setSleepingCapacity(int sleepingCapacity) {
        this.sleepingCapacity = sleepingCapacity;
    }
}
