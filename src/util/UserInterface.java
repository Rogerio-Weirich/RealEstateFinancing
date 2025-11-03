package util;

import model.Financing;

import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface {
    //Attibute: responsible for reading user input
    private Scanner scanner;
    private ArrayList<Financing> properties;  // to create a list containing 5 properties
    private ArrayList<String> propertyDetails;// to provide each property its own description
    private int selectedIndex;                 // keeps the selected index

    //Constructor: initializes the interface with a given Scanner
    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        initProperties();  // Initializes the 5 properties
    }

    private void initProperties() {
        properties = new ArrayList<>();
        propertyDetails = new ArrayList<>();

        // 5 properties with values (only properties, no taxes)
        properties.add(new Financing(350000.0, 0, 0)); // propertie 1
        propertyDetails.add(
                "House: Centro, Curitiba - PR"
        );
        properties.add(new Financing(475000.0, 0, 0)); // propertie 2
        propertyDetails.add(
                "Apartment: Mercês, Curitiba - PR"
        );
        properties.add(new Financing(400000.0, 0, 0)); // propertie 3
        propertyDetails.add(
                "Townhouse: JD. Social, Curitiba - PR"
        );
        properties.add(new Financing(375000.0, 0, 0)); // propertie 4
        propertyDetails.add(
                "Loft: Botânico, Curitiba - PR"
        );
        properties.add(new Financing(625000.0, 0, 0)); // propertie 5
        propertyDetails.add(
                "Penthouse: Batel, Curitiba - PR"
        );

    }

    //Method:
    public double getPropertyValue() {
        System.out.println("\n=== SELECT A PROPERTY ===");
        for (int i = 0; i < properties.size(); i++) {
            double value = properties.get(i).getPropertyValue();
            String details = propertyDetails.get(i);
            System.out.printf("%d. %s - R$ %, .2f%n", (i + 1), details, value);
        }
        System.out.println("==========================");

        int choice;
        do {
            System.out.print("Select the property [1 - 5]: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Enter a valid number.");
                scanner.next();
                System.out.print("Select the property [1 - 5]: ");
            }
            choice = scanner.nextInt();
            if (choice < 1 || choice > 5) {
                System.out.println("Choose between [1 - 5]!");
            }
        } while (choice < 1 || choice > 5);

        selectedIndex = choice - 1;
        return properties.get(selectedIndex).getPropertyValue();
    }

    public String getPropertyDescription() {
        return propertyDetails.get(selectedIndex);
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
