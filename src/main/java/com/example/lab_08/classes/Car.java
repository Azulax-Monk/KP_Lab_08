package com.example.lab_08.classes;

import java.util.concurrent.atomic.AtomicInteger;

public class Car {
    static final AtomicInteger NEXT_ID = new AtomicInteger(1);
    private final int id = NEXT_ID.getAndIncrement();
    private String name;
    private Body body;
    private Engine engine;
    private Accessory accessory;

    public Car(Body b, Engine e, Accessory a) {
        name = "Car";
        body = b;
        engine = e;
        accessory = a;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
