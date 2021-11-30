package com.example.lab_08.classes.suppliers;

import com.example.lab_08.classes.carParts.Accessory;
import com.example.lab_08.classes.warehouses.WarehouseController;
import com.example.lab_08.enums.SupplierState;
import com.example.lab_08.interfaces.ISupplier;

public class AccessorySupplier implements ISupplier {
    private Accessory accessoryToSupply;
    private WarehouseController warehouseController;
    private SupplierState state;
    private long speedTime;
    private final long waitTime = 100;

    public AccessorySupplier(WarehouseController warehouseController) {
        this.warehouseController = warehouseController;
        speedTime = 5000;
        state = SupplierState.WORKING;
    }

    @Override
    public void setState(SupplierState state) {
        this.state = state;
    }

    @Override
    public void run() throws InterruptedException {
        while (true) {
            Thread.sleep(speedTime);

            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException("Thread interrupted");
            }

            if (this.state.equals(SupplierState.WORKING)) {
                accessoryToSupply = orderToConstruct();
                store();
            }
            else {
                while (this.state.equals(SupplierState.STOPPED)) {
                    Thread.sleep(waitTime);
                }
            }
        }
    }

    @Override
    public Accessory orderToConstruct() {
        System.out.println("Begin constructing accessory");
        return new AccessoryConstructor().construct();
    }

    @Override
    public boolean store() {
        if (warehouseController.pushItem(accessoryToSupply)) {
            accessoryToSupply = null;
            System.out.println("Stored accessory");
            return true;
        }
        else {
            this.state = SupplierState.STOPPED;
            System.out.println("Failed to store accessory");
            return false;
        }
    }

    public long getSpeedTime() {
        return speedTime;
    }

    public void setSpeedTime(long speedTime) {
        this.speedTime = speedTime;
    }
}
