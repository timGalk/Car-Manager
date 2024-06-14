package com.example.carmanager;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {
    private String name;
    private String surname;
    private String ID;
    private String email;
    private String phone;

    // No-argument constructor
    public Customer() {}

    @JsonCreator
    public Customer(@JsonProperty("name") String name,
                    @JsonProperty("surname") String surname,
                    @JsonProperty("ID") String ID,
                    @JsonProperty("email") String email,
                    @JsonProperty("phone") String phone) {
        this.name = name;
        this.surname = surname;
        this.ID = ID;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
