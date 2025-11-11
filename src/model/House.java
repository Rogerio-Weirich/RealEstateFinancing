package model;

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

    @Override
    //Gets the base installment value and add fixed amount (+80)
    public double calculateMonthlyValue() {return super.calculateMonthlyValue() + 80;}

    @Override
    // Reuse the calculateMonthlyValue() of this subclass
    public double calculateTotalValue() {return calculateMonthlyValue() * getTotalMonths();}
}
