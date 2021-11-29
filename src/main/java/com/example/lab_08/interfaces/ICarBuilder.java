package com.example.lab_08.interfaces;

import com.example.lab_08.classes.*;

public interface ICarBuilder extends IConstructor{
    public void reset();
    public Engine getEngine();
    public Body getBody();
    public Accessory getAccessory();
    public Car construct();
}
