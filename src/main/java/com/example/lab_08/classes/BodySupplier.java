package com.example.lab_08.classes;

import com.example.lab_08.interfaces.ISupplier;

public class BodySupplier implements ISupplier {
    private Body bodyToSupply;
    private WarehouseController warehouseController;
    private boolean state;

    @Override
    public void run() {
        while (true) {
            // wait some time
            if (this.state) {
                bodyToSupply = orderToConstruct();
                store();
            }
            else {
                while (!this.state) ;
            }
        }
    }

    @Override
    public Body orderToConstruct() {
        return new BodyConstructor().construct();
    }

    @Override
    public boolean store() {
        if (warehouseController.pushItem(bodyToSupply)) {
            bodyToSupply = null;
            return true;
        }
        else {
            this.state = false;
            return false;
        }
    }
}
