package com.example.lab_08.classes.warehouses;

import com.example.lab_08.classes.carParts.Car;
import com.example.lab_08.classes.events.EventPool;
import com.example.lab_08.classes.events.EventType;
import com.example.lab_08.enums.WarehouseState;
import com.example.lab_08.enums.WarehouseType;
import com.example.lab_08.interfaces.IWarehouse;

import java.util.ArrayList;

public class CarWarehouse implements IWarehouse {
    private ArrayList<Car> cars;
    private int size;
    private WarehouseType warehouseType;
    private WarehouseState state;
    private EventPool eventPool;

    public CarWarehouse(int maxSize) {
        this.size = maxSize;
        this.cars = new ArrayList<>();
        this.warehouseType = WarehouseType.CAR_WAREHOUSE;
        this.state = WarehouseState.EMPTY;
    }

    @Override
    public boolean pushItem(Object item) {
        if (item.getClass().equals(Car.class) && !isFull()) {
            cars.add((Car) item);
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
    public Car popItem() {
        if (!isEmpty()) {
            Car c = cars.remove(cars.size() - 1);
            if (isEmpty())
                setState(WarehouseState.EMPTY);
            else
                setState(WarehouseState.SEMI_STUFFED);
            return c;
        }
        else {
            return null;
        }
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
    public int getStoredItemCount() {
        return this.cars.size();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (cars.size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isFull() {
        if (cars.size() == size) {
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
