module com.example.lab_08 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    //requires gson;
    requires com.google.gson;


    opens com.example.lab_08 to javafx.fxml;
    opens com.example.lab_08.utils to javafx.fxml;
    opens com.example.lab_08.custom_controls to javafx.fxml;
    opens com.example.lab_08.classes to javafx.fxml, com.google.gson;

    exports com.example.lab_08;
    exports com.example.lab_08.classes;
    exports com.example.lab_08.utils;
    exports com.example.lab_08.custom_controls;
    exports com.example.lab_08.enums;
    opens com.example.lab_08.enums to javafx.fxml;
}