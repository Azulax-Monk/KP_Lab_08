package com.example.lab_08.controllers;

import com.example.lab_08.classes.system.Program;
import com.example.lab_08.classes.threads.ThreadPool;
import com.example.lab_08.custom_controls.CarSupplierCustomControl;
import com.example.lab_08.custom_controls.DealerCustomControl;
import com.example.lab_08.custom_controls.DetailSupplierCustomControl;
import com.example.lab_08.custom_controls.WarehouseCustomControl;
import com.example.lab_08.models.FactoryInfoModel;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class MainController {
    @FXML private final ObjectProperty<FactoryInfoModel> factoryInfo = new SimpleObjectProperty<>();
    @FXML private DetailSupplierCustomControl s1;
    @FXML private DetailSupplierCustomControl s2;
    @FXML private DetailSupplierCustomControl s3;
    @FXML private CarSupplierCustomControl s4;
    @FXML private WarehouseCustomControl w1;
    @FXML private WarehouseCustomControl w2;
    @FXML private WarehouseCustomControl w3;
    @FXML private WarehouseCustomControl w4;
    @FXML private DealerCustomControl d1;
    @FXML private Slider engineSpeedSlider;
    @FXML private Slider bodySpeedSlider;
    @FXML private Slider accessorySpeedSlider;
    @FXML private Slider carSpeedSlider;
    @FXML private Slider dealerSpeedSlider;

    public MainController() {

    }

    @FXML
    private void initialize() {
        engineSpeedSlider.setMax(9000);
        engineSpeedSlider.setMin(1000);

        bodySpeedSlider.setMax(9000);
        bodySpeedSlider.setMin(1000);

        accessorySpeedSlider.setMax(9000);
        accessorySpeedSlider.setMin(1000);

        carSpeedSlider.setMax(9000);
        carSpeedSlider.setMin(1000);

        dealerSpeedSlider.setMax(9000);
        dealerSpeedSlider.setMin(1000);
    }

    @FXML
    private void start() {
        Program p = new Program();
        p.start();

        setFactoryInfo(new FactoryInfoModel(p.getFactory(), p.getDealerAgency()));

        binding();
    }

    @FXML
    private void stop() {
        ThreadPool.getInstance().cleanUp();
    }

    private void binding() {
        bindSuppliersCustomControls();
        bindWarehousesCustomControl();
        bindDealersCustomControls();
        bindSpeedTimeSliders();
    }

    private void bindSuppliersCustomControls() {
        s1.supplierCountProperty().bind(factoryInfo.get().engineSuppliersProperty().get().supplierCountProperty().asString());
        s1.createdItemsCountProperty().bind(factoryInfo.get().getEngineWarehouse().createdItemsCountProperty().asString());
        s1.stateProperty().bind(factoryInfo.get().getEngineSuppliers().stateProperty());

        s2.supplierCountProperty().bind(factoryInfo.get().bodySuppliersProperty().get().supplierCountProperty().asString());
        s2.createdItemsCountProperty().bind(factoryInfo.get().getBodyWarehouse().createdItemsCountProperty().asString());
        s2.stateProperty().bind(factoryInfo.get().getBodySuppliers().stateProperty());

        s3.supplierCountProperty().bind(factoryInfo.get().accessorySuppliersProperty().get().supplierCountProperty().asString());
        s3.createdItemsCountProperty().bind(factoryInfo.get().getAccessoryWarehouse().createdItemsCountProperty().asString());
        s3.stateProperty().bind(factoryInfo.get().getAccessorySuppliers().stateProperty());

        s4.supplierCountProperty().bind(factoryInfo.get().carSuppliersProperty().get().supplierCountProperty().asString());
        s4.createdItemsCountProperty().bind(factoryInfo.get().getCarWarehouse().createdItemsCountProperty().asString());
        s4.stateProperty().bind(factoryInfo.get().getCarSuppliers().stateProperty());
    }

    private void bindWarehousesCustomControl() {
        w1.storedItemsCountProperty().bind(factoryInfo.get().getEngineWarehouse().storedItemsCountProperty().asString());
        w1.stateProperty().bind(factoryInfo.get().getEngineWarehouse().stateProperty());

        w2.storedItemsCountProperty().bind(factoryInfo.get().getBodyWarehouse().storedItemsCountProperty().asString());
        w2.stateProperty().bind(factoryInfo.get().getBodyWarehouse().stateProperty());

        w3.storedItemsCountProperty().bind(factoryInfo.get().getAccessoryWarehouse().storedItemsCountProperty().asString());
        w3.stateProperty().bind(factoryInfo.get().getAccessoryWarehouse().stateProperty());

        w4.storedItemsCountProperty().bind(factoryInfo.get().getCarWarehouse().storedItemsCountProperty().asString());
        w4.stateProperty().bind(factoryInfo.get().getCarWarehouse().stateProperty());
    }

    private void bindDealersCustomControls() {
        d1.dealerCountProperty().bind(factoryInfo.get().getDealers().dealersCountProperty().asString());
        d1.boughtCarsCountProperty().bind(factoryInfo.get().getDealers().boughtCarsCountProperty().asString());
        d1.stateProperty().bind(factoryInfo.get().getDealers().stateProperty());
    }

    private void bindSpeedTimeSliders() {
        engineSpeedSlider.valueProperty().addListener(
                (ObservableValue<? extends Number> observableValue, Number oldV, Number newV) ->
                {
                    factoryInfo.get().engineSuppliersProperty().get().setSpeedTime(newV.intValue());
                });

        bodySpeedSlider.valueProperty().addListener(
                (ObservableValue<? extends Number> observableValue, Number oldV, Number newV) ->
                {
                    factoryInfo.get().bodySuppliersProperty().get().setSpeedTime(newV.intValue());
                });

        accessorySpeedSlider.valueProperty().addListener(
                (ObservableValue<? extends Number> observableValue, Number oldV, Number newV) ->
                {
                    factoryInfo.get().accessorySuppliersProperty().get().setSpeedTime(newV.intValue());
                });

        carSpeedSlider.valueProperty().addListener(
                (ObservableValue<? extends Number> observableValue, Number oldV, Number newV) ->
                {
                    factoryInfo.get().carSuppliersProperty().get().setSpeedTime(newV.intValue());
                });

        dealerSpeedSlider.valueProperty().addListener(
                (ObservableValue<? extends Number> observableValue, Number oldV, Number newV) ->
                {
                    factoryInfo.get().dealersProperty().get().setSpeedTime(newV.intValue());
                });



//        engineSpeedSlider.valueProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number oldV, Number newV) {
//                factoryInfo.get().bodySuppliersProperty().get().setSpeedTime(newV.intValue());
//            }
//        });
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