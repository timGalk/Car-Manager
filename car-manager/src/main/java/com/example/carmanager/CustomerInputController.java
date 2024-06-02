package com.example.carmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerInputController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Vehicle selectedVehicle;

    @FXML
    private Button button_goBack;

    @FXML
    private Button button_submit;

    @FXML
    private Label label_invalidSubmission;

    @FXML
    private Label label_selectedVehicle;

    @FXML
    private TextField textField_dateFrom;

    @FXML
    private TextField textField_dateTo;

    @FXML
    private TextField textField_email;

    @FXML
    private TextField textField_name;

    @FXML
    private TextField textField_phoneNumber;

    @FXML
    private TextField textField_surname;

    //information provided in the text fields should be used to create a new Customer object
    //check for availability of the car with its rentFrom and rentTo LocalDate properties
    //if the car isn't available for rent, display a message in the invalidSubmission label

    //vehicle selected in the display list in the previous scene is used here
    public void displayVehicle(Vehicle selectedVehicle){
        label_selectedVehicle.setText(selectedVehicle.getModel());
    }
    public void setVehicle(Vehicle vehicle){
        selectedVehicle = vehicle;
    }
    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("vehicleSelection-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}