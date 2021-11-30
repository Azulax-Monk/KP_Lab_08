package com.example.lab_08.classes.suppliers;

import com.example.lab_08.classes.carParts.Engine;
import com.example.lab_08.interfaces.IConstructor;

public class EngineConstructor implements IConstructor {
    @Override
    public Engine construct() {
        return new Engine(100);
    }
}
