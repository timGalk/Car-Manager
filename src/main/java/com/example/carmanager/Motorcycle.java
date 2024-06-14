package com.example.carmanager;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Motorcycle extends Vehicle {
    private String motorcycleType;

    @JsonCreator
    public Motorcycle(@JsonProperty("model") String model,
                      @JsonProperty("year") int year,
                      @JsonProperty("color") String color,
                      @JsonProperty("passengers") int passengers,
                      @JsonProperty("status") boolean status,
                      @JsonProperty("price") int price,
                      @JsonProperty("motorcycleType") String motorcycleType) {
        super(model, year, color, passengers, status, price);
        this.motorcycleType = motorcycleType;
    }

    public String getMotorcycleType() {
        return motorcycleType;
    }

    public void setMotorcycleType(String motorcycleType) {
        this.motorcycleType = motorcycleType;
    }
}
