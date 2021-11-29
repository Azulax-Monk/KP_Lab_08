package com.example.lab_08.Classes;

import com.example.lab_08.Classes.Interfaces.IConstructor;

public class AccessoryConstructor implements IConstructor {
    @Override
    public Accessory construct() {
        return new Accessory();
    }
}
