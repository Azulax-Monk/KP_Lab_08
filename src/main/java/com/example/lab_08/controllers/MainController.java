package com.example.lab_08.controllers;

import com.example.lab_08.classes.system.Program;
import com.example.lab_08.custom_controls.CarSupplierCustomControl;
import com.example.lab_08.custom_controls.DealerCustomControl;
import com.example.lab_08.custom_controls.DetailSupplierCustomControl;
import com.example.lab_08.custom_controls.WarehouseCustomControl;
import com.example.lab_08.models.FactoryInfoModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

public class MainController {
    @FXML private final ObjectProperty<FactoryInfoModel> factoryInfo = new SimpleObjectProperty<>();
    @FXML private DetailSupplierCustomControl s1;
    @FXML private DetailSupplierCustomControl s2;
    @FXML private DetailSupplierCustomControl s3;
    @FXML private CarSupplierCustomControl s4;
    @FXML private WarehouseCustomControl w1;
    @FXML private WarehouseCustomControl w2;
    @FXML private WarehouseCustomControl w3;
    @FXML private DealerCustomControl d1;

    public MainController() {

    }

    @FXML
    private void initialize() {
        Program p = new Program();
        p.start();

        setFactoryInfo(new FactoryInfoModel(p.getFactory(), p.getDealerAgency()));

        bindCustomControls();
    }

    private void bindCustomControls() {
        int a = 0;
    }

    private void bindSuppliersCustomControls() {
        s1.supplierCountProperty().bind(factoryInfo.get().supplierCountProperty().asString("<" + factoryInfo.get().getSupplierCount() + ">"));
        s1.createdDetailsCountProperty().bind(factoryInfo.get().getEngineWarehouse().createdItemsCountProperty().asString());
        s1.stateProperty().bind(factoryInfo.get().getEngineSuppliers().stateProperty());
    }

    private void bindWarehousesCustomControl() {

    }

    private void bindDealersCustomControls() {

    }


    // Getters and setters region
    public FactoryInfoModel getFactoryInfo() {
        return factoryInfo.get();
    }

    public ObjectProperty<FactoryInfoModel> factoryInfoProperty() {
        return factoryInfo;
    }

    public void setFactoryInfo(FactoryInfoModel factoryInfo) {
        this.factoryInfo.set(factoryInfo);
    }
}