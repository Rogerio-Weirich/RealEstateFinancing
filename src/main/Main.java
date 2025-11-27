package main;

import model.Financing;
import model.Plot;
import model.House;
import model.Apartment;
import util.FinancingPersistence;
import util.InputProvider;
import util.UserInterface;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Create a Scanner to read user input from the console
        InputProvider userInterface = new UserInterface(scanner); //Instantiate the user interface, passing the Scanner

        System.out.println(
                "=== Welcome to Weikyr's Property Financing! ===\n"
        );

        ArrayList<Financing> financings = FinancingPersistence.loadFromBinaryFile(); // load previously saved financings from binary
        ArrayList<String> propertyDescriptions = new ArrayList<>(); // stores description of each approved financing
        ArrayList<String> plotZoneTypes = new ArrayList<>(); // stroe zone type (plot only)

        if (financings.isEmpty()) {
            System.out.println("No previous finacings found. Starting a new session.\n");
        } else {
            System.out.println("Loaded " + financings.size() + " previous financing(s).\n");
        }

        char choice; //Controls the loop
        int financingCount = financings.size() + 1; // iterates for "current financing #x"

        do {
            System.out.println("=== FINANCING SIMULATION #" + financingCount + "===\n"); // Simulation header
            double propertyValue = userInterface.getPropertyValue(); // Prompt user to choose between available options
            String description = userInterface.getPropertyDescription(); // gets property Description
            String type = userInterface.getPropertyType().trim(); // get the type (plot, house, ap)
            System.out.println("Selected property: " + description + "\n");
            // asks for term and rate
            int financingTermInYears = userInterface.getFinancingTerm(); // ask user for financing term in years
            double interestRate = userInterface.getAnnualInterestRate(); // as user for annual interest rate
            // asks type of Plot
            String zoneType = "Residential"; // default plot type
            if (type.equals("Plot")) {
                System.out.print("Select the plot type: [ Commercial || Residential ]: ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("No input provided. Using default: Residential");
                } else if (input.toLowerCase().startsWith("c")) { // commercial if: "c", "C", "commercial", "Commercial", "COMMERCIAL"
                    zoneType = "Commercial";
                    System.out.println("Selected plot type is: Commercial");
                } else if (input.toLowerCase().startsWith("r")) { // residential if: "r", "R", "residential", "Residential", "RESIDENTIAL"
                    zoneType = "Residential";
                    System.out.println("Selected plot type is: Residential");
                } else {
                    System.out.println("Invalid input. Using default: Residential");
                }
            }

            // Creates and add to a list
            Financing newFinancing = null;
            if (type.equals("House")) {
                System.out.print("Enter the built area size: ");
                double builtAreaSize = scanner.nextDouble();
                System.out.print("Enter the land size: ");
                double landSize = scanner.nextDouble();
                scanner.nextLine(); //consume newline after nextDouble
                newFinancing = new House(propertyValue, financingTermInYears, interestRate, builtAreaSize, landSize);
                plotZoneTypes.add(""); //placeholder - not used for house
            } else if (type.equals("Apartment")) {
                System.out.print("Enter the number of Garage spots: ");
                int garageSpots = scanner.nextInt();
                System.out.print("Entenr the floor number: ");
                int floorNumber = scanner.nextInt();
                scanner.nextLine();
                newFinancing = new Apartment(propertyValue, financingTermInYears, interestRate, garageSpots, floorNumber);
                plotZoneTypes.add("");
            } else if (type.equals("Plot")) {
                newFinancing = new Plot(propertyValue, financingTermInYears, interestRate, zoneType);
                plotZoneTypes.add(zoneType); // store zone type for summary
            } else {
                plotZoneTypes.add("Residential"); // for Apartment and House (they don't use it)
            }

            double monthlyPayment = newFinancing.calculateMonthlyValue();
            double totalPayment = newFinancing.calculateTotalValue();

            if (monthlyPayment < 0) { // if financing is rejected
                System.out.println("FINANCING REJECTED");
                System.out.println("Reason: Fixed R$ 80.00 increase exceeds half of monthly interest.");
                System.out.println("This financing was not saved.\n");
            } else { // add only approved financings
                financings.add(newFinancing);
                propertyDescriptions.add(description);
                plotZoneTypes.add(type.equals("Plot") ? zoneType : ""); // update zone if plot

                System.out.println("FINANCING APPROVED AND SAVED!");
                System.out.printf("Property: %s | Monthly: R$ %,.2f | Total: R$ %,.2f%n%n",
                        description, monthlyPayment, totalPayment);
            }

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
        System.out.println("=== FINANCING SUMMARY ===");
        double totalProperties = 0; // Variable for total of properties
        double totalFinancings = 0; // Variable for total of financings

        // shows monthly instalment and full details (with polymorphism)
        for (int i = 0; i < financings.size(); i++) { // shows each financing's details
            Financing f = financings.get(i);
            String d = (i < propertyDescriptions.size()) ? propertyDescriptions.get(i) :
                    "Unknown Property";
            String zone = (i < plotZoneTypes.size()) ? plotZoneTypes.get(i) : "";
            String fullDescription = d;
            if (f instanceof Plot && !zone.isEmpty()) {
                fullDescription = zone + "Plot - " + d;
            }

            // usage of getPropertyValue() to access the private attribute propertyValue
            System.out.println("\nFinancing " + (i + 1) + " - " + fullDescription);
            System.out.println("-------------------------");

            System.out.printf("Property Value: R$ %,.2f%n", f.getPropertyValue());

            int years = f.getFinancingTermInYears();
            int months = years * 12;
            System.out.printf("Term: %d years (%d months)%n", years, months);

            double annualRate = f.getAnnualInterestRate();
            double monthlyRatePercent = annualRate / 12;
            System.out.printf("Annual Interest Rate: %.2f%% (%.4f%% monthly)%n", annualRate, monthlyRatePercent);

            // Display specific atrtibutes based on type
            if (f instanceof House h) {
                System.out.printf("Built Area Size: %.2f m²%n", h.getBuiltAreaSize());
                System.out.printf("Land Size: %.2f m²%n", h.getLandSize());
            } else if (f instanceof Apartment a) {
                System.out.printf("Garage Spots: %d%n", a.getGarageSpots());
                System.out.printf("Floor Number: %d%n", a.getFloorNumber());
            } else if (f instanceof Plot p) {
                System.out.printf("Zone Type: %s%n", p.getZoneType());
            }

            double monthlyPayment = f.calculateMonthlyValue(); // uses +80 if its house
            double totalPaid = f.calculateTotalValue();

            if (monthlyPayment < 0) { // Hoyse financing rejected due to disproportionate increase
                System.out.println("Monthly Payment: FINANCING REJECTED!");
                System.out.println("Reason: R$ 80,00 increase exceeds half of monthly interest.");
                System.out.printf("Total paid (interest): NOT CALCULATED%n");
            } else { // Do not incluse in total Sums
                System.out.printf("Monthly Payment: R$ %,.2f%n", monthlyPayment);
                System.out.printf("Total paid(interest): R$ %,.2f%n", totalPaid - f.getPropertyValue());
                totalProperties += f.getPropertyValue();
                totalFinancings += totalPaid;
            }
            // Aims to display all financings if multiple was selected
            System.out.println("-------------------------");
            System.out.printf("%nTotal properties value: R$ %,.2f%n", totalProperties);
            System.out.printf("Total financings value:   R$ %,.2f%n", totalFinancings);

            if (!financings.isEmpty()) {
                FinancingPersistence.saveToTextFile(financings);
                FinancingPersistence.saveToBinaryFile(financings);
                System.out.println("\nAll Financings saved successfully!");
                System.out.println("-> financings.txt       (readable)");
                System.out.println("-> financings.ser       (binary)");
            }
            // Close the scanner 🥺
            System.out.println("\n\nThank you for using the simulator 📖🚀");
            scanner.close();
        }
    }
}