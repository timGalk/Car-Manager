package com.example.carmanager;

public class Reservation {
    private Vehicle vehicle;
    private Customer customer;
    private String startDate;
    private String endDate;

    public Reservation(Vehicle vehicle, Customer customer, String startDate, String endDate) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
