package com.example.lab_08.interfaces;

import com.example.lab_08.enums.WarehouseState;
import com.example.lab_08.enums.WarehouseType;

public interface IWarehouse extends INotifier {
    boolean pushItem(Object item);
    Object popItem();
    int getSize();
    int getStoredItemCount();
    WarehouseState getState();
    void setState(WarehouseState state);
    boolean isEmpty();
    boolean isFull();
    WarehouseType getType();
}
