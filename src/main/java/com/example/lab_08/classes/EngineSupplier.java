package com.example.lab_08.classes;

import com.example.lab_08.enums.SupplierState;
import com.example.lab_08.interfaces.ISupplier;

public class EngineSupplier implements ISupplier {
    private Engine engineToSupply;
    private WarehouseController warehouseController;
    private SupplierState state;
    private long speedTime;
    private final long waitTime = 100;

    public EngineSupplier(WarehouseController warehouseController) {
        this.warehouseController = warehouseController;
        speedTime = 5000;
        state = SupplierState.WORKING;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(speedTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.state.equals(SupplierState.WORKING)) {
                engineToSupply = orderToConstruct();
                store();
            }
            else {
                while (this.state.equals(SupplierState.STOPPED)) {
                    try {
                        Thread.sleep(waitTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public Engine orderToConstruct() {
        System.out.println("Begin constructing engine");
        return new EngineConstructor().construct();
    }

    @Override
    public boolean store() {
        if (warehouseController.pushItem(engineToSupply)) {
            engineToSupply = null;
            System.out.println("Stored engine");
            return true;
        }
        else {
            this.state = SupplierState.STOPPED;
            System.out.println("Failed to store engine");
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
