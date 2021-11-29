package com.example.lab_08.classes;

import com.example.lab_08.interfaces.ISupplier;

public class BodySupplier implements ISupplier {
    private Body bodyToSupply;
    private WarehouseController warehouseController;
    private boolean state;
    private long speedTime;
    private final long waitTime = 100;

    public BodySupplier(WarehouseController warehouseController) {
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
                bodyToSupply = orderToConstruct();
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
    public Body orderToConstruct() {
        System.out.println("Begin constructing body");
        return new BodyConstructor().construct();
    }

    @Override
    public boolean store() {
        if (warehouseController.pushItem(bodyToSupply)) {
            bodyToSupply = null;
            System.out.println("Stored body");
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
