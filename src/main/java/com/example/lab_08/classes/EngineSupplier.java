package com.example.lab_08.classes;

import com.example.lab_08.interfaces.ISupplier;

public class EngineSupplier implements ISupplier {
    private Engine engineToSupply;
    private WarehouseController warehouseController;
    private boolean state;

    @Override
    public void run() {
        while (true) {
            // wait some time
            if (this.state) {
                engineToSupply = orderToConstruct();
                store();
            }
            else {
                while (!this.state) ;
            }
        }
    }

    @Override
    public Engine orderToConstruct() {
        return new EngineConstructor().construct();
    }

    @Override
    public boolean store() {
        if (warehouseController.pushItem(engineToSupply)) {
            engineToSupply = null;
            return true;
        }
        else {
            this.state = false;
            return false;
        }
    }
}
