package com.example.lab_08.classes;

import com.example.lab_08.enums.WarehouseType;
import com.example.lab_08.interfaces.IWarehouse;

import java.util.ArrayList;

public class CarPartWarehouse implements IWarehouse {
    private ArrayList<CarPart> carParts;
    private int size;
    private WarehouseType warehouseType;

    public CarPartWarehouse(int maxSize, WarehouseType warehouseType) {
        this.size = maxSize;
        this.carParts = new ArrayList<>();
        this.warehouseType = warehouseType;
    }

    @Override
    public boolean pushItem(Object item) {
        if (CarPart.class.isAssignableFrom(item.getClass()) && !isFull()) {
            carParts.add((CarPart) item);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public CarPart popItem() {
        if (!isEmpty()) {
            return carParts.remove(carParts.size() - 1);
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
}
