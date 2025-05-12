package com.pluralsight;

public class HoverCar extends Vehicle{
    protected HoverCar(String color, int numberOfPassengers, int cargoCapacity, int fuelCapacity) {
        super(color, numberOfPassengers, cargoCapacity, fuelCapacity);
    }
    public HoverCar(){
        super();
    }
}
