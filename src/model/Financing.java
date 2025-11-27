package model;

import java.io.Serializable;

public abstract class Financing implements Serializable {
    //Attribute: Instance variables
    private static final long serialVersionUID = 1L;
    private double propertyValue; // Total value of the property
    private int financingTermInYears; // Duration of the financing in years
    private double annualInterestRate; // Annual interest Rate ("%" based)

    //Constructor: initializes a new Financing object
    public Financing(double targetPropertyValue, int financingTermInYears, double annualInterestRate) {
        this.propertyValue = targetPropertyValue;
        this.financingTermInYears = financingTermInYears;
        this.annualInterestRate = annualInterestRate;
    }

    public double getPropertyValue()        {return propertyValue;}
    public int    getFinancingTermInYears() {return financingTermInYears;}
    public double getAnnualInterestRate()   {return annualInterestRate;}

    //Method: calculates the monthly interest rate
    protected double getMonthlyRate() {
        return annualInterestRate / 100.0 / 12.0;
    }

    // Method: calculate the total number of months for the financing
    protected int getTotalMonths() {
        return financingTermInYears * 12;
    }

    //Method: calculates the estimated monthly value
    public double calculateMonthlyValue() {
        return (this.propertyValue / (this.financingTermInYears * 12)) * (1 + (this.annualInterestRate / 12));
    }

    //Method: calculates the total value paid at the end of the financing period
    public double calculateTotalValue() {
        return this.calculateMonthlyValue() * this.financingTermInYears * 12;
    }
}

