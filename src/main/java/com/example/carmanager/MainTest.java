package com.example.carmanager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) throws IOException {

        DataManager dataManager = new DataManager();
        String filePath = "car-manager/Data/CommonBase.json";

//        List<BEVCar> bevCars = dataManager.getInfoBEV(filePath);
//        System.out.println(bevCars);
//
//        for(BEVCar bevCar:bevCars){
//            System.out.println(bevCar.getModel());
//        }
        List<Vehicle> vehicles = dataManager.parseVehicles(filePath);

        if (vehicles != null) {
            for (Vehicle vehicle : vehicles) {
                System.out.println();
            }
        } else {
            System.err.println("Failed to load vehicles data.");
        }



    }

}
