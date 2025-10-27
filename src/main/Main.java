package main;

import model.Financing;
import util.UserInterface;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Create a Scanner to read user input from the console
        UserInterface userInterface = new UserInterface(scanner); //Instantiate the user interface, passing the Scanner

        System.out.println(
                "Welcome to Weikyr's Property Financing!"
        );

        ArrayList<Financing> financings = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {// Collect data of 4 financings, using inputs from "UI" class
            System.out.println("Current Financing " + i + ":");
            double propertyValue = userInterface.getPropertyValue();
            int financingTermInYears = userInterface.getFinancingTerm();
            double interestRate = userInterface.getAnnualInterestRate();
            // Display the calculated monthly payment
            Financing newFinancing = new Financing(propertyValue, financingTermInYears, interestRate);
            financings.add(newFinancing);
            System.out.println("Monthly value: R$ " + String.format("%,.2f" ,newFinancing.calculateMonthlyValue()) + ".");
        }

        double totalProperties = 0; // Variable for total of properties
        double totalFinancings = 0; // Variable for total of financings

        for (int i = 0; i < financings.size(); i++) { // shows each financing's details
            Financing f = financings.get(i);
            // usage of getPropertyValue() to access the private attribute propertyValue
            System.out.println(
                    "Financing " + (i + 1) + " - property value: R$ " +
                    String.format("%,.2f", f.getPropertyValue()) +
                    ", Financing Value: R$ " +
                    String.format("%,.2f", f.calculateTotalValue())
            );
            totalProperties += f.getPropertyValue(); //
            totalFinancings += f.calculateTotalValue(); //
        }


        System.out.println( // Displays the total value for both property and financing
                "\nTotal properties value is: R$ " + String.format("%,.2f" ,totalProperties) +
                ". \nTotal financings value is: R$ " + String.format("%,.2f" ,totalFinancings) + "."
        );

        // Close the scanner
        scanner.close();
    }
}