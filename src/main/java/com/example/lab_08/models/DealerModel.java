package com.example.lab_08.models;

import com.example.lab_08.classes.events.Event;
import com.example.lab_08.classes.events.EventPool;
import com.example.lab_08.classes.events.EventType;
import com.example.lab_08.classes.system.Dealer;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

import java.util.ArrayList;

public class DealerModel {
    @FXML private IntegerProperty dealersCount = new SimpleIntegerProperty(0);
    @FXML private IntegerProperty boughtCarsCount = new SimpleIntegerProperty(0);
    @FXML private StringProperty state = new SimpleStringProperty("");
    @FXML private IntegerProperty speedTime = new SimpleIntegerProperty(0);
    private ArrayList<Dealer> dealers;
    private EventPool eventPool;

    public DealerModel(ArrayList<Dealer> dealers) {
        this.dealers = dealers;
        setDealersCount(this.dealers.size());

        eventPool = new EventPool();
        eventPool.addEvent(new Event(EventType.CAR_BOUGHT));
        for (var d : dealers) {
            d.setEventPool(eventPool);
        }

        eventPool.getEvent(EventType.CAR_BOUGHT).addListener(() ->
        {
            setBoughtCarsCount(getBoughtCarsCount() + 1);
        });
    }

    // Getters and setters region
    public int getDealersCount() {
        return dealersCount.get();
    }

    public IntegerProperty dealersCountProperty() {
        return dealersCount;
    }

    public void setDealersCount(int dealersCount) {
        Platform.runLater(
                () -> {
                    this.dealersCount.set(dealersCount);
                }
        );
    }

    public int getBoughtCarsCount() {
        return boughtCarsCount.get();
    }

    public IntegerProperty boughtCarsCountProperty() {
        return boughtCarsCount;
    }

    public void setBoughtCarsCount(int boughtCarsCount) {
        Platform.runLater(
                () -> {
                    this.boughtCarsCount.set(boughtCarsCount);
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

    public int getSpeedTime() {
        return speedTime.get();
    }

    public IntegerProperty speedTimeProperty() {
        return speedTime;
    }

    public void setSpeedTime(int speedTime) {
        this.speedTime.set(speedTime);
        for (var d : dealers) {
            d.setSpeedTime(speedTime);
        }
    }
}
