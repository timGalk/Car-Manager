package com.example.carmanager;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Camper extends Vehicle {

    private int sleepingCapacity;

    @JsonCreator
    public Camper(@JsonProperty("model") String model,
                  @JsonProperty("year") int year,
                  @JsonProperty("color") String color,
                  @JsonProperty("passengers") int passengers,
                  @JsonProperty("status") boolean status,
                  @JsonProperty("price") int price,
                  @JsonProperty("sleepingCapacity") int sleepingCapacity) {
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
