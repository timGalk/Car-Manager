package com.example.carmanager;
import com.google.gson.*;
import java.lang.reflect.Type;

public class VehicleDeserializer implements JsonDeserializer<Vehicle> {
    @Override
    public Vehicle deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String vehicleType = jsonObject.get("type").getAsString();

        switch (vehicleType) {
            case "BEVCar":
                return context.deserialize(json, BEVCar.class);
            case "ICECar":
                return context.deserialize(json, ICECar.class);
            case "Camper":
                return context.deserialize(json, Camper.class);
            case "Motorcycle":
                return context.deserialize(json,Motorcycle.class);
            case "PickupTruck":
                return context.deserialize(json,PickupTruck.class);
            case "HybridCar":
                return context.deserialize(json,HybridCar.class);
            default:
                throw new JsonParseException("Unknown vehicle type: " + vehicleType);
        }

    }
}
