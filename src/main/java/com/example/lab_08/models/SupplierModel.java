package com.example.lab_08.models;

import com.example.lab_08.classes.events.Event;
import com.example.lab_08.classes.events.EventPool;
import com.example.lab_08.classes.events.EventType;
import com.example.lab_08.interfaces.ISupplier;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

import java.util.ArrayList;

public class SupplierModel {
    @FXML private StringProperty state = new SimpleStringProperty("");
    @FXML private IntegerProperty supplierCount = new SimpleIntegerProperty(0);
    @FXML private IntegerProperty itemsCreatedCount = new SimpleIntegerProperty(0);
    private ArrayList<? extends ISupplier> suppliers;
    private EventPool eventPool;

    public SupplierModel(ArrayList<? extends ISupplier> suppliers) {
        this.suppliers = suppliers;
        setSupplierCount(this.suppliers.size());

        eventPool = new EventPool();
        //eventPool.addEvent(new Event(EventType.ENTITY_COUNT_CHANGED));
        eventPool.addEvent(new Event(EventType.ENTITY_STATE_CHANGED));
        eventPool.addEvent(new Event(EventType.ITEM_CREATED));
        for (var s : suppliers) {
            s.setEventPool(eventPool);
        }

        eventPool.getEvent(EventType.ENTITY_STATE_CHANGED).addListener(() ->
        {
            setState(suppliers.get(0).getState().toString());
        });

        eventPool.getEvent(EventType.ITEM_CREATED).addListener(() ->
        {
            setItemsCreatedCount(getItemsCreatedCount() + 1);
        });
    }


    // Getters and setters region
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

    public int getSupplierCount() {
        return supplierCount.get();
    }

    public IntegerProperty supplierCountProperty() {
        return supplierCount;
    }

    public void setSupplierCount(int supplierCount) {
        this.supplierCount.set(supplierCount);
    }


    public int getItemsCreatedCount() {
        return itemsCreatedCount.get();
    }

    public IntegerProperty itemsCreatedCountProperty() {
        return itemsCreatedCount;
    }

    public void setItemsCreatedCount(int itemsCreatedCount) {
        // Updates UI in separate thread
        Platform.runLater(
                () -> {
                    this.itemsCreatedCount.set(itemsCreatedCount);
                }
        );
    }
}
