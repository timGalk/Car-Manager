package com.example.carmanager;

import java.time.LocalDate;

public class Vehicle {
    //

    private String model;
    private int year;
    private String color;
    private int passengers;
    private int price;
    private boolean status;
    // added boolean property bookingStatus in order to define booking status  of vehicle


    public Vehicle(String model, int year, String color, int passengers, boolean status, int price) {
        this.price = price;
        this.passengers = passengers;
        this.color = color;
        this.year = year;
        this.model = model;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
