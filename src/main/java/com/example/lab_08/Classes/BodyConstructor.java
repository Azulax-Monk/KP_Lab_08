package com.example.lab_08.Classes;

import com.example.lab_08.Classes.Interfaces.IConstructor;

public class BodyConstructor implements IConstructor {
    @Override
    public Body construct() {
        return new Body();
    }
}
