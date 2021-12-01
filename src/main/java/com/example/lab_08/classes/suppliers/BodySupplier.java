package com.example.lab_08.classes.suppliers;

import com.example.lab_08.classes.carParts.Body;
import com.example.lab_08.classes.events.EventPool;
import com.example.lab_08.classes.events.EventType;
import com.example.lab_08.classes.warehouses.WarehouseController;
import com.example.lab_08.enums.SupplierState;
import com.example.lab_08.interfaces.INotifier;
import com.example.lab_08.interfaces.ISupplier;
import java.util.logging.Logger;

public class BodySupplier implements ISupplier {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Body bodyToSupply;
    private WarehouseController warehouseController;
    private SupplierState state;
    private long speedTime;
    private final long waitTime = 100;
    private EventPool eventPool;

    public BodySupplier(WarehouseController warehouseController) {
        this.warehouseController = warehouseController;
        speedTime = 5000;       // to be upd
        state = SupplierState.WORKING;
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
                bodyToSupply = orderToConstruct();
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
    public Body orderToConstruct() {
        LOGGER.info("Thread " + Thread.currentThread().getName() + ": Begin constructing body");
        return new BodyConstructor().construct();
    }

    @Override
    public boolean store() {
        eventPool.getEvent(EventType.ITEM_CREATED).invoke();
        if (warehouseController.pushItem(bodyToSupply)) {
            bodyToSupply = null;
            LOGGER.info("Thread " + Thread.currentThread().getName() + ": Stored body");
            return true;
        }
        else {
            this.state = SupplierState.STOPPED;
            LOGGER.info("Thread " + Thread.currentThread().getName() + ": Failed to store body");
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
