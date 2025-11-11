package model;

public class Plot extends Financing {
    private String zoneType;

    /*
    Constructor for Plot class
    Calls superclass (Finacing) to initialize Attributes
     */
    public Plot(double targetPropertyValue, int financingTermInYears, double annualInterestRate, String zoneType) {
        super(targetPropertyValue, financingTermInYears, annualInterestRate);
        this.zoneType = zoneType;
    }

    //getter for zone type
    public String getZoneType() {return zoneType;}

    @Override
    public double calculateMonthlyValue() {
        // get the vase instalment value (w/ interest) from superclass
        double installmentWithInterest = super.calculateMonthlyValue();
        // Apply 2% (addicional cost/fee)
        return installmentWithInterest * 1.02;
    }

    @Override
    // Reuse the calculateMonthlyValue() of this subclass
    public double calculateTotalValue() {
        return calculateMonthlyValue() * getTotalMonths();
    }
}
