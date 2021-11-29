package com.example.lab_08.classes;

import com.example.lab_08.enums.WarehouseType;
import com.example.lab_08.interfaces.IWarehouse;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Factory {
    private ArrayList<CarSupplier> carSuppliers;
    private ArrayList<EngineSupplier> engineSuppliers;
    private ArrayList<BodySupplier> bodySuppliers;
    private ArrayList<AccessorySupplier> accessorySuppliers;
    private ArrayList<IWarehouse> warehouses;
    private ArrayList<WarehouseController> warehouseControllers;
    private Settings settings;

    public Factory(Settings settings) {
        carSuppliers = new ArrayList<>();
        engineSuppliers = new ArrayList<>();
        bodySuppliers = new ArrayList<>();
        accessorySuppliers = new ArrayList<>();
        this.settings = settings;
        initialize();
    }

    public void start() {
        // start all suppliers + warehouses?
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (var supplier: carSuppliers)
                    supplier.run();
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (var supplier: engineSuppliers)
                    supplier.run();
            }
        });
        t2.start();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (var supplier: bodySuppliers)
                    supplier.run();
            }
        });
        t3.start();


        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (var supplier: accessorySuppliers)
                    supplier.run();
            }
        });
        t4.start();
    }

    private void initialize() {
        carSuppliers = new ArrayList<>();
        engineSuppliers = new ArrayList<>();
        bodySuppliers = new ArrayList<>();
        accessorySuppliers = new ArrayList<>();
        warehouses = new ArrayList<>();
        warehouseControllers = new ArrayList<>();

        CarWarehouse carWH = new CarWarehouse(settings.getCarWarehouseSize());
        warehouses.add(carWH);

        CarPartWarehouse engineWH = new CarPartWarehouse(settings.getEngineWarehouseSize(), WarehouseType.ENGINE_WAREHOUSE);             // to be upd
        warehouses.add(engineWH);

        CarPartWarehouse bodyWH = new CarPartWarehouse(settings.getBodyWarehouseSize(), WarehouseType.BODY_WAREHOUSE);
        warehouses.add(bodyWH);

        CarPartWarehouse accessoryWH = new CarPartWarehouse(settings.getAccessoryWarehouseSize(), WarehouseType.ACCESSORY_WAREHOUSE);
        warehouses.add(accessoryWH);

        warehouseControllers.add(new WarehouseController(getWarehouse(WarehouseType.CAR_WAREHOUSE)));       // hardcode
        warehouseControllers.add(new WarehouseController(getWarehouse(WarehouseType.BODY_WAREHOUSE)));
        warehouseControllers.add(new WarehouseController(getWarehouse(WarehouseType.ENGINE_WAREHOUSE)));
        warehouseControllers.add(new WarehouseController(getWarehouse(WarehouseType.ACCESSORY_WAREHOUSE)));

        for (int i = 0; i < settings.getSupplierCount(); i++) {
            carSuppliers.add(new CarSupplier(
                            getWarehouseController(WarehouseType.CAR_WAREHOUSE),
                            getWarehouseController(WarehouseType.ENGINE_WAREHOUSE),
                            getWarehouseController(WarehouseType.BODY_WAREHOUSE),
                            getWarehouseController(WarehouseType.ACCESSORY_WAREHOUSE)
                            ));
            engineSuppliers.add(new EngineSupplier(getWarehouseController(WarehouseType.ENGINE_WAREHOUSE)));
            bodySuppliers.add(new BodySupplier(getWarehouseController(WarehouseType.BODY_WAREHOUSE)));
            accessorySuppliers.add(new AccessorySupplier(getWarehouseController(WarehouseType.ACCESSORY_WAREHOUSE)));
        }
    }

    public IWarehouse getWarehouse(WarehouseType type) {
        return warehouses.stream().filter(warehouse -> warehouse.getType() ==
                type).findFirst().get();
    }

    public WarehouseController getWarehouseController(WarehouseType type) {
        return warehouseControllers.stream().filter(controller -> controller.getWarehouse().getType() ==
                type).findFirst().get();
    }
}