package com.example.carmanager;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Car extends Vehicle {

    private String fuelType;

    @JsonCreator
    public Car(@JsonProperty("model") String model,
               @JsonProperty("year") int year,
               @JsonProperty("color") String color,
               @JsonProperty("passengers") int passengers,
               @JsonProperty("status") boolean status,
               @JsonProperty("price") int price,
               @JsonProperty("fuelType") String fuelType) {
        super(model, year, color, passengers, status, price);
        this.fuelType = fuelType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
