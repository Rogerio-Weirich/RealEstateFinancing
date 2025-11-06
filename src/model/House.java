package model;

public class House extends Financing {
    public House(double targetPropertyValue, int financingTermInYears, double annualInterestRate) {
       super(targetPropertyValue, financingTermInYears, annualInterestRate);
    }

    public double calculateMonthlyValue() {
        return super.calculateMonthlyValue() + 80;
    }
}
