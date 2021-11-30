package com.example.lab_08.interfaces;

import com.example.lab_08.classes.carParts.Accessory;
import com.example.lab_08.classes.carParts.Body;
import com.example.lab_08.classes.carParts.Car;
import com.example.lab_08.classes.carParts.Engine;

public interface ICarBuilder extends IConstructor{
    public void reset();
    public Engine getEngine();
    public Body getBody();
    public Accessory getAccessory();
    public Car construct();
}
