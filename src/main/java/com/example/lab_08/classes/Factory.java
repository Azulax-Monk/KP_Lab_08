package com.example.lab_08.classes;

import com.example.lab_08.enums.WarehouseType;
import com.example.lab_08.interfaces.ISupplier;
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
        for (var supplier: carSuppliers)
            ThreadPool.getInstance().executeRunnable(new Runnable() {
                @Override
                public void run() {
                    supplier.run();
                }
            });


        for (var supplier: engineSuppliers)
            ThreadPool.getInstance().executeRunnable(new Runnable() {
                @Override
                public void run() {
                    supplier.run();
                }
            });

        for (var supplier: bodySuppliers)
            ThreadPool.getInstance().executeRunnable(new Runnable() {
                @Override
                public void run() {
                    supplier.run();
                }
            });


        for (var supplier: accessorySuppliers)
            ThreadPool.getInstance().executeRunnable(new Runnable() {
                @Override
                public void run() {
                    supplier.run();
                }
            });
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

        warehouseControllers.get(0).setSuppliers(carSuppliers);
        warehouseControllers.get(1).setSuppliers(engineSuppliers);
        warehouseControllers.get(2).setSuppliers(bodySuppliers);
        warehouseControllers.get(3).setSuppliers(accessorySuppliers);

/*
        for (var carSupplier: carSuppliers)
            warehouseControllers.get(0).setSuppliers(carSupplier);
        for (var carSupplier: engineSuppliers)
            warehouseControllers.get(1).setSuppliers(engineSuppliers);
        for (var carSupplier: carSuppliers)
            warehouseControllers.get(2).setSuppliers(bodySuppliers);
        for (var carSupplier: carSuppliers)
            warehouseControllers.get(3).setSuppliers(accessorySuppliers);*/
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