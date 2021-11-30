package com.example.lab_08.models;

import com.example.lab_08.classes.system.DealerAgency;
import com.example.lab_08.classes.system.Factory;
import com.example.lab_08.enums.WarehouseType;
import javafx.beans.property.*;
import javafx.fxml.FXML;

public class FactoryInfoModel {
    @FXML private IntegerProperty supplierCount = new SimpleIntegerProperty(0);
    @FXML private IntegerProperty dealerCount = new SimpleIntegerProperty(0);
    @FXML private ObjectProperty<SupplierModel> engineSuppliers = new SimpleObjectProperty<>();
    @FXML private ObjectProperty<SupplierModel> bodySuppliers = new SimpleObjectProperty<>();
    @FXML private ObjectProperty<SupplierModel> accessorySuppliers = new SimpleObjectProperty<>();
    @FXML private ObjectProperty<SupplierModel> carSuppliers = new SimpleObjectProperty<>();
    @FXML private ObjectProperty<WarehouseModel> engineWarehouse = new SimpleObjectProperty<>();
    @FXML private ObjectProperty<WarehouseModel> bodyWarehouse = new SimpleObjectProperty<>();
    @FXML private ObjectProperty<WarehouseModel> accessoryWarehouse = new SimpleObjectProperty<>();
    @FXML private ObjectProperty<WarehouseModel> carWarehouse = new SimpleObjectProperty<>();
    @FXML private ObjectProperty<DealerModel> dealers = new SimpleObjectProperty<>();

    public FactoryInfoModel(Factory factory, DealerAgency dealerAgency) {
        setEngineSuppliers(new SupplierModel(factory.getEngineSuppliers()));
        setBodySuppliers(new SupplierModel(factory.getBodySuppliers()));
        setAccessorySuppliers(new SupplierModel(factory.getAccessorySuppliers()));
        setCarSuppliers(new SupplierModel(factory.getCarSuppliers()));

        setEngineWarehouse(new WarehouseModel(factory.getWarehouseController(WarehouseType.ENGINE_WAREHOUSE)));
        setBodyWarehouse(new WarehouseModel(factory.getWarehouseController(WarehouseType.BODY_WAREHOUSE)));
        setAccessoryWarehouse(new WarehouseModel(factory.getWarehouseController(WarehouseType.ACCESSORY_WAREHOUSE)));
        setCarWarehouse(new WarehouseModel(factory.getWarehouseController(WarehouseType.CAR_WAREHOUSE)));

        setDealers(new DealerModel(dealerAgency.getDealers()));

        setDealerCount(dealerAgency.getDealers().size());
        setSupplierCount(factory.getEngineSuppliers().size());
    }

    // Getters and setters region
    public int getSupplierCount() {
        return supplierCount.get();
    }

    public IntegerProperty supplierCountProperty() {
        return supplierCount;
    }

    public void setSupplierCount(int supplierCount) {
        this.supplierCount.set(supplierCount);
    }

    public int getDealerCount() {
        return dealerCount.get();
    }

    public IntegerProperty dealerCountProperty() {
        return dealerCount;
    }

    public void setDealerCount(int dealerCount) {
        this.dealerCount.set(dealerCount);
    }

    // Suppliers
    public SupplierModel getEngineSuppliers() {
        return engineSuppliers.get();
    }

    public ObjectProperty<SupplierModel> engineSuppliersProperty() {
        return engineSuppliers;
    }

    public void setEngineSuppliers(SupplierModel engineSuppliers) {
        this.engineSuppliers.set(engineSuppliers);
    }

    public SupplierModel getBodySuppliers() {
        return bodySuppliers.get();
    }

    public ObjectProperty<SupplierModel> bodySuppliersProperty() {
        return bodySuppliers;
    }

    public void setBodySuppliers(SupplierModel bodySuppliers) {
        this.bodySuppliers.set(bodySuppliers);
    }

    public SupplierModel getAccessorySuppliers() {
        return accessorySuppliers.get();
    }

    public ObjectProperty<SupplierModel> accessorySuppliersProperty() {
        return accessorySuppliers;
    }

    public void setAccessorySuppliers(SupplierModel accessorySuppliers) {
        this.accessorySuppliers.set(accessorySuppliers);
    }

    public SupplierModel getCarSuppliers() {
        return carSuppliers.get();
    }

    public ObjectProperty<SupplierModel> carSuppliersProperty() {
        return carSuppliers;
    }

    public void setCarSuppliers(SupplierModel carSuppliers) {
        this.carSuppliers.set(carSuppliers);
    }

    // Warehouses
    public WarehouseModel getEngineWarehouse() {
        return engineWarehouse.get();
    }

    public ObjectProperty<WarehouseModel> engineWarehouseProperty() {
        return engineWarehouse;
    }

    public void setEngineWarehouse(WarehouseModel engineWarehouse) {
        this.engineWarehouse.set(engineWarehouse);
    }

    public WarehouseModel getBodyWarehouse() {
        return bodyWarehouse.get();
    }

    public ObjectProperty<WarehouseModel> bodyWarehouseProperty() {
        return bodyWarehouse;
    }

    public void setBodyWarehouse(WarehouseModel bodyWarehouse) {
        this.bodyWarehouse.set(bodyWarehouse);
    }

    public WarehouseModel getAccessoryWarehouse() {
        return accessoryWarehouse.get();
    }

    public ObjectProperty<WarehouseModel> accessoryWarehouseProperty() {
        return accessoryWarehouse;
    }

    public void setAccessoryWarehouse(WarehouseModel accessoryWarehouse) {
        this.accessoryWarehouse.set(accessoryWarehouse);
    }

    public WarehouseModel getCarWarehouse() {
        return carWarehouse.get();
    }

    public ObjectProperty<WarehouseModel> carWarehouseProperty() {
        return carWarehouse;
    }

    public void setCarWarehouse(WarehouseModel carWarehouse) {
        this.carWarehouse.set(carWarehouse);
    }

    // Dealers
    public DealerModel getDealers() {
        return dealers.get();
    }

    public ObjectProperty<DealerModel> dealersProperty() {
        return dealers;
    }

    public void setDealers(DealerModel dealers) {
        this.dealers.set(dealers);
    }
}
