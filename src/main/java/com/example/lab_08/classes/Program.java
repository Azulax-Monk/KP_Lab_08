package com.example.lab_08.classes;

public class Program {
    private Factory factory;
    private DealerAgency dealerAgency;

    public void start() {
        initialize();
        factory.start();
        dealerAgency.start();
    }

    private void initialize() {
        Settings settings = new Settings();
        factory = new Factory(settings);
        dealerAgency = new DealerAgency(settings);
    }
}
