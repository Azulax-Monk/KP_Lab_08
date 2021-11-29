package com.example.lab_08.Classes.Interfaces;

public interface ICarBuilder extends IConstructor{
    public void reset();
    public Engine getEngine();
    public Body getBody();
    public Accessory getAccessory();
    public Car construct();
}
