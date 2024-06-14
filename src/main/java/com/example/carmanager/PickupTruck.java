package com.example.carmanager;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PickupTruck extends Vehicle {

    private double payloadCapacity;

    @JsonCreator
    public PickupTruck(@JsonProperty("model") String model,
                       @JsonProperty("year") int year,
                       @JsonProperty("color") String color,
                       @JsonProperty("passengers") int passengers,
                       @JsonProperty("status") boolean status,
                       @JsonProperty("price") int price,
                       @JsonProperty("payloadCapacity") double payloadCapacity) {
        super(model, year, color, passengers, status, price);
        this.payloadCapacity = payloadCapacity;
    }

    public double getPayloadCapacity() {
        return payloadCapacity;
    }

    public void setPayloadCapacity(double payloadCapacity) {
        this.payloadCapacity = payloadCapacity;
    }
}
