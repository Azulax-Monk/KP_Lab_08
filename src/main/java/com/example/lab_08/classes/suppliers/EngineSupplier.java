package com.example.lab_08.classes.suppliers;

import com.example.lab_08.classes.carParts.Engine;
import com.example.lab_08.classes.events.EventPool;
import com.example.lab_08.classes.events.EventType;
import com.example.lab_08.classes.warehouses.WarehouseController;
import com.example.lab_08.enums.SupplierState;
import com.example.lab_08.interfaces.INotifier;
import com.example.lab_08.interfaces.ISupplier;
import java.util.logging.Logger;

public class EngineSupplier implements ISupplier {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Engine engineToSupply;
    private WarehouseController warehouseController;
    private SupplierState state;
    private long speedTime;
    private final long waitTime = 100;
    private EventPool eventPool;

    public EngineSupplier(WarehouseController warehouseController) {
        this.warehouseController = warehouseController;
        speedTime = 5000;       // to be upd
        state = SupplierState.WORKING;
    }

    @Override
    public void setState(SupplierState state) {
        this.state = state;
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
                engineToSupply = orderToConstruct();
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
    public Engine orderToConstruct() {
        LOGGER.info("Thread " + Thread.currentThread().getName() + ": Begin constructing engine");
        return new EngineConstructor().construct();
    }

    @Override
    public boolean store() {
        if (warehouseController.pushItem(engineToSupply)) {
            engineToSupply = null;
            LOGGER.info("Thread " + Thread.currentThread().getName() + ": Stored engine");
            return true;
        }
        else {
            this.state = SupplierState.STOPPED;
            LOGGER.info("Thread " + Thread.currentThread().getName() + ": Failed to store engine");
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
