package com.example.lab_08.classes;

import com.example.lab_08.interfaces.ICarBuilder;

public class CarConstructor implements ICarBuilder {
    private WarehouseController engineWarehouseController;
    private WarehouseController bodyWarehouseController;
    private WarehouseController accessoryWarehouseController;

    public void reset() {
        // to be upd
    }

    public CarConstructor(WarehouseController engineWC, WarehouseController bodyWC, WarehouseController accessoryWC) {
        this.engineWarehouseController = engineWC;
        this.bodyWarehouseController = bodyWC;
        this.accessoryWarehouseController = accessoryWC;
    }

    @Override
    private Engine getEngine() {
        return engineWarehouseController.popItem();
    }

    @Override
    private Body getBody() {
        return bodyWarehouseController.popItem();
    }

    @Override
    private Accessory getAccessory() {
        return accessoryWarehouseController.popItem();
    }

    @Override
    public Car construct() {
        Engine engine = getEngine();
        Body body = getBody();
        Accessory accessory = getAccessory();

        if (engine == null || body == null || accessory == null)
            return null;
        else
            return new Car(engine, body, accessory);
    }
}