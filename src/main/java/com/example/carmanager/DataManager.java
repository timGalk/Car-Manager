package com.example.carmanager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataManager {

    private final String VehiclesDB = "Data/VehicleDB.json";
    private final String CustomerDB = "Data/CustomerDB.json";
    private final String ReservationDB = "Data/ReservationDB.json";
    private ObjectMapper objectMapper;

    public DataManager() {
        // Configure object mapper for vehicles with polymorphic type handling
        BasicPolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfBaseType(Vehicle.class)
                .build();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

    }

    //Vehicle Parser
    public List<Vehicle> sortallVehicles(ObjectMapper mapper) throws IOException {
        JsonNode node = mapper.readTree(new File(VehiclesDB));
        List<Vehicle> vehicles = new ArrayList<>();
        if (node.isArray()) {
            for (JsonNode jsonNode : node) {
                Vehicle vehicle = mapper.treeToValue(jsonNode, Vehicle.class);
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }


    // Vehicles sorting by type
    //BEVCars
    public List<BEVCar> sortBevCars(List<Vehicle> vehicles) {
        List<BEVCar> bevCars = vehicles.stream()
                .filter(BEVCar.class::isInstance)
                .map(BEVCar.class::cast)
                .collect(Collectors.toList());
        return bevCars;
    }

    //Camper
    public List<Camper> sortCampers(List<Vehicle> vehicles) {
        List<Camper> campers = vehicles.stream()
                .filter(Camper.class::isInstance)
                .map(Camper.class::cast)
                .collect(Collectors.toList());
        return campers;
    }

    // Cars
    public List<Camper> sortCars(List<Vehicle> vehicles) {
        List<Camper> campers = vehicles.stream()
                .filter(Camper.class::isInstance)
                .map(Camper.class::cast)
                .collect(Collectors.toList());
        return campers;
    }


    //Motorcycles
    public List<Motorcycle> sortMotorcycles(List<Vehicle> vehicles) {
        List<Motorcycle> motorcycles = vehicles.stream()
                .filter(Motorcycle.class::isInstance)
                .map(Motorcycle.class::cast)
                .collect(Collectors.toList());
        return motorcycles;
    }

    //Hybrid cars
    public List<HybridCar> sortHybridCars(List<Vehicle> vehicles) {
        List<HybridCar> hybridCars = vehicles.stream()
                .filter(HybridCar.class::isInstance)
                .map(HybridCar.class::cast)
                .collect(Collectors.toList());
        return hybridCars;
    }

    //ICECars
    public List<ICECar> sortIceCars(List<Vehicle> vehicles) {
        List<ICECar> iceCars = vehicles.stream()
                .filter(ICECar.class::isInstance)
                .map(ICECar.class::cast)
                .collect(Collectors.toList());
        return iceCars;
    }


    //PickupTracks
    public List<PickupTruck> sortPickupTrucks(List<Vehicle> vehicles) {
        List<PickupTruck> pickupTrucks = vehicles.stream()
                .filter(PickupTruck.class::isInstance)
                .map(PickupTruck.class::cast)
                .collect(Collectors.toList());
        return pickupTrucks;
    }

    // Consumers Parser
    //error
    public List<Customer> parseCustomers() {
        try (FileReader fr = new FileReader(CustomerDB)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Customer>>() {
            }.getType();
            List<Customer> customers = gson.fromJson(fr, listType);
            return customers;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    // Method to add info about Customer into json file
    public void addCustInfo(Customer newCustomer) {
        try {
            Gson gson = new Gson();
            // Read existing customers from the JSON file
            FileReader reader = new FileReader(CustomerDB);
            Type customerListType = new TypeToken<List<Customer>>() {
            }.getType();
            List<Customer> customers = gson.fromJson(reader, customerListType);
            reader.close();

            // Add the new customer to the list
            customers.add(newCustomer);

            // Write the updated list back to the JSON file
            FileWriter writer = new FileWriter(CustomerDB);
            gson.toJson(customers, writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add info about reservation into json file
    public void addReservationInfo(Reservation newReservation) {
        try {
            Gson gson = new Gson();
            // Read existing reservation from the JSON file
            FileReader reader = new FileReader(ReservationDB);
            Type reservationListType = new TypeToken<List<Reservation>>() {
            }.getType();
            List<Reservation> reservations = gson.fromJson(reader, reservationListType);
            reader.close();
            // Add the new customer to the list
            reservations.add(newReservation);

            // Write the updated list back to the JSON file
            FileWriter writer = new FileWriter(ReservationDB);
            gson.toJson(reservations, writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method to book particular Vehicle
    public void bookVehicle(ObjectMapper mapper,List<Vehicle> vehicles,Vehicle bookedVehicle) throws IOException {
        for (Vehicle vehicle : vehicles) {
            if (bookedVehicle.getModel().equals(vehicle.getModel()) ) {
                vehicle.setStatus(false);
                System.out.println("Vehicle status updated to false.");
                break;
            }
        }


        ArrayNode arrayNode = mapper.createArrayNode();
        for (Vehicle vehicle : vehicles) {
            JsonNode vehicleNode = mapper.valueToTree(vehicle);
            arrayNode.add(vehicleNode);
        }
        mapper.writeValue(new File(VehiclesDB), arrayNode);
        System.out.println("Vehicle data saved to file.");
    }

    }