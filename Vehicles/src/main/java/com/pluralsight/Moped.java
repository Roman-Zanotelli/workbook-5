package com.pluralsight;

public class Moped extends Vehicle{
    protected Moped(String color, int numberOfPassengers, int cargoCapacity, int fuelCapacity) {
        super(color, numberOfPassengers, cargoCapacity, fuelCapacity);
    }
    public Moped(){
        super();
    }
}
