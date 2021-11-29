package com.example.lab_08.classes;

import java.util.Random;

public class Dealer {
    private WarehouseController carWarehouseController;
    private boolean state;
    private Car car;

    public Dealer(WarehouseController carWarehouseController) {
        this.carWarehouseController = carWarehouseController;
        state = true;
        car = null;
    }

    public void run() {

    }

    public boolean takeCar() {
        car = (Car) carWarehouseController.popItem();
        if (car != null) {
            return  true;
        }
        else {
            return false;
        }
    }

    public boolean sellCar() {
        return true;
    }
}
