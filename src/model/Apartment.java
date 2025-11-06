package model;

public class Apartment extends Financing {
    public Apartment(double targetPropertyValue, int financingTermInYears, double annualInterestRate) {
        super(targetPropertyValue, financingTermInYears, annualInterestRate);
    }

    @Override
    public double calculateMonthlyValue() {
        double r = getMonthlyRate();
        int m = getTotalMonths();

        if (r == 0) {
            return getPropertyValue() / m;
        }
        double pow = Math.pow(1 + r, m);
        return getPropertyValue() * (r * pow) / (pow - 1);
    }

    @Override
    public double calculateTotalValue() {
        return calculateMonthlyValue() * getTotalMonths();
    }
}
