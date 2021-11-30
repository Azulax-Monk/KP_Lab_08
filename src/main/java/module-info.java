module com.example.lab_08 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    //requires gson;
    requires com.google.gson;


    opens com.example.lab_08 to javafx.fxml;
    opens com.example.lab_08.utils to javafx.fxml;
    opens com.example.lab_08.custom_controls to javafx.fxml;
    //opens com.example.lab_08.classes to javafx.fxml, com.google.gson;

    exports com.example.lab_08;
    exports com.example.lab_08.models;
    exports com.example.lab_08.utils;
    exports com.example.lab_08.custom_controls;
    exports com.example.lab_08.enums;
    opens com.example.lab_08.enums to javafx.fxml;
    exports com.example.lab_08.controllers;
    opens com.example.lab_08.controllers to javafx.fxml;
    exports com.example.lab_08.classes.warehouses;
    opens com.example.lab_08.classes.warehouses to com.google.gson, javafx.fxml;
    exports com.example.lab_08.classes.suppliers;
    opens com.example.lab_08.classes.suppliers to com.google.gson, javafx.fxml;
    exports com.example.lab_08.classes.carParts;
    opens com.example.lab_08.classes.carParts to com.google.gson, javafx.fxml;
    exports com.example.lab_08.classes.system;
    opens com.example.lab_08.classes.system to com.google.gson, javafx.fxml;
    exports com.example.lab_08.classes.threads;
    opens com.example.lab_08.classes.threads to com.google.gson, javafx.fxml;
}