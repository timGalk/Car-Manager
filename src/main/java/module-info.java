module com.example.carmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.carmanager to javafx.fxml;
    exports com.example.carmanager;
}