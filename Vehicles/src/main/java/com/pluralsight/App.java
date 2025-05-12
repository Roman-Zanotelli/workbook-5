package com.pluralsight;

public class App {
    public static void main(String[] args) {
        Moped slowRide = new Moped();
        slowRide.setColor("Red");
        slowRide.setFuelCapacity(5);
        Car vroom = new Car();
        vroom.setNumberOfPassengers(4);
        HoverCar fly = new HoverCar();
        fly.setColor("Green");
        SemiTruck truck = new SemiTruck();
        truck.setCargoCapacity(100);
    }
}
