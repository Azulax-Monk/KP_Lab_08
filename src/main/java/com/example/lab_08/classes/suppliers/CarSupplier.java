package com.example.lab_08.classes.suppliers;

import com.example.lab_08.classes.carParts.Car;
import com.example.lab_08.classes.warehouses.CarConstructor;
import com.example.lab_08.classes.warehouses.WarehouseController;
import com.example.lab_08.enums.SupplierState;
import com.example.lab_08.interfaces.ICarBuilder;
import com.example.lab_08.interfaces.ISupplier;

public class CarSupplier implements ISupplier {
    private Car carToSupply;
    private WarehouseController carWarehouseController;
    private ICarBuilder carConstructor;
    private SupplierState state;
    private long speedTime;
    private int carsCount;
    private final long waitTime = 100;

    public CarSupplier(WarehouseController carWC, WarehouseController engineWC,
                       WarehouseController bodyWC, WarehouseController accessoryWC) {
        this.carWarehouseController = carWC;
        this.carConstructor = new CarConstructor(engineWC, bodyWC, accessoryWC);
        this.state = SupplierState.STOPPED;
        speedTime = 5000;
        carsCount = 0;
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
                carToSupply = orderToConstruct();
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
