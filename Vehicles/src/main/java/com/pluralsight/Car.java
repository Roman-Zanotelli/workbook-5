package com.pluralsight;

public class Car extends Vehicle{
    protected Car(String color, int numberOfPassengers, int cargoCapacity, int fuelCapacity) {
        super(color, numberOfPassengers, cargoCapacity, fuelCapacity);
    }
    public Car(){
        super();
    }
}
