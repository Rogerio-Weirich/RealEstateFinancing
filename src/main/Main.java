package main;

import model.Financing;
import util.UserInterface;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a Scanner to read user input from the console
        Scanner scanner = new Scanner(System.in);
        // Instantiate the user interface, passing the Scanner
        UserInterface userInterface = new UserInterface(scanner);

        System.out.println(
                "Welcome to Weikyr's Property model.Financing!"
        );

        // Collect user inputs using "UI" class
        double propertyValue = userInterface.getPropertyValue();
        int financingTermInYears = userInterface.getFinancingTerm();
        double interestRate = userInterface.getAnnualInterestRate();

        // Display the calculated monthly payment
        Financing newFinancing = new Financing(propertyValue, financingTermInYears, interestRate);
        System.out.println(newFinancing.calculateMonthlyValue());

        // Close the scanner
        scanner.close();
    }
}