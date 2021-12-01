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

    public static class CarConstructor implements ICarBuilder {
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
        public Engine getEngine() { return (Engine) engineWarehouseController.popItem(); }

        @Override
        public Body getBody() {
            return (Body) bodyWarehouseController.popItem();
        }

        @Override
        public Accessory getAccessory() { return (Accessory) accessoryWarehouseController.popItem(); }

        @Override
        public Car construct() {
            Engine engine = getEngine();
            Body body = getBody();
            Accessory accessory = getAccessory();

            if (engine == null || body == null || accessory == null)
                return null;
            else
                return new Car(body, engine, accessory);
        }
    }
}
