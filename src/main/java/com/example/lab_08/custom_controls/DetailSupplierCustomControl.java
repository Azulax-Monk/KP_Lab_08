package com.example.lab_08.custom_controls;

import java.io.IOException;
import java.net.URL;

import com.example.lab_08.MainApplication;
import com.example.lab_08.models.FactoryInfoModel;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DetailSupplierCustomControl extends VBox {
    @FXML private Label nameField;
    @FXML private Label stateField;
    @FXML private Label itemsCountField;
    @FXML private Label supplierCountField;
    @FXML private StringProperty name = new SimpleStringProperty("");
    @FXML private StringProperty state = new SimpleStringProperty("");
    @FXML private StringProperty createdDetailsCount = new SimpleStringProperty("");
    @FXML private StringProperty supplierCount = new SimpleStringProperty("");

    public DetailSupplierCustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/DetailSupplierView.fxml"));
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
        //supplierCountField.textProperty().set(getSupplierCount());
        itemsCountField.textProperty().bind(createdDetailsCount);
        stateField.textProperty().bind(state);
    }

    // Getters and setters region
    public String getState() {
        return "State: " + state.get();
    }

    public StringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public String getCreatedDetailsCount() {
        return "Items created: " + createdDetailsCount.get();
    }

    public StringProperty createdDetailsCountProperty() {
        return createdDetailsCount;
    }

    public void setCreatedDetailsCount(String createdDetailsCount) {
        this.createdDetailsCount.set(createdDetailsCount);
    }

    public String getSupplierCount() {
        return supplierCount.get();
    }

    public StringProperty supplierCountProperty() {
        return supplierCount;
    }

    public void setSupplierCount(String supplierCount) {
        this.supplierCount.set(supplierCount);
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