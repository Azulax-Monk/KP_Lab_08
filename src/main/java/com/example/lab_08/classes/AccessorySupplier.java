package com.example.lab_08.classes;

import com.example.lab_08.interfaces.ISupplier;

public class AccessorySupplier implements ISupplier {
    private Accessory accessoryToSupply;
    private WarehouseController warehouseController;
    private boolean state;
    private long speedTime;
    private final long waitTime = 100;

    public AccessorySupplier(WarehouseController warehouseController) {
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
                accessoryToSupply = orderToConstruct();
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
            this.state = false;
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
