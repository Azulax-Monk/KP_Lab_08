package com.example.lab_08.Classes;

import com.example.lab_08.Classes.Interfaces.IConstructor;

public class EngineConstructor implements IConstructor {
    @Override
    public Engine construct() {
        return new Engine();
    }
}
