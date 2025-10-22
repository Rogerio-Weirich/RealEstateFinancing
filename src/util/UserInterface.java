package util;

import java.util.Scanner;

public class UserInterface {
    //Attibute: responsible for reading user input
    private Scanner scanner;

    //Constructor: initializes the interface with a given Scanner
    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    //Method:
    public double getPropertyValue() {
        double value;
        do { // prompts the user for the property value
            System.out.print("Enter the Property Value R$ ");
            while (!scanner.hasNextDouble()) { // loop until a valid double is entered
                System.out.println("Please, give a valid value.");
                System.out.print("Enter the Property Value R$ ");
                scanner.next(); // discard invalid inputs
            }
            value = scanner.nextDouble(); //read the valid double
            if (value <= 0) { //check if value is positive
                System.out.println("The Property Value has to be more than zero.");
            }
        } while (value <= 0); // repeat if the value is not positive
        return value;
    }

    //Method
    public int getFinancingTerm() {
        int value;
        do { // prompts the user for the financing term
            System.out.print("Enter the Financing Term (in years): ");
            while (!scanner.hasNextInt()) { // loop until a valid integer is entered
                System.out.println("Please give a valid value.");
                System.out.print("Enter the Financing Term (in years): ");
                scanner.next(); // discard invalid inputs
            }
            value = scanner.nextInt(); //read the valid integer
            if (value <= 0) { // check if the value is positive
                System.out.println("The Financing Term has to be more than Zero.");
            }
        } while (value <= 0); // repeat if the value is not positive
        return value;
    }

    //Method
    public double getAnnualInterestRate() {
        double value;
        do { // prompts the user for the annual interest rate
            System.out.print("Enter the Annual Interest Rate (%): ");
            while (!scanner.hasNextDouble()) { // loop until a valid double is entered
                System.out.println("Please give a valid value.");
                System.out.print("Enter the Annual Interest Rate (%): ");
                scanner.next(); // discard invalid inputs
            }
            value = scanner.nextDouble(); //read the valid integer
            if (value <= 0) { // check if the value is positive
                System.out.println("The Annual Interest Rate has to be more than Zero.");
            }
        } while (value <= 0); // repeat if the value is not positive
        return value;
    }
}
