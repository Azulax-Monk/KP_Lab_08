package com.example.lab_08.classes.carParts;

import java.util.concurrent.atomic.AtomicInteger;

public class CarPart {
    static final AtomicInteger NEXT_ID = new AtomicInteger(1);
    protected final int id = NEXT_ID.getAndIncrement();
    protected String name;

    public CarPart(String name) {
        this.name = name;
    }

    public int getId() {
        return  id;
    }

    public String getName() {
        return name;
    }

}
