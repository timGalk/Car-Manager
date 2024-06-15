package com.example.carmanager;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BEVCar extends Vehicle {
    private double batteryCapacity;
    private double range;

    @JsonCreator
    public BEVCar(
            @JsonProperty("model") String model,
            @JsonProperty("year") int year,
            @JsonProperty("color") String color,
            @JsonProperty("passengers") int passengers,
            @JsonProperty("price") int price,
            @JsonProperty("status") boolean status,
            @JsonProperty("batteryCapacity") double batteryCapacity,
            @JsonProperty("range") double range
    ) {
        super(model, year, color, passengers, status, price);
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
