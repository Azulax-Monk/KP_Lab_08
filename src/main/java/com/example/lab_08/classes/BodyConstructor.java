package com.example.lab_08.classes;

import com.example.lab_08.interfaces.IConstructor;

public class BodyConstructor implements IConstructor {
    @Override
    public Body construct() {
        return new Body();
    }
}
