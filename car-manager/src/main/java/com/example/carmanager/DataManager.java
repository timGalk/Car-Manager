package com.example.carmanager;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class DataManager {
    // BEVCars parser
    public List<BEVCar> getInfoBEV(String filename){
        File theFile = new File(filename);
        try {
            FileReader fr = new FileReader(theFile);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<BEVCar>>() {
          }.getType();
            List<BEVCar> bevCars = gson.fromJson(fr, listType);
            fr.close();
            return bevCars;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    //Common parser
    public List<Vehicle> parseVehicles(String filename) {
        try (FileReader fr = new FileReader(filename)) {
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







}
