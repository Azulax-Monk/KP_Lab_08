package com.example.lab_08.classes;

public class Accessory extends CarPart{
    private String type;

    public Accessory(String type) {
        super("Accessory");
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
