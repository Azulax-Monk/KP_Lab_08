package com.example.lab_08.models;

import com.example.lab_08.classes.events.Event;
import com.example.lab_08.classes.events.EventPool;
import com.example.lab_08.classes.events.EventType;
import com.example.lab_08.classes.warehouses.WarehouseController;
import javafx.application.Platform;
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

        eventPool = new EventPool();
        eventPool.addEvent(new Event(EventType.ENTITY_STATE_CHANGED));
        eventPool.addEvent(new Event(EventType.ITEM_STORED));

        warehouseController.setEventPool(eventPool);
        warehouseController.getWarehouse().setEventPool(eventPool);
        eventPool.getEvent(EventType.ENTITY_STATE_CHANGED).addListener(() ->
        {
            setState(warehouseController.getWarehouse().getState().toString());
        });

        eventPool.getEvent(EventType.ITEM_STORED).addListener(() ->
        {
            setStoredItemsCount(warehouseController.getWarehouse().getStoredItemCount());
            setCreatedItemsCount((int) warehouseController.getItemsProduced());
        });

        warehouseController.getEventPool().getEvent(EventType.ENTITY_STATE_CHANGED).invoke();
    }

    // Getters and setters region
    public int getStoredItemsCount() {
        return storedItemsCount.get();
    }

    public IntegerProperty storedItemsCountProperty() {
        return storedItemsCount;
    }

    public void setStoredItemsCount(int storedItemsCount) {
        Platform.runLater(
                () -> {
                    this.storedItemsCount.set(storedItemsCount);
                }
        );
    }

    public int getCreatedItemsCount() {
        return (int) warehouseController.getItemsProduced();
    }

    public IntegerProperty createdItemsCountProperty() {
        return createdItemsCount;
    }

    public void setCreatedItemsCount(int createdItemsCount) {
        Platform.runLater(
                () -> {
                    this.createdItemsCount.set(createdItemsCount);
                }
        );
    }

    public String getState() {
        return state.get();
    }

    public StringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        Platform.runLater(
                () -> {
                    this.state.set(state);
                }
        );
    }
}
