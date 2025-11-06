package model;

public class House extends Financing {
    /*
    Constructor for House class
    Calls superclass (Finacing) to initialize Attributes
     */
    public House(double targetPropertyValue, int financingTermInYears, double annualInterestRate) {
       super(targetPropertyValue, financingTermInYears, annualInterestRate);
    }

    @Override
    //Gets the base installment value and add fixed amount (+80)
    public double calculateMonthlyValue() {return super.calculateMonthlyValue() + 80;}

    @Override
    // Reuse the calculateMonthlyValue() of this subclass
    public double calculateTotalValue() {return calculateMonthlyValue() * getTotalMonths();}
}
