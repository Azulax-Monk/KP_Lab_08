module com.example.lab_08 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.lab_08 to javafx.fxml;
    opens com.example.lab_08.utils to javafx.fxml;
    opens com.example.lab_08.custom_controls to javafx.fxml;
    exports com.example.lab_08;
    exports com.example.lab_08.utils;
    exports com.example.lab_08.custom_controls;
    exports com.example.lab_08.enums;
    opens com.example.lab_08.enums to javafx.fxml;
}