package com.pluralsight;

import java.time.LocalDate;

public class Vehicle extends Asset{
    private String makeModel;
    private int year, odometer;

    public Vehicle(String description, String dateAcquired, double originalCost, String makeModel, int year, int odometer) {
        super(description, dateAcquired, originalCost);
        this.makeModel = makeModel;
        this.year = year;
        this.odometer = odometer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    @Override
    public double getValue() {
        //TODO:
        // A car's value is determined as
        // 0-3 years old - 3% reduced value of cost per year
        // 4-6 years old - 6% reduced value of cost per year
        // 7-10 years old - 8% reduced value of cost per year
        // over 10 years old - $1000.00
        // MINUS reduce final value by 25% if over 100,000 miles
        // unless makeModel contains word Honda or Toyota
        // Calculate value depreciation
        double price = this.originalCost;

        //get the age of the car
        int age = LocalDate.now().getYear() - this.year;

        //depreciate based off age
        if (age <= 3){
            price *= Math.pow(.97, age); //price is set to accumulative depreciation over 0-3 years
        } else if(age <= 6){
            price *= Math.pow(.94, age); //price is set to accumulative depreciation over 4-6 years
        } else if (age <= 10) {
            price *= Math.pow(.92, age); //price is set to accumulative depreciation over 7-10 years
        } else{
            price = 1000; //flat price for vehicles over 10 years old
        }

        //checks if the odometer is OVER 100k miles AND the car is NOT (Honda OR Toyota)
        if(odometer > 100000 && !(makeModel.contains("Honda") || makeModel.contains("Toyota"))){
            price *= .75;
        }



        //return final price
        return price;
//        return (age > 10 ? 1000 : this.originalCost * Math.pow(year <= 3 ? .97: year <= 6? .94 : .92, age)) * (makeModel.contains("Honda") || makeModel.contains("Toyota") || odometer <= 100000 ? 1 : .75);

    }

    @Override
    public String toString() {
        //Appends on the year and make/model on top of the Asset.toString() format
        return String.format("%s | Year: %d | Make/Model: %s" , super.toString(), year, makeModel);
    }
}
