package com.example.lab_08.classes.suppliers;

import com.example.lab_08.classes.carParts.Accessory;
import com.example.lab_08.classes.carParts.Body;
import com.example.lab_08.classes.carParts.Car;
import com.example.lab_08.classes.carParts.Engine;
import com.example.lab_08.classes.warehouses.WarehouseController;
import com.example.lab_08.interfaces.ICarBuilder;
import com.example.lab_08.interfaces.IConstructor;

public class EngineConstructor implements IConstructor {
    @Override
    public Engine construct() {
        return new Engine(100);
    }
}
