package com.example.carmanager;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


public class DataManager {

    private final String VehiclesDB = "car-manager/Data/VehicleDB.json";
    private final String CustomerDB = "car-manager/Data/CustomerDB.json";

    // BEVCars parser
//    public List<BEVCar> getInfoBEV(String filename){
//        File theFile = new File(filename);
//        try {
//            FileReader fr = new FileReader(theFile);
//            Gson gson = new Gson();
//            Type listType = new TypeToken<ArrayList<BEVCar>>() {
//          }.getType();
//            List<BEVCar> bevCars = gson.fromJson(fr, listType);
//            fr.close();
//            return bevCars;
//        } catch (IOException e){
//            e.printStackTrace();
//            return null;
//        }
//    }
    //Vehicle Parser
    public List<Vehicle> parseVehicles() {
        try (FileReader fr = new FileReader(VehiclesDB)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Vehicle.class, new VehicleDeserializer())
                    .create();
            Type listType = new TypeToken<List<Vehicle>>() {}.getType();
            List<Vehicle> vehicles = gson.fromJson(fr, listType);
            return vehicles;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    // Consumers Parser
    public List<Customer> parseCustomers() {
        try (FileReader fr = new FileReader(CustomerDB)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Vehicle>>() {
            }.getType();
            List<Customer> customers = gson.fromJson(fr, listType);
            return customers;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to add info about Customer into json file

    public void addCustInfo(Customer newCustomer){
        try {
            Gson gson = new Gson();
            // Read existing customers from the JSON file
            FileReader reader = new FileReader(CustomerDB);
            Type customerListType = new TypeToken<List<Customer>>() {}.getType();
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


}
