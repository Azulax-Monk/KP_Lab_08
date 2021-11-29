package com.example.lab_08.classes;

import com.example.lab_08.enums.SupplierState;
import com.example.lab_08.interfaces.IWarehouse;

import java.util.ArrayList;

public class WarehouseController {
    private IWarehouse assignedWarehouse;
    //private ArrayList<ISupplier> supplierList;

    public WarehouseController(IWarehouse assignedWarehouse) {
        this.assignedWarehouse = assignedWarehouse;
        // supplierList = suppliers
    }

    public IWarehouse getWarehouse() {
        return assignedWarehouse;
    }

    public boolean pushItem(Object item) {

        if (!assignedWarehouse.isFull()) {
            assignedWarehouse.pushItem(item);
            return true;
        }
        else {
            notifySuppliers(SupplierState.STOPPED);
            return false;
        }
    }

    public Object popItem() {
        notifySuppliers(SupplierState.WORKING);

        if (!assignedWarehouse.isEmpty()) {
            return assignedWarehouse.popItem();
        }
        else {
            return null;
        }
    }

    public void notifySuppliers(SupplierState state) {
        // foreach (s : supplierList)
        //      s.state = state
    }
}
