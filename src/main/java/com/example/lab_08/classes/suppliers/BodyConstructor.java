package com.example.lab_08.classes.suppliers;

import com.example.lab_08.classes.carParts.Body;
import com.example.lab_08.interfaces.IConstructor;

public class BodyConstructor implements IConstructor {
    @Override
    public Body construct() {
        return new Body("metal");
    }
}
