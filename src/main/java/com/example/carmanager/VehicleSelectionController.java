package com.example.carmanager;

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
    private Label label_additionalInformation;

    @FXML
    private ListView<Vehicle> listView_vehicleList;

    @FXML
    private VBox vbox_additionalInformation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //exemplary list - this should be used with the actual array list of all vehicle objects
        List<Vehicle> exampleList = new ArrayList<>();
        exampleList.add(new Vehicle("BMW",2009,"white", 2, true, 120));
        vehicleList = FXCollections.observableArrayList(exampleList);
        choiceBox_vehicleType.getItems().addAll(vehicleTypes);
        choiceBox_vehicleType.setOnAction(event -> {
            try {
                filterList(event);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        filteredList = new FilteredList<>(vehicleList, p -> true);
        listView_vehicleList.setItems(filteredList);
        listView_vehicleList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Vehicle>() {
            @Override
            public void changed(ObservableValue<? extends Vehicle> observableValue, Vehicle vehicle, Vehicle t1) {
                selectedVehicle = listView_vehicleList.getSelectionModel().getSelectedItem();
            }
        });
    }
    public void filterList(ActionEvent event) throws ClassNotFoundException {
        String selectedType = choiceBox_vehicleType.getValue();

        filteredList.setPredicate(vehicle -> {
            if ("All".equals(selectedType)) {
                return true;
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
    @FXML
    public void displayDetails(){
        // this method should utilize the declared labels and properties of the selected object
        // the selected vehicle is selectedVehicle
        // one label exists for individual properties of all extensions of Vehicle - label_additionalInformation
        label_model.setText(selectedVehicle.getModel());
        label_type.setText(selectedVehicle.getClass().getSimpleName());
        label_color.setText(selectedVehicle.getColor());
    }

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