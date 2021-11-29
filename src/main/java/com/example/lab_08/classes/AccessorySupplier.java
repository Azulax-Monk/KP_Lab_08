package com.example.lab_08.classes;

import com.example.lab_08.interfaces.ISupplier;

public class AccessorySupplier implements ISupplier {
    private Accessory accessoryToSupply;
    private WarehouseController warehouseController;
    private boolean state;

    @Override
    public void run() {
        while (true) {
            // wait some time
            if (this.state) {
                accessoryToSupply = orderToConstruct();
                store();
            }
            else {
                while (!this.state) ;
            }
        }
    }

    @Override
    public Accessory orderToConstruct() {
        return new AccessoryConstructor().construct();
    }

    @Override
    public boolean store() {
        if (warehouseController.pushItem(accessoryToSupply)) {
            accessoryToSupply = null;
            return true;
        }
        else {
            this.state = false;
            return false;
        }
    }
}
