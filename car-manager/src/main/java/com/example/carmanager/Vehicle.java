package com.example.carmanager;

public class Vehicle {
    private String model;
    private int year;
    private String color;
    private int passengers;

    public Vehicle(String model, int year, String color, int passengers) {
        this.model = model;
        this.year = year;
        this.color = color;
        this.passengers = passengers;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }
}
