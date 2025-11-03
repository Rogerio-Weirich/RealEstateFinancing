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
                "Welcome to Weikyr's Property Financing!\n"
        );

        ArrayList<Financing> financings = new ArrayList<>();          // list for financings
        ArrayList<String>    propertyDescriptions = new ArrayList<>();// list for property descriptions
        char choice; //Controls the loop
        int financingCount = 1; // iterates for "current financing x"

        do {
            System.out.println("Current Financing " + financingCount + ".");
            double propertyValue = userInterface.getPropertyValue(); // Choose between available options
            String description = userInterface.getPropertyDescription();
            System.out.println("The selected property is \"" + description + "\"\n");
            // asks for term and rate
            int financingTermInYears = userInterface.getFinancingTerm();
            double interestRate = userInterface.getAnnualInterestRate();
            // Creates and add to a list
            Financing newFinancing = new Financing(propertyValue, financingTermInYears, interestRate);
            financings.add(newFinancing);
            propertyDescriptions.add(description);
            // shows monthly instalment and full details
            System.out.println("\n=== FINANCING DETAILS ===");
            System.out.printf("Property Value: R$ %,.2f%n", newFinancing.getPropertyValue());
            System.out.printf("Term: %d years (%d months)%n", financingTermInYears, financingTermInYears * 12);
            System.out.printf("Annual Interest Rate: %.2f%% (%.4f%% mothly)%n", interestRate, interestRate / 12);
            System.out.printf("Montlhy Payment: R$ %,.2f%n", newFinancing.calculateMonthlyValue());
            System.out.printf(
                    "Total paid: R$ %,.2f%n", newFinancing.calculateTotalValue() - newFinancing.getPropertyValue()
            );
            System.out.println();
            // Asks if user wants to continue the simulation
            while (true) { // loop for validation
                System.out.print("Do you want to simulate another Financing? [ Y = Yes | N = No ]: ");
                choice = Character.toUpperCase(scanner.next().charAt(0));
                if (choice == 'Y' || choice == 'N') { // if answer is valid
                    break;
                } else {
                    System.out.println("Invalid option. Please enter [Y] for Yes or [N] for No.");
                }
            } // finishes the validation
            financingCount++; // iterates for the next
        } while (choice == 'Y'); // main looping will only continue if chosen choice was 'y'

        // Summary for the properties
        double totalProperties = 0; // Variable for total of properties
        double totalFinancings = 0; // Variable for total of financings

        System.out.println("=== FINANCING SUMMARY ===");
        for (int i = 0; i < financings.size(); i++) { // shows each financing's details
            Financing f = financings.get(i);
            String d = propertyDescriptions.get(i);
            // usage of getPropertyValue() to access the private attribute propertyValue
            System.out.println(
                    "Financing " + (i + 1) + " - " + d +
                    "\nProperty Value: R$ " + String.format("%,.2f", f.getPropertyValue()) +
                    "\nFinancing Value: R$ " + String.format("%,.2f", f.calculateTotalValue())
            );
            totalProperties += f.getPropertyValue(); // Acccumulate the total property value
            totalFinancings += f.calculateTotalValue(); // Accumulate the total financing value
        }

        System.out.println( // Displays the total values for all property and financing
                "\nTotal properties value is: R$ " + String.format("%,.2f" ,totalProperties) +
                ". \nTotal financings value is: R$ " + String.format("%,.2f" ,totalFinancings) + "."
        );

        // Close the scanner 🥺
        scanner.close();
    }
}