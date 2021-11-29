package com.example.lab_08.Classes;

import com.example.lab_08.Classes.Interfaces.ICarBuilder;
import com.example.lab_08.Classes.Interfaces.ISupplier;

public class CarSupplier implements ISupplier {
    private Car carToSupply;
    private WarehouseController carWarehouseController;
    private ICarBuilder carConstructor;
    private boolean state;

    @Override
    public void run() {
        while (true) {
            // wait some time
            if (this.state) {
                carToSupply = orderToConstruct();
                store();
            }
            else {
                while (!this.state) ;
            }
        }
    }

    @Override
    public Car orderToConstruct() {
        return carConstructor.construct();
    }

    @Override
    public boolean store() {
        if (carWarehouseController.pushItem(carToSupply)) {
            carToSupply = null;
            return true;
        }
        else {
            this.state = false;
            return false;
        }
    }
}
