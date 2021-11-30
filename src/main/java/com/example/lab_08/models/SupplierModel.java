package com.example.lab_08.models;

import com.example.lab_08.interfaces.ISupplier;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

import java.util.ArrayList;

public class SupplierModel {
    @FXML private StringProperty state = new SimpleStringProperty("");
    private ArrayList<? extends ISupplier> suppliers;

    public SupplierModel(ArrayList<? extends ISupplier> suppliers) {
        this.suppliers = suppliers;
    }


    // Getters and setters region
    public String getState() {
        return state.get();
    }

    public StringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }
}
