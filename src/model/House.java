package model;

import util.IncreaseGreatherThanInterestException;

public class House extends Financing {
    private double builtAreaSize;
    private double landSize;

    /*
    Constructor for House class
    Calls superclass (Finacing) to initialize Attributes
     */
    public House(double targetPropertyValue, int financingTermInYears, double annualInterestRate, double builtAreaSize, double landSize) {
       super(targetPropertyValue, financingTermInYears, annualInterestRate);
       this.builtAreaSize = builtAreaSize;
       this.landSize = landSize;
    }

    //getter for Land and Area size
    public double getBuiltAreaSize() {return builtAreaSize;}
    public double getLandSize()      {return landSize;}

    // validtes business rule preventing increase of exceeding half of monthly interest
    private void validateHouseIncrease(double monthlyInterest, double increaseAmount)
            throws IncreaseGreatherThanInterestException {

        if (increaseAmount > monthlyInterest / 2.0) {
            throw new IncreaseGreatherThanInterestException( //Exception if half of monthly is exceeded
            "The fixed increase of R$ 80,00 exceeds half of the monthly interest amount " +
            "(Half interest: R$ " + String.format("%.2f", monthlyInterest / 2.0) + "). Financing not allowed."
            );
        }
    }

    @Override
    //Gets the base installment value and add fixed amount (+80)
    public double calculateMonthlyValue() {
        double baseMonthly = super.calculateMonthlyValue();
        double principalPart = getPropertyValue() / getTotalMonths();
        double interestPart = baseMonthly - principalPart;
        double increase = 80.0;

        try { // Validates the increase against the interest portion
            validateHouseIncrease(interestPart, increase);
        } catch (IncreaseGreatherThanInterestException e) {
            // log the error and return sentinel value for invalid financing
            System.err.println("Validation Error (House): " + e.getMessage());
            return -1.0; // sentinel value
        }
        return baseMonthly + increase;
    }

    @Override
    // Reuse the calculateMonthlyValue() of this subclass
    public double calculateTotalValue() {
        double monthly = calculateMonthlyValue();
        if (monthly < 0) {
            return -1.0; // for the rejection
        }
        return monthly * getTotalMonths();
    }
}
