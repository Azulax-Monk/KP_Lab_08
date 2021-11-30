package com.example.lab_08.classes.warehouses;

import com.example.lab_08.enums.SupplierState;
import com.example.lab_08.interfaces.ISupplier;
import com.example.lab_08.interfaces.IWarehouse;

import java.util.ArrayList;

public class WarehouseController {
    private IWarehouse assignedWarehouse;
    private long itemsProduced = 0;
    private ArrayList<? extends ISupplier> supplierList;

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
            return true;
        }
        else {
            notifySuppliers(SupplierState.STOPPED);
            return false;
        }
    }

    public synchronized Object popItem() {
        notifySuppliers(SupplierState.WORKING);

        if (!assignedWarehouse.isEmpty()) {
            return assignedWarehouse.popItem();
        }
        else {
            return null;
        }
    }

    public void notifySuppliers(SupplierState state) {
        for (var s : supplierList) {
            s.setState(state);
        }
    }

    public long getItemsProduced() {
        return itemsProduced;
    }
}
