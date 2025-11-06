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

        // shows monthly instalment and full details (with polymorphism)
        System.out.println("=== FINANCING SUMMARY ===");
        for (int i = 0; i < financings.size(); i++) { // shows each financing's details
            Financing f = financings.get(i);
            String d = propertyDescriptions.get(i);
            // usage of getPropertyValue() to access the private attribute propertyValue
            System.out.println("\nFinancing " + (i + 1) + " - " + d);
            System.out.println("-------------------------");

            System.out.printf("Property Value: R$ %,.2f%n", f.getPropertyValue());

            int years = f.getFinancingTermInYears();
            int months = years * 12;
            System.out.printf("Term: %d years (%d months)%n", years, months);

            double annualRate = f.getAnnualInterestRate();
            double monthlyRatePercent = annualRate / 12;
            System.out.printf("Annual Interest Rate: %.2f%% (%.4f%% monthly)%n", annualRate, monthlyRatePercent);

            double monthlyPayment = f.calculateMonthlyValue();  // ← usa +80 se for House!
            System.out.printf("Monthly Payment: R$ %,.2f%n", monthlyPayment);

            double totalPaid = f.calculateTotalValue();
            System.out.printf("Total paid (interest): R$ %,.2f%n", totalPaid - f.getPropertyValue());

            totalProperties += f.getPropertyValue();
            totalFinancings += totalPaid;
        }

        // Aims to display all financings if multiple was selected
        System.out.println("-------------------------");
        System.out.printf("%nTotal properties value is: R$ %,.2f%n", totalProperties);
        System.out.printf("Total financings value is: R$ %,.2f%n", totalFinancings);

        // Close the scanner 🥺
        scanner.close();
    }
}