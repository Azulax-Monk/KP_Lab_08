package com.example.lab_08.classes;

public class Settings {
    private int carWarehouseSize;
    private int engineWarehouseSize;
    private int bodyWarehouseSize;
    private int accessoryWarehouseSize;
    private int supplierCount;
    private int dealerCount;
    private final String filepath = "settings.config";

    public Settings() {

    }

    private void deserialize() {

    }

    public int getCarWarehouseSize() {
        return carWarehouseSize;
    }

    public int getEngineWarehouseSize() {
        return engineWarehouseSize;
    }

    public int getBodyWarehouseSize() {
        return bodyWarehouseSize;
    }

    public int getAccessoryWarehouseSize() {
        return accessoryWarehouseSize;
    }

    public int getSupplierCount() {
        return supplierCount;
    }

    public int getDealerCount() {
        return dealerCount;
    }

    public String getFilepath() {
        return filepath;
    }
}
