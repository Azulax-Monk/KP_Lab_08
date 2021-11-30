package com.example.lab_08.classes;

import java.util.ArrayList;

public class DealerAgency {
    private ArrayList<Dealer> dealers;
    private Settings settings;

    public DealerAgency(Settings settings, WarehouseController warehouseController) {
        this.dealers = new ArrayList<>();
        this.settings = settings;
        initialize(warehouseController);
    }

    public void start() {
        // Create threads
        for (var d : dealers) {
            ThreadPool.getInstance().executeRunnable(new Runnable() {
                @Override
                public void run() {
                    d.run();
                }
            });
        }
    }

    public void initialize(WarehouseController carWarehouseController) {
        for (int i = 0; i < settings.getDealerCount(); i++) {
            dealers.add(new Dealer(carWarehouseController));
        }
    }

}
