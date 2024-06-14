package com.example.carmanager;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


// This annotation is used to indicate that this class can be serialized/deserialized with type information.
// The `use` attribute specifies that the type information will be included as a property in the JSON.
// The `property` attribute specifies the name of the property that will hold the type information.
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
// This annotation specifies the possible subtypes of the class and their corresponding type names.
// It is used to help Jackson know which class to instantiate when deserializing JSON objects.
@JsonSubTypes({
        @JsonSubTypes.Type(value = Camper.class, name = "Camper"),
        @JsonSubTypes.Type(value = PickupTruck.class, name = "PickupTruck"),
        @JsonSubTypes.Type(value = Motorcycle.class, name = "Motorcycle"),
        @JsonSubTypes.Type(value = ICECar.class, name = "ICECar"),
        @JsonSubTypes.Type(value = HybridCar.class, name = "HybridCar"),
        @JsonSubTypes.Type(value = Car.class, name = "Car")
})

public class Vehicle {
    //

    private String model;
    private int year;
    private String color;
    private int passengers;
    private int price;
    private boolean status;
    private List<Reservation> reservations;
    // added boolean property bookingStatus in order to define booking status  of vehicle


    // No-argument constructor
    public Vehicle() {
        //this.reservations = new ArrayList<>();
    }

    // This annotation is used to indicate that this constructor should be used when deserializing JSON data.
    @JsonCreator
    public Vehicle(@JsonProperty("model") String model,
                   @JsonProperty("year") int year,
                   @JsonProperty("color") String color,
                   @JsonProperty("passengers") int passengers,
                   @JsonProperty("status") boolean status,
                   @JsonProperty("price") int price) {
        this.model = model;
        this.year = year;
        this.color = color;
        this.passengers = passengers;
        this.status = status;
        this.price = price;
        this.reservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
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

    @Override
    public String toString() {
        return model;
    }
}
