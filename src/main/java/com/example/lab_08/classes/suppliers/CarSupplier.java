package com.example.lab_08.classes.suppliers;

import com.example.lab_08.classes.carParts.Car;
import com.example.lab_08.classes.events.EventPool;
import com.example.lab_08.classes.events.EventType;
import com.example.lab_08.classes.warehouses.WarehouseController;
import com.example.lab_08.enums.SupplierState;
import com.example.lab_08.interfaces.ICarBuilder;
import com.example.lab_08.interfaces.ISupplier;
import java.util.logging.Logger;

public class CarSupplier implements ISupplier {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Car carToSupply;
    private WarehouseController carWarehouseController;
    private ICarBuilder carConstructor;
    private SupplierState state;
    private long speedTime;
    private int carsCount;
    private final long waitTime = 100;
    private EventPool eventPool;

    public CarSupplier(WarehouseController carWC, WarehouseController engineWC,
                       WarehouseController bodyWC, WarehouseController accessoryWC) {
        this.carWarehouseController = carWC;
        this.carConstructor = new CarConstructor(engineWC, bodyWC, accessoryWC);
        this.state = SupplierState.STOPPED;
        speedTime = 5000;       // to be upd
        carsCount = 0;
    }

    @Override
    public void setState(SupplierState state) {
        this.state = state;
        eventPool.getEvent(EventType.ENTITY_STATE_CHANGED).invoke();
    }

    @Override
    public SupplierState getState() {
        return this.state;
    }

    @Override
    public void run() throws InterruptedException {
        while (true) {
            Thread.sleep(speedTime);

            if (Thread.currentThread().isInterrupted()) {
                LOGGER.warning("Thread " + Thread.currentThread().getName() + ": Thread interrupted");
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
        LOGGER.info("Thread " + Thread.currentThread().getName() + ": Begin constructing car");
        return carConstructor.construct();
    }

    @Override
    public boolean store() {
        eventPool.getEvent(EventType.ITEM_CREATED).invoke();
        if (carWarehouseController.pushItem(carToSupply)) {
            carToSupply = null;
            LOGGER.info("Thread " + Thread.currentThread().getName() + ": Stored car");
            return true;
        }
        else {
            this.state = SupplierState.STOPPED;
            LOGGER.info("Thread " + Thread.currentThread().getName() + ": Failed to store car");
            return false;
        }
    }

    public long getSpeedTime() {
        return speedTime;
    }

    public void setSpeedTime(long speedTime) {
        this.speedTime = speedTime;
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
        ISupplier.super.notify(ep, type);
    }


}
