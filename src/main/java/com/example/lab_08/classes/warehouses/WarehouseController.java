package com.example.lab_08.classes.warehouses;

import com.example.lab_08.classes.events.EventPool;
import com.example.lab_08.classes.events.EventType;
import com.example.lab_08.enums.SupplierState;
import com.example.lab_08.interfaces.INotifier;
import com.example.lab_08.interfaces.ISupplier;
import com.example.lab_08.interfaces.IWarehouse;
import java.util.logging.Logger;

import java.util.ArrayList;

public class WarehouseController implements INotifier {
    private IWarehouse assignedWarehouse;
    private long itemsProduced = 0;
    private ArrayList<? extends ISupplier> supplierList;
    private EventPool eventPool;

    public WarehouseController(IWarehouse assignedWarehouse) {
        this.assignedWarehouse = assignedWarehouse;
    }

    public void setSuppliers(ArrayList<? extends ISupplier> suppliers) {
        this.supplierList = suppliers;
    }

    public IWarehouse getWarehouse() {
        return assignedWarehouse;
    }

    public synchronized boolean pushItem(Object item) {
        if (!assignedWarehouse.isFull()) {
            assignedWarehouse.pushItem(item);
            itemsProduced++;
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("Warehouse " + assignedWarehouse.getType() + " stored: " + item.getClass().getSimpleName());
            eventPool.getEvent(EventType.ITEM_STORED).invoke();
            return true;
        }
        else {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("Warehouse " + assignedWarehouse.getType() + " is full: " + assignedWarehouse.isFull());
            notifySuppliers(SupplierState.STOPPED);
            return false;
        }
    }

    public synchronized Object popItem() {
        if (!assignedWarehouse.isEmpty()) {
            Object o = assignedWarehouse.popItem();
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("Warehouse " + assignedWarehouse.getType() + " poped: " + o.getClass().getSimpleName());

            notifySuppliers(SupplierState.WORKING);
            return o;
        }
        else {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("Warehouse " + assignedWarehouse.getType() + " is empty: " + assignedWarehouse.isEmpty());
            notifySuppliers(SupplierState.WORKING);
            return null;
        }
    }

    public synchronized void notifySuppliers(SupplierState state) {
        for (var s : supplierList) {
            s.setState(state);
        }
    }

    public long getItemsProduced() {
        return itemsProduced;
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
}
