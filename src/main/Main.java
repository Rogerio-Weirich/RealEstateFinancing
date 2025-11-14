package main;

import model.Financing;
import model.Plot;
import model.House;
import model.Apartment;
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
            String description = userInterface.getPropertyDescription(); // gets property Description
            String type = userInterface.getPropertyType().trim();
            System.out.println("The selected property is \"" + description + "\"\n");
            // asks for term and rate
            int financingTermInYears = userInterface.getFinancingTerm();
            double interestRate = userInterface.getAnnualInterestRate();
            scanner.nextLine();
            // Creates and add to a list
            Financing newFinancing = null;
            if (type.equals("House")) {
                System.out.print("Enter the built area size: ");
                double builtAreaSize = scanner.nextDouble();
                System.out.print("Enter the land size: ");
                double landSize = scanner.nextDouble();
                scanner.nextLine();
                newFinancing = new House(propertyValue, financingTermInYears, interestRate, builtAreaSize, landSize);
            } else if (type.equals("Apartment")) {
                System.out.print("Enter the number of Garage spots: ");
                int garageSpots = scanner.nextInt();
                System.out.print("Entenr the floor number: ");
                int floorNumber = scanner.nextInt();
                scanner.nextLine();
                newFinancing = new Apartment(propertyValue, financingTermInYears, interestRate, garageSpots, floorNumber);
            } else if (type.contains("Lot")) {
                System.out.println("Current selected Zone Type is: " + type);
                System.out.print("Confirm or Enter the Zone Type [ Commercial or Residential ]: ");
                String zoneTypeInput = scanner.nextLine();
                newFinancing = new Plot(propertyValue, financingTermInYears, interestRate, zoneTypeInput);
            }
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

            // Display specific atrtibutes based on type
            if (f instanceof House) {
                House h = (House) f;
                System.out.printf("Built Area Size: %.2f m²%n", h.getBuiltAreaSize());
                System.out.printf("Land Size: %.2f m²%n", h.getLandSize());
            } else if (f instanceof Apartment) {
                Apartment a = (Apartment) f;
                System.out.printf("Garage Spots: %d%n", a.getGarageSpots());
                System.out.printf("Floor Number: %d%n", a.getFloorNumber());
            } else if (f instanceof Plot) {
                Plot p = (Plot) f;
                System.out.printf("Zone Type: %s%n", p.getZoneType());
            }

            double monthlyPayment = f.calculateMonthlyValue();  // Uses +80 if its house
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