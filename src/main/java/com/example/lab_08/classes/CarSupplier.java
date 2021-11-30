package com.example.lab_08.classes;

import com.example.lab_08.enums.SupplierState;
import com.example.lab_08.interfaces.ICarBuilder;
import com.example.lab_08.interfaces.ISupplier;

public class CarSupplier implements ISupplier {
    private Car carToSupply;
    private WarehouseController carWarehouseController;
    private ICarBuilder carConstructor;
    private SupplierState state;
    private long speedTime;
    private final long waitTime = 100;

    public CarSupplier(WarehouseController carWC, WarehouseController engineWC,
                       WarehouseController bodyWC, WarehouseController accessoryWC) {
        this.carWarehouseController = carWC;
        this.carConstructor = new CarConstructor(engineWC, bodyWC, accessoryWC);
        this.state = SupplierState.STOPPED;
        speedTime = 5000;       // to be upd
    }

    @Override
    public void setState(SupplierState state) {
        this.state = state;
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
                carToSupply = orderToConstruct();
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
    public Car orderToConstruct() {
        System.out.println("Begin constructing car");
        return carConstructor.construct();
    }

    @Override
    public boolean store() {
        if (carWarehouseController.pushItem(carToSupply)) {
            carToSupply = null;
            System.out.println("Stored car");
            return true;
        }
        else {
            this.state = SupplierState.STOPPED;
            System.out.println("Failed to store car");
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
