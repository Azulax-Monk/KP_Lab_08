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

public class CarSupplierCustomControl extends VBox {
    @FXML private Label nameField;
    @FXML private Label createdItemsField;
    @FXML private Label stateField;
    @FXML private Label supplierCountField;
    @FXML private StringProperty name = new SimpleStringProperty("");
    @FXML private StringProperty state = new SimpleStringProperty("");
    @FXML private StringProperty createdItemsCount = new SimpleStringProperty("");
    @FXML private StringProperty supplierCount = new SimpleStringProperty("");

    public CarSupplierCustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/CarSupplierView.fxml"));
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
        supplierCountField.textProperty().bind(supplierCount);
        createdItemsField.textProperty().bind(createdItemsCount);
        stateField.textProperty().bind(state);
    }

    // Getters and setters region
    public String getCreatedItemsCountCount() {
        return createdItemsCount.get();
    }

    public StringProperty createdItemsCountProperty() {
        return createdItemsCount;
    }

    public void setCreatedItemsCountCount(int createdCarsCount) {
        this.createdItemsCount.set(String.valueOf(createdCarsCount));
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

    public String getName() {
        return name.get() + " <" + getSupplierCount() + ">";
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name + ": ");
    }

    public String getSupplierCount() {
        return supplierCount.get();
    }

    public StringProperty supplierCountProperty() {
        return supplierCount;
    }

    public void setSupplierCount(int supplierCount) {
        this.supplierCount.set(String.valueOf(supplierCount));
    }
}