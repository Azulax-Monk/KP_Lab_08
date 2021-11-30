package com.example.lab_08.classes.warehouses;

import com.example.lab_08.classes.carParts.Car;
import com.example.lab_08.enums.WarehouseType;
import com.example.lab_08.interfaces.IWarehouse;

import java.util.ArrayList;

public class CarWarehouse implements IWarehouse {
    private ArrayList<Car> cars;
    private int size;
    private WarehouseType warehouseType;

    public CarWarehouse(int maxSize) {
        this.size = maxSize;
        this.cars = new ArrayList<>();
        this.warehouseType = WarehouseType.CAR_WAREHOUSE;
    }

    @Override
    public boolean pushItem(Object item) {
        if (item.getClass().equals(Car.class) && !isFull()) {
            cars.add((Car) item);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Car popItem() {
        if (!isEmpty()) {
            return cars.remove(cars.size() - 1);
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
}
