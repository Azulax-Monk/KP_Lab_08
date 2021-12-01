package com.example.lab_08.classes.system;

import com.example.lab_08.classes.carParts.Car;
import com.example.lab_08.classes.warehouses.WarehouseController;
import java.util.logging.Logger;

public class Dealer {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
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
                LOGGER.warning("Thread " + Thread.currentThread().getName() + ": Thread interrupted");
                throw new InterruptedException("Thread interrupted");
            }

            takeCar();
            if (car != null)
                sellCar();
        }
    }

    public boolean takeCar() {
        car = (Car) carWarehouseController.popItem();
        if (car != null) {
            LOGGER.info("Thread " + Thread.currentThread().getName() + ": Dealer takes car");
            return  true;
        }
        else {
            LOGGER.info("Thread " + Thread.currentThread().getName() + ": Dealer doesn't take car");
            return false;
        }
    }

    public boolean sellCar() {
        LOGGER.info("Thread " + Thread.currentThread().getName() + ": Dealer sells car");
        return true;
    }
}
