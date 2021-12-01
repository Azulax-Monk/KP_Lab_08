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
    @FXML private Label createdItemsField;
    @FXML private Label supplierCountField;
    @FXML private StringProperty name = new SimpleStringProperty("");
    @FXML private StringProperty state = new SimpleStringProperty("");
    @FXML private StringProperty createdItemsCount = new SimpleStringProperty("");
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
        createdItemsField.textProperty().bind(createdItemsCount);
        stateField.textProperty().bind(state);
    }

    // Getters and setters region

    public String getCreatedItemsCount() {
        return "Items count: " + createdItemsCount.get();
    }

    public StringProperty createdItemsCountProperty() {
        return createdItemsCount;
    }

    public void setCreatedItemsCount(String createdItemsCount) {
        this.createdItemsCount.set(createdItemsCount);
    }

    public String getState() {
        return "State: " + state.get();
    }

    public StringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
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