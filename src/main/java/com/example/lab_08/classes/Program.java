package com.example.lab_08.classes;

import com.example.lab_08.enums.WarehouseType;

public class Program {
    private Factory factory;
    private DealerAgency dealerAgency;

    public void start() {
        initialize();
        factory.start();
        dealerAgency.start();
    }

    private void initialize() {
        Settings settings = Settings.deserialize();
        factory = new Factory(settings);
        dealerAgency = new DealerAgency(settings, factory.getWarehouseController(WarehouseType.CAR_WAREHOUSE));
    }
}
