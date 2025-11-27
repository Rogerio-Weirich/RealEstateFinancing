package util;

/**
 * interface that defines the contract for obtaining user input
 * The UserInterface class implements this interface to separate
 * the input logic from the main program flow.
 */
public interface InputProvider {
    double getPropertyValue      (); // Method: prompts user to select a property and return value
    String getPropertyDescription(); //Method: retrieve the description of the selected property
    String getPropertyType       (); // Method: Retrieves the type of the selected property
    int    getFinancingTerm      (); // Method: prompts user for the financing term in years
    double getAnnualInterestRate (); // Method: prompts the user for the annual interest rate
}
