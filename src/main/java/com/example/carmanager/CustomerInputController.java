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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CustomerInputController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Vehicle selectedVehicle;
    private List<Reservation> reservations = new ArrayList<>();

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

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

    public void btb_Submit(ActionEvent actionEvent) {
        try {
            // Parse the dates from the text fields using the specified date formatter
            LocalDate dateFrom = LocalDate.parse(textField_dateFrom.getText(), dateFormatter);
            LocalDate dateTo = LocalDate.parse(textField_dateTo.getText(), dateFormatter);

            // Check if the selected vehicle is available for the specified dates
            if (!isVehicleAvailable(dateFrom, dateTo)) {
                label_invalidSubmission.setText("Vehicle is not available for the selected dates.");
                return;
            }

            // Validate email format
            if (!isValidEmail(textField_email.getText())) {
                label_invalidSubmission.setText("Invalid email format.");
                return;
            }

            // Validate phone number format
            if (!isValidPhoneNumber(textField_phoneNumber.getText())) {
                label_invalidSubmission.setText("Invalid phone number format. \nPlease use 123-123-1234.");
                return;
            }

            // Generate a random ID for the customer
            Random random = new Random();
            int id = random.nextInt(999999);

            // Create a new Vehicle object using the selected vehicle
            Vehicle vehicle = selectedVehicle;

            // Create a new Customer object using the information from the text fields and the generated ID
            Customer customer = new Customer(textField_name.getText(), textField_surname.getText(), String.format("%06d", id), textField_email.getText(), textField_phoneNumber.getText());

            // Create a new Reservation object using the vehicle, customer, and dates
            Reservation reservation = new Reservation(vehicle, customer, dateFrom.toString(), dateTo.toString());

            // Create an instance of DataManager to handle data storage and add to customer database
            DataManager dataManager = new DataManager();
            dataManager.addCustInfo(customer);

            // Add the new reservation and add to database
            reservations.add(reservation);
            dataManager.addReservationInfo(reservation);

            // Generate a PDF invoice for the reservation
            PDFInvoiceGenerator invoice = new PDFInvoiceGenerator(reservations);
            invoice.WriteInvoice();

            // Display a success message that the invoice is created
            label_invalidSubmission.setText("Invoice generated successfully!");
        } catch (DateTimeParseException e) {
            label_invalidSubmission.setText("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private boolean isVehicleAvailable(LocalDate dateFrom, LocalDate dateTo) {
        for (Reservation reservation : reservations) {
            if (reservation.getVehicle().equals(selectedVehicle)) {
                LocalDate reservedFrom = LocalDate.parse(reservation.getStartDate());
                LocalDate reservedTo = LocalDate.parse(reservation.getEndDate());
                if ((dateFrom.isBefore(reservedTo) && dateTo.isAfter(reservedFrom)) || dateFrom.equals(reservedFrom) || dateTo.equals(reservedTo)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        // Define the email pattern
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Define the phone number pattern (123-123-1234 format)
        String phonePattern = "^\\d{3}-\\d{3}-\\d{4}$";
        Pattern pattern = Pattern.compile(phonePattern);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}