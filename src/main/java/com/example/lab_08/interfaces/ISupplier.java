package com.example.lab_08.interfaces;

import com.example.lab_08.enums.SupplierState;

public interface ISupplier {
    public void run() throws InterruptedException;
    public Object orderToConstruct();
    public boolean store();
    public void setState(SupplierState state);
    public void setSpeedTime(long speedTime);
    public long getSpeedTime();
}
