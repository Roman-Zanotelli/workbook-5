package com.pluralsight;

public class House extends Asset{
    String address;
    int condition, squareFoot, lotSize;

    public House(String description, String dateAcquired, double originalCost, String address, int condition, int squareFoot, int lotSize) {
        super(description, dateAcquired, originalCost);
        this.address = address;
        this.condition = condition;
        this.squareFoot = squareFoot;
        this.lotSize = lotSize;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getSquareFoot() {
        return squareFoot;
    }

    public void setSquareFoot(int squareFoot) {
        this.squareFoot = squareFoot;
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    @Override
    public double getValue() {
        //TODO:
        // Check house condition to determine price per square foot (ppsf)
        // float ppsf = condition == 4 ? 180: condition == 3 ? 130 : condition == 2 ? 90 : condition == 1? 80 : 0;
        // calculate total price ppsf * squarfoot
        // lotsize * 0.25

        //     if condition==4 ppsf=180, condition==3 ppsf=130, condition==2 ppsf=90, else ppsf=80 THEN multiply ppsf by squareFootage THEN add lotsize value for .25 per square foot
        return (condition == 4 ? 180: condition == 3 ? 130 : condition == 2 ? 90 : 80) * squareFoot + lotSize * .25;
    }

    @Override
    public String toString() {
        //Appends on the address of the house on top of the Asset.toString() format
        return String.format("%s | Address: %s", super.toString(), address);
    }
}
