package com.example.lab_08.classes.warehouses;

import com.example.lab_08.classes.carParts.CarPart;
import com.example.lab_08.classes.events.EventPool;
import com.example.lab_08.classes.events.EventType;
import com.example.lab_08.enums.WarehouseState;
import com.example.lab_08.enums.WarehouseType;
import com.example.lab_08.interfaces.IWarehouse;

import java.util.ArrayList;

public class CarPartWarehouse implements IWarehouse {
    private ArrayList<CarPart> carParts;
    private int size;
    private WarehouseType warehouseType;
    private WarehouseState state;
    private EventPool eventPool;

    public CarPartWarehouse(int maxSize, WarehouseType warehouseType) {
        this.size = maxSize;
        this.carParts = new ArrayList<>();
        this.warehouseType = warehouseType;
        this.state = WarehouseState.EMPTY;
    }

    @Override
    public WarehouseState getState() {
        return this.state;
    }

    @Override
    public void setState(WarehouseState state) {
        if (!getState().equals(state)) {
            this.state = state;
            eventPool.getEvent(EventType.ENTITY_STATE_CHANGED).invoke();
        }
    }

    @Override
    public boolean pushItem(Object item) {
        if (CarPart.class.isAssignableFrom(item.getClass()) && !isFull()) {
            carParts.add((CarPart) item);
            if (isFull())
                setState(WarehouseState.FULL);
            else
                setState(WarehouseState.SEMI_STUFFED);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public CarPart popItem() {
        if (!isEmpty()) {
            CarPart cp = carParts.remove(carParts.size() - 1);
            if (isEmpty())
                setState(WarehouseState.EMPTY);
            else
                setState(WarehouseState.SEMI_STUFFED);
            return cp;
        }
        else {
            return null;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getStoredItemCount() {
        return this.carParts.size();
    }

    @Override
    public boolean isEmpty() {
        if (carParts.size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isFull() {
        if (carParts.size() == size) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public WarehouseType getType() {
        return warehouseType;
    }

    @Override
    public void setEventPool(EventPool ep) {
        this.eventPool = ep;
    }

    @Override
    public EventPool getEventPool() {
        return this.eventPool;
    }

    @Override
    public void notify(EventPool ep, EventType type) {
        IWarehouse.super.notify(ep, type);
    }
}
