package com.example.lab_08.controllers;

import com.example.lab_08.classes.system.Program;
import com.example.lab_08.custom_controls.DetailSupplierCustomControl;
import com.example.lab_08.models.FactoryInfoModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

public class MainController {
    @FXML private final ObjectProperty<FactoryInfoModel> factoryInfo = new SimpleObjectProperty<>();
    @FXML
    private final StringProperty test = new SimpleStringProperty("test");
    @FXML
    private DetailSupplierCustomControl s1;

    public MainController() {

    }

    @FXML
    private void initialize() {
        Program p = new Program();
        p.start();

        setFactoryInfo(new FactoryInfoModel(p.getFactory(), p.getDealerAgency()));

        bindCustomControls();
    }

    @FXML
    private void apply() {

    }

    private void bindCustomControls() {
        s1.supplierCountProperty().bind(factoryInfo.get().supplierCountProperty().asString("<" + factoryInfo.get().getSupplierCount() + ">"));
        //s1.createdDetailsCountProperty().bind(factoryInfo.get());
        s1.stateProperty().bind(factoryInfo.get().getEngineSuppliers().stateProperty());
        int a = 0;
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

    public String getTest() {
        return test.get();
    }

    public StringProperty testProperty() {
        return test;
    }

    public void setTest(String test) {
        this.test.set(test);
    }
}