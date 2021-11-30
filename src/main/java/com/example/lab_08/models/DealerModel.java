package com.example.lab_08.models;

import com.example.lab_08.classes.system.Dealer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

import java.util.ArrayList;

public class DealerModel {
    @FXML private IntegerProperty boughtCarsCount = new SimpleIntegerProperty(0);
    @FXML private StringProperty state = new SimpleStringProperty("");
    private ArrayList<Dealer> dealers;

    public DealerModel(ArrayList<Dealer> dealers) {
        this.dealers = dealers;
    }

    // Getters and setters region
    public int getBoughtCarsCount() {
        return boughtCarsCount.get();
    }

    public IntegerProperty boughtCarsCountProperty() {
        return boughtCarsCount;
    }

    public void setBoughtCarsCount(int boughtCarsCount) {
        this.boughtCarsCount.set(boughtCarsCount);
    }

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
