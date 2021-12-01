package com.example.lab_08.models;

import com.example.lab_08.classes.events.EventPool;
import com.example.lab_08.classes.warehouses.WarehouseController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

public class WarehouseModel {
    @FXML private IntegerProperty storedItemsCount = new SimpleIntegerProperty(0);
    @FXML private IntegerProperty createdItemsCount = new SimpleIntegerProperty(0);
    @FXML private StringProperty state = new SimpleStringProperty("");
    private WarehouseController warehouseController;
    private EventPool eventPool;

    public WarehouseModel(WarehouseController warehouse) {
        this.warehouseController = warehouse;
        this.warehouseController.setEventPool(eventPool);
    }

    // Getters and setters region
    public int getStoredItemsCount() {
        return storedItemsCount.get();
    }

    public IntegerProperty storedItemsCountProperty() {
        return storedItemsCount;
    }

    public void setStoredItemsCount(int storedItemsCount) {
        this.storedItemsCount.set(storedItemsCount);
    }

    public int getCreatedItemsCount() {
        return (int) warehouseController.getItemsProduced();
    }

    public IntegerProperty createdItemsCountProperty() {
        return createdItemsCount;
    }

    public void setCreatedItemsCount(int createdItemsCount) {
        this.createdItemsCount.set(createdItemsCount);
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
