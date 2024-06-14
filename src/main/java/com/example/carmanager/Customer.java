package com.example.carmanager;

import java.util.List;

public class Customer {
    private String name;
    private String surname;
    private String ID;
    private String phone;
    private String mail;
    private List<Vehicle> bookedVehicles;

    public Customer(String name, String surname, String ID, String phone, String mail) {
        this.name = name;
        this.surname = surname;
        this.ID = ID;
        this.phone = phone;
        this.mail = mail;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
