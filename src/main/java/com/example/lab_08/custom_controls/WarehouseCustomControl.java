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
    @FXML private Label itemsCountField;
    @FXML private StringProperty name = new SimpleStringProperty("");
    @FXML private StringProperty state = new SimpleStringProperty("");
    @FXML private IntegerProperty storedDetailsCount = new SimpleIntegerProperty(0);
    @FXML private IntegerProperty warehouseCount = new SimpleIntegerProperty(0);

    public WarehouseCustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/WarehouseView.fxml"));
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

    public int getStoredDetailsCount() {
        return storedDetailsCount.get();
    }

    public IntegerProperty storedDetailsCountProperty() {
        return storedDetailsCount;
    }

    public void setStoredDetailsCount(int createdDetailsCount) {
        this.storedDetailsCount.set(createdDetailsCount);
        itemsCountField.textProperty().set("Items created: " + getStoredDetailsCount());
    }

    public int getWarehouseCount() {
        return warehouseCount.get();
    }

    public IntegerProperty warehouseCountProperty() {
        return warehouseCount;
    }

    public void setWarehouseCount(int supplierCount) {
        this.warehouseCount.set(supplierCount);
        nameField.textProperty().set(getName());
    }

    public String getName() {
        return name.get() + " <" + getWarehouseCount() + ">";
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
        nameField.textProperty().set(getName());
    }
}