package com.example.carmanager;

import java.util.ArrayList;
import java.util.List;

public class FleetManager {
    private List<Vehicle> vehicles;

    public FleetManager() {
        vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public List<Vehicle> searchAvailableVehicles(String category) {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof ICECar && category.equalsIgnoreCase("ICE Car")) {
                availableVehicles.add(vehicle);
            } else if (vehicle instanceof HybridCar && category.equalsIgnoreCase("Hybrid Car")) {
                availableVehicles.add(vehicle);
            } else if (vehicle instanceof BEVCar && category.equalsIgnoreCase("BEV Car")) {
                availableVehicles.add(vehicle);
            } else if (vehicle instanceof Motorcycle && category.equalsIgnoreCase("Motorcycle")) {
                availableVehicles.add(vehicle);
            } else if (vehicle instanceof PickupTruck && category.equalsIgnoreCase("Pickup Truck")) {
                availableVehicles.add(vehicle);
            } else if (vehicle instanceof Camper && category.equalsIgnoreCase("Camper")) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    public Reservation makeReservation(Vehicle vehicle, Customer customer, String startDate, String endDate) {
        return new Reservation(vehicle, customer, startDate, endDate);
    }
}

