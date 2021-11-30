package com.example.lab_08.classes.suppliers;

import com.example.lab_08.classes.carParts.Accessory;
import com.example.lab_08.interfaces.IConstructor;

public class AccessoryConstructor implements IConstructor {
    @Override
    public Accessory construct() {
        return new Accessory("spoiler");
    }
}
