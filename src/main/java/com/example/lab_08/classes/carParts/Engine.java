package com.example.lab_08.classes.carParts;

import com.example.lab_08.classes.carParts.CarPart;

public class Engine extends CarPart {
    private int power;

    public Engine(int power) {
        super("Engine");
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}
