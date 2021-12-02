package com.example.lab_08.classes.system;

import com.example.lab_08.classes.carParts.Car;
import com.example.lab_08.classes.events.EventPool;
import com.example.lab_08.classes.events.EventType;
import com.example.lab_08.classes.warehouses.WarehouseController;
import com.example.lab_08.enums.DealerState;
import com.example.lab_08.interfaces.INotifier;

import java.util.logging.Logger;

public class Dealer implements INotifier {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private WarehouseController carWarehouseController;
    private DealerState state;
    private long speedTime;
    private final long waitTime = 100;
    private Car car;
    private EventPool eventPool;

    public Dealer(WarehouseController carWarehouseController) {
        this.carWarehouseController = carWarehouseController;
        state = DealerState.WAITING;
        speedTime = 5000; // to be upd
        car = null;
    }

    private void setState(DealerState state) {
        if (!this.state.equals(state)) {
            this.state = state;
        }
    }

    public DealerState getState() {
        return this.state;
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
            LOGGER.info("Thread " + Thread.currentThread().getName() + ": Dealer took car");
            notify(eventPool, EventType.CAR_BOUGHT);
            return  true;
        }
        else {
            LOGGER.info("Thread " + Thread.currentThread().getName() + ": Dealer didn't take car");
            return false;
        }
    }

    public boolean sellCar() throws InterruptedException {
        Thread.sleep(speedTime);
        car = null;
        LOGGER.info("Thread " + Thread.currentThread().getName() + ": Dealer sold car");
        return true;
    }

    @Override
    public void setEventPool(EventPool ep) {
        this.eventPool = ep;
    }

    @Override
    public EventPool getEventPool() {
        return this.eventPool;
    }

    @Override
    public void notify(EventPool ep, EventType type) {
        INotifier.super.notify(ep, type);
    }

    public long getSpeedTime() {
        return speedTime;
    }

    public void setSpeedTime(long speedTime) {
        this.speedTime = speedTime;
    }
}
