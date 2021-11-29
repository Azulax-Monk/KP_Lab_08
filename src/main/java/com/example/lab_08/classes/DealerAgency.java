package com.example.lab_08.classes;

import java.util.ArrayList;

public class DealerAgency {
    private ArrayList<Dealer> dealers;
    private Settings settings;

    public DealerAgency(Settings settings) {
        this.dealers = new ArrayList<>();
        this.settings = settings;
    }

    public void start() {
        // Create threads
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (var d : dealers) {
                    d.run();
                }
            }
        });
        t1.start();
    }

    public void initialize(WarehouseController carWarehouseController) {
        for (int i = 0; i < settings.getDealerCount(); i++) {
            dealers.add(new Dealer(carWarehouseController));
        }
    }

}
