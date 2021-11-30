package com.example.lab_08.classes.system;

import com.example.lab_08.classes.carParts.Car;
import com.example.lab_08.classes.warehouses.WarehouseController;

public class Dealer {
    private WarehouseController carWarehouseController;
    private boolean state;
    private long speedTime;
    private final long waitTime = 100;
    private Car car;

    public Dealer(WarehouseController carWarehouseController) {
        this.carWarehouseController = carWarehouseController;
        state = true;
        speedTime = 5000; // to be upd
        car = null;
    }

    public void run() throws InterruptedException {
        while (true) {
            Thread.sleep(speedTime);

            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException("Thread interrupted");
            }

            takeCar();
            sellCar();
        }
    }

    public boolean takeCar() {
        car = (Car) carWarehouseController.popItem();
        if (car != null) {
            System.out.println("Dealer takes car");
            return  true;
        }
        else {
            System.out.println("Dealer doesn't take car");
            return false;
        }
    }

    public boolean sellCar() {
        System.out.println("Dealer sells car");
        return true;
    }
}
