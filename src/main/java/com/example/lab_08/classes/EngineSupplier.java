package com.example.lab_08.classes;

import com.example.lab_08.interfaces.ISupplier;

public class EngineSupplier implements ISupplier {
    private Engine engineToSupply;
    private WarehouseController warehouseController;
    private boolean state;
    private long speedTime;
    private final long waitTime = 100;

    public EngineSupplier(WarehouseController warehouseController) {
        this.warehouseController = warehouseController;
        speedTime = 5000;
        state = true;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(speedTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.state) {
                engineToSupply = orderToConstruct();
                store();
            }
            else {
                while (!this.state) {
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
            this.state = false;
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
