package com.example.lab_08.classes;

import com.example.lab_08.interfaces.ICarBuilder;
import com.example.lab_08.interfaces.ISupplier;

public class CarSupplier implements ISupplier {
    private Car carToSupply;
    private WarehouseController carWarehouseController;
    private ICarBuilder carConstructor;
    private boolean state;

    public CarSupplier(WarehouseController carWC, WarehouseController engineWC,
                       WarehouseController bodyWC, WarehouseController accessoryWC) {
        this.carWarehouseController = carWC;
        this.carConstructor = new CarConstructor(engineWC, bodyWC, accessoryWC);
        this.state = false;
    }

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
