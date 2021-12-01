package com.example.lab_08.classes.system;

import com.example.lab_08.classes.logger.MainLogger;
import com.example.lab_08.enums.WarehouseType;

import java.io.IOException;
import java.util.logging.Logger;

public class Program {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Factory factory;
    private DealerAgency dealerAgency;

    public void start() {
        initialize();

        LOGGER.info("Thread " + Thread.currentThread().getName() + ": Program starting");

        factory.start();
        dealerAgency.start();
    }

    private void initialize() {
        try {
            MainLogger.setup();
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }

        LOGGER.info("Thread " + Thread.currentThread().getName() + ": Program initialization");

        Settings settings = Settings.deserialize();
        factory = new Factory(settings);
        dealerAgency = new DealerAgency(settings, factory.getWarehouseController(WarehouseType.CAR_WAREHOUSE));
    }

    // Getters and setters region
    public Factory getFactory() {
        return factory;
    }

    public DealerAgency getDealerAgency() {
        return dealerAgency;
    }
}
