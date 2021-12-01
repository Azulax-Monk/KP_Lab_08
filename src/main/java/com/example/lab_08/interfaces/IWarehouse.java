package com.example.lab_08.interfaces;

import com.example.lab_08.enums.WarehouseType;

public interface IWarehouse extends INotifier {
    boolean pushItem(Object item);
    Object popItem();
    int getSize();
    boolean isEmpty();
    boolean isFull();
    WarehouseType getType();
}
