package com.example.carmanager;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private Vehicle vehicle;
    private Customer customer;
    private String startDate;
    private String endDate;

    // No-argument constructor
    public Reservation() {}

    @JsonCreator
    public Reservation(@JsonProperty("vehicle") Vehicle vehicle,
                       @JsonProperty("customer") Customer customer,
                       @JsonProperty("startDate") String startDate,
                       @JsonProperty("endDate") String endDate) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public double calculateTotalPrice() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            long duration = end.getTime() - start.getTime();
            long numberOfDays = TimeUnit.DAYS.convert(duration, TimeUnit.MILLISECONDS);
            return (numberOfDays * vehicle.getPrice());
        } catch (ParseException e) {
            e.printStackTrace();
            return 0; // In case of an error, return 0 or handle appropriately
        }
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
