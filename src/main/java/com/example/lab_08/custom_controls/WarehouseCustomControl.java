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

public class WarehouseCustomControl extends VBox {
    @FXML private Label nameField;
    @FXML private Label stateField;
    @FXML private Label storedItemsField;
    @FXML private StringProperty name = new SimpleStringProperty("");
    @FXML private StringProperty state = new SimpleStringProperty("");
    @FXML private StringProperty storedItemsCount = new SimpleStringProperty("");
    @FXML private StringProperty warehouseCount = new SimpleStringProperty("");

    public WarehouseCustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/WarehouseView.fxml"));
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
        storedItemsField.textProperty().bind(storedItemsCount);
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

    public String getStoredDetailsCount() {
        return storedItemsCount.get();
    }

    public StringProperty storedItemsCountProperty() {
        return storedItemsCount;
    }

    public void setStoredItemsCountCount(int storedItemsCount) {
        this.storedItemsCount.set(String.valueOf(storedItemsCount));
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}