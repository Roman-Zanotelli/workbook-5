package com.pluralsight;

public abstract class Vehicle {
    protected String color;
    protected int numberOfPassengers;
    protected int cargoCapacity;
    protected int fuelCapacity;

    protected Vehicle(String color, int numberOfPassengers, int cargoCapacity, int fuelCapacity) {
        this.color = color;
        this.numberOfPassengers = numberOfPassengers;
        this.cargoCapacity = cargoCapacity;
        this.fuelCapacity = fuelCapacity;
    }
    protected Vehicle(){

    }

    public final String getColor() {
        return color;
    }

    public final int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public final int getFuelCapacity() {
        return fuelCapacity;
    }

    public final int getCargoCapacity() {
        return cargoCapacity;
    }

    public final void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public final void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public final void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public final void setColor(String color) {
        this.color = color;
    }
}
