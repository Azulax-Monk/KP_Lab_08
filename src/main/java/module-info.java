module com.example.lab_08 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.lab_08 to javafx.fxml;
    exports com.example.lab_08;
}