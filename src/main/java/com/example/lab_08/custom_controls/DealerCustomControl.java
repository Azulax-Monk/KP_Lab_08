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
    @FXML private Label boughtCarsField;
    @FXML private Label dealerCountField;
    @FXML private StringProperty name = new SimpleStringProperty("");
    @FXML private StringProperty state = new SimpleStringProperty("");
    @FXML private StringProperty boughtCarsCount = new SimpleStringProperty("");
    @FXML private StringProperty dealerCount = new SimpleStringProperty("");

    public DealerCustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/DealerView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        bindProperties();
    }

    private void bindProperties() {
        nameField.textProperty().bind(name);
        dealerCountField.textProperty().bind(dealerCount);
        boughtCarsField.textProperty().bind(boughtCarsCount);
        stateField.textProperty().bind(state);
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

    public String getBoughtCarsCount() {
        return boughtCarsCount.get();
    }

    public StringProperty boughtCarsCountProperty() {
        return boughtCarsCount;
    }

    public void setBoughtCarsCount(String boughtCarsCount) {
        this.boughtCarsCount.set(boughtCarsCount);
    }

    public String getDealerCount() {
        return dealerCount.get();
    }

    public StringProperty dealerCountProperty() {
        return dealerCount;
    }

    public void setDealerCount(String dealerCount) {
        this.dealerCount.set(dealerCount);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name + ": ");
    }
}