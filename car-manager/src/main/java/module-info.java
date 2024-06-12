module com.example.carmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.carmanager to javafx.fxml;
    exports com.example.carmanager;
}