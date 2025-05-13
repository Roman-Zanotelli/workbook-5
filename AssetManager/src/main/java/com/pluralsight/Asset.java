package com.pluralsight;

public class Asset {
    //Vars
    protected String description, dateAcquired;
    protected double originalCost;

    public Asset(String description, String dateAcquired, double originalCost) {
        this.description = description;
        this.dateAcquired = dateAcquired;
        this.originalCost = originalCost;
    }

    //Methods
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(String dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    public double getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(double originalCost) {
        this.originalCost = originalCost;
    }

    public double getValue(){
        return originalCost;
    }

    @Override
    public String toString() {
        //Handles formatting the description, date acquired, original cost and current value of all assets in one function
        return String.format("Description: %s | Date Acquired: %s | Original Cost: %.2f | Current Value: %.2f", description, dateAcquired, originalCost, getValue());
    }
}
