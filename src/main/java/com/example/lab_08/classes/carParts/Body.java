package com.example.lab_08.classes.carParts;

public class Body extends CarPart {
    private String material;

    public Body(String material) {
        super("Body");
        this.material = material;
    }

    public String getMaterial() {
        return  material;
    }
}
