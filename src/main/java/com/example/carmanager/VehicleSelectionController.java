package com.example.carmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VehicleSelectionController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Vehicle selectedVehicle;
    private String[] vehicleTypes = {"All", "BEVCar", "Camper", "Car", "HybridCar","ICECar", "Motorcycle", "PickupTruck"};
    private String[] availabilityOptions = {"All","Available","Not Available"};
    private FilteredList filteredList;
    private ObservableList<Vehicle> vehicleList;
    @FXML
    private AnchorPane anchorPane_scene1;

    @FXML
    private Button button_rent;

    @FXML
    private ChoiceBox<String> choiceBox_availability;

    @FXML
    private ChoiceBox<String> choiceBox_vehicleType;

    @FXML
    private Label label_color;

    @FXML
    private Label label_model;

    @FXML
    private Label label_passengers;

    @FXML
    private Label label_type;

    @FXML
    private Label label_year;

    @FXML
    public Label label_additionalInformation;

    @FXML
    private ListView<Vehicle> listView_vehicleList;

    @FXML
    private VBox vbox_additionalInformation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObjectMapper mapper = new ObjectMapper();
        DataManager dataManager = new DataManager();
        // Get the list of all vehicles
        List<Vehicle> exampleList = null;
        try {
            exampleList = dataManager.sortallVehicles(mapper);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Initialize the observable list with the vehicle list
        vehicleList = FXCollections.observableArrayList(exampleList);

        // Add vehicle types to the choice box
        choiceBox_vehicleType.getItems().addAll(vehicleTypes);
        choiceBox_vehicleType.setOnAction(event -> {
            try {
                filterList(event); // Filter the list based on selected type
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        // Add availability types to the choice box
        choiceBox_availability.getItems().addAll(availabilityOptions);
        choiceBox_availability.setOnAction(event -> {
            filterListByAvailability(event,filteredList,choiceBox_availability); // Filter the list based on selected availability
        });

        // Initialize filtered list with all vehicles
        filteredList = new FilteredList<>(vehicleList, p -> true);
        listView_vehicleList.setItems(filteredList);

        // Add listener for selecting a vehicle from the list
        listView_vehicleList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Vehicle>() {
            @Override
            public void changed(ObservableValue<? extends Vehicle> observableValue, Vehicle vehicle, Vehicle t1) {
                selectedVehicle = listView_vehicleList.getSelectionModel().getSelectedItem();
                //displayDetails();
            }
        });
    }

    public void filterListByAvailability(ActionEvent event,FilteredList<Vehicle> filteredList,ChoiceBox<String> choiceBox_availability) {
        String selectedAvailability = choiceBox_availability.getValue();

        filteredList.setPredicate(vehicle -> {
            if ("All".equals(selectedAvailability)) {
                return true; // Show all vehicles if "All" is selected
            } else if ("Available".equals(selectedAvailability)) {
                return vehicle.isStatus(); // Show only available vehicles
            } else {
                return !vehicle.isStatus(); // Show only not available vehicles
            }
        });
    }

    // Method to filter the vehicle list based on selected type
    public void filterList(ActionEvent event) throws ClassNotFoundException {
        String selectedType = choiceBox_vehicleType.getValue();

        filteredList.setPredicate(vehicle -> {
            if ("All".equals(selectedType)) {
                return true; // Show all vehicles if "All" is selected
            }
            try {
                Class<?> clas = Class.forName("com.example.carmanager." + selectedType);
                return clas.isInstance(vehicle);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    // Method to display details of the selected vehicle
    @FXML
    public void displayDetails(){

        // this method should utilize the declared labels and properties of the selected object
        // the selected vehicle is selectedVehicle
        // one label exists for individual properties of all extensions of Vehicle - label_additionalInformation
        label_model.setText(selectedVehicle.getModel());
        label_type.setText(selectedVehicle.getClass().getSimpleName());
        label_color.setText(selectedVehicle.getColor());
        label_year.setText(String.valueOf(selectedVehicle.getYear()));
        label_passengers.setText(String.valueOf(selectedVehicle.getPassengers()));

        // Display additional information based on the type of vehicle
        String additionalInfo = " ";
        if (selectedVehicle instanceof BEVCar) {
            BEVCar bevCar = (BEVCar) selectedVehicle;
            additionalInfo = "Battery Capacity: " + bevCar.getBatteryCapacity() + " kWh\nRange: " + bevCar.getRange() + " miles";
        } else if (selectedVehicle instanceof HybridCar) {
            HybridCar hybridCar = (HybridCar) selectedVehicle;
            additionalInfo = "Engine Size: " + hybridCar.getEngineSize() + " L\nFuel Type: " + hybridCar.getFuelType() + "\nElectric Range: " + hybridCar.getElectricRange() + " miles";
        } else if (selectedVehicle instanceof ICECar) {
            ICECar iceCar = (ICECar) selectedVehicle;
            additionalInfo = "Engine Size: " + iceCar.getEngineSize() + " L\nEngine Size: " + iceCar.getEngineSize() + "\nRange: " + iceCar.getRange();
        } else if (selectedVehicle instanceof Camper) {
            Camper camper = (Camper) selectedVehicle;
            additionalInfo = "Sleeping Capacity: " + camper.getSleepingCapacity();
        } else if (selectedVehicle instanceof Motorcycle) {
            Motorcycle motorcycle = (Motorcycle) selectedVehicle;
            additionalInfo = "Motorcycle Type: " + motorcycle.getMotorcycleType();
        } else if (selectedVehicle instanceof PickupTruck) {
            PickupTruck pickupTruck = (PickupTruck) selectedVehicle;
            additionalInfo = "Payload Capacity: " + pickupTruck.getPayloadCapacity() + " lbs";
        }
        label_additionalInformation.setText(additionalInfo);
    }

    // Method to handle the rent button click event
    public void rent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("customerInput-view.fxml"));
        root = loader.load();
        CustomerInputController customerInputController = loader.getController();
        if (selectedVehicle != null){
            customerInputController.displayVehicle(selectedVehicle);
            customerInputController.setVehicle(selectedVehicle);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}