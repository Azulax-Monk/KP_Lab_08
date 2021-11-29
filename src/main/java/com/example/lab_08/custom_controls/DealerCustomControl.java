package com.example.lab_08.custom_controls;

import java.io.IOException;
import java.net.URL;

import com.example.lab_08.MainApplication;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DealerCustomControl extends VBox {
    @FXML private Label nameField;
    @FXML private Label stateField;
    @FXML private Label carsCountField;
    @FXML private StringProperty name = new SimpleStringProperty("");
    @FXML private StringProperty state = new SimpleStringProperty("");
    @FXML private IntegerProperty boughtCount = new SimpleIntegerProperty(0);
    @FXML private IntegerProperty dealerCount = new SimpleIntegerProperty(0);

    public DealerCustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/DealerView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
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
        stateField.textProperty().set("State: " + state);
    }

    public int getBoughtCarsCount() {
        return boughtCount.get();
    }

    public IntegerProperty boughtCarsCountProperty() {
        return boughtCount;
    }

    public void setBoughtCarsCount(int createdDetailsCount) {
        this.boughtCount.set(createdDetailsCount);
        carsCountField.textProperty().set("Cars bought: " + getBoughtCarsCount());
    }

    public int getDealerCount() {
        return dealerCount.get();
    }

    public IntegerProperty dealerCountProperty() {
        return dealerCount;
    }

    public void setDealerCount(int supplierCount) {
        this.dealerCount.set(supplierCount);
        nameField.textProperty().set(getName());
    }

    public String getName() {
        return name.get() + " <" + getDealerCount() + ">";
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
        nameField.textProperty().set(getName());
    }
}