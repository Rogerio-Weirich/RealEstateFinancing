package model;

import java.io.Serializable;

public class Apartment extends Financing implements Serializable {
    private static final long serialVersionUID = 1L;
    private int garageSpots;
    private int floorNumber;

    /*
    Constructor for Apartment class
    Calls superclass (Finacing) to initialize Attributes
     */
    public Apartment(double targetPropertyValue, int financingTermInYears, double annualInterestRate, int garageSpots, int floorNumber) {
        super(targetPropertyValue, financingTermInYears, annualInterestRate);
        this.garageSpots = garageSpots;
        this.floorNumber = floorNumber;
    }

    //getters for garage spots and floors in a Building with apartments
    public int getGarageSpots() {return garageSpots;}
    public int getFloorNumber() {return floorNumber;}

    @Override
    public double calculateMonthlyValue() {
        double r = getMonthlyRate(); // get monthly interest rate (r)
        int m = getTotalMonths(); // get total months (m)
        if (r == 0) { // prevent division by zero
            return getPropertyValue() / m;
        }
        //Installmente calculation (PRICE System)
        // P = PV * i(1 + i)^n / (1+i)^n -1
        double pow = Math.pow(1 + r, m);
        return getPropertyValue() * (r * pow) / (pow - 1);
    }

    @Override
    // Reuse the calculateMonthlyValue() of this subclass
    public double calculateTotalValue() {return calculateMonthlyValue() * getTotalMonths();}
}
