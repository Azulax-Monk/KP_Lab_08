package com.example.lab_08.classes;

import java.util.ArrayList;

public class Factory {
    private ArrayList<CarSupplier> carSuppliers;
    private ArrayList<EngineSupplier> engineSuppliers;
    private ArrayList<BodySupplier> bodySuppliers;
    private ArrayList<AccessorySupplier> accessorySuppliers;
    private ArrayList<IWarehouse> warehouses;
    private ArrayList<WarehouseController> warehouseControllers;
    private Settings settings;

    public Factory(Settings settings) {
        this.settings = settings;
    }

    public void start() {
        // start all suppliers + warehouses
        for (var supplier: carSuppliers)
            supplier.run();

        // ...
    }

    private void initialize() {
        CarWarehouse carWH = new CarWarehouse(settings.carWarehouseSize);
        carWH.type = WarehouseType.CAR_WAREHOUSE;
        warehouses.add(carWH);

        CarPartWarehouse engineWH = new CarPartWarehouse(settings.engineWarehouseSize);             // to be upd
        engineWH.type = WarehouseType.ENGINE_WAREHOUSE;
        warehouses.add(engineWH);

        CarPartWarehouse bodyWH = new CarPartWarehouse(settings.bodyWarehouseSize);
        bodyWH.type = WarehouseType.BODY_WAREHOUSE;
        warehouses.add(bodyWH);

        CarPartWarehouse accessoryWH = new CarPartWarehouse(settings.accessoryWarehouseSize);
        accessoryWH.type = WarehouseType.ACCESSORY_WAREHOUSE;
        warehouses.add(accessoryWH);

        warehouseControllers.add(new WarehouseController(getWarehouse(WarehouseType.CAR_WAREHOUSE)));       // hardcode
        warehouseControllers.add(new WarehouseController(getWarehouse(WarehouseType.ENGINE_WAREHOUSE)));
        warehouseControllers.add(new WarehouseController(getWarehouse(WarehouseType.BODY_WAREHOUSE)));
        warehouseControllers.add(new WarehouseController(getWarehouse(WarehouseType.ACCESSORY_WAREHOUSE)));

        for (int i = 0; i < settings.supplierCount; i++) {
            carSuppliers.add(new CarSupplier(getWarehouseController(WarehouseType.CAR_WAREHOUSE)));
            carSuppliers.add(new CarSupplier(getWarehouseController(WarehouseType.ENGINE_WAREHOUSE)));
            carSuppliers.add(new CarSupplier(getWarehouseController(WarehouseType.BODY_WAREHOUSE)));
            carSuppliers.add(new CarSupplier(getWarehouseController(WarehouseType.ACCESSORY_WAREHOUSE)));
        }
    }

    public WarehouseController getWarehouse(WarehouseType type) {
        return warehouses.stream().filter(warehouse -> warehouse.type ==
                type.CAR_WAREHOUSE).findFirst().get();
    }

    public WarehouseController getWarehouseController(WarehouseType type) {
        return warehouseControllers.stream().filter(controller -> controller.assignedWarehouse.type ==
                type.CAR_WAREHOUSE).findFirst().get();
    }
}