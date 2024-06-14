package com.example.carmanager;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ICECar extends Vehicle {

    private double engineSize;
    private String range;

    @JsonCreator
    public ICECar(@JsonProperty("model") String model,
                  @JsonProperty("year") int year,
                  @JsonProperty("color") String color,
                  @JsonProperty("passengers") int passengers,
                  @JsonProperty("status") boolean status,
                  @JsonProperty("price") int price,
                  @JsonProperty("engineSize") double engineSize,
                  @JsonProperty("range") String range) {
        super(model, year, color, passengers, status, price);
        this.engineSize = engineSize;
        this.range = range;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
