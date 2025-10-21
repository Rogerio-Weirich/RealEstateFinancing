package model;

public class Financing {
    private double propertyValue;
    private int financingTermInYears;
    private double annualInterestRate;

    public Financing(double targetPropertyValue, int financingTermInYears, double annualInterestRate) {
        this.propertyValue = targetPropertyValue;
        this.financingTermInYears = financingTermInYears;
        this.annualInterestRate = annualInterestRate;
    }

    public double calculateMonthlyValue() {
        return (this.propertyValue / (this.financingTermInYears * 12)) * (1 + (this.annualInterestRate / 12));
    }

    public double calculateTotalValue() {
        return this.calculateMonthlyValue() * this.financingTermInYears * 12;
    }
}

