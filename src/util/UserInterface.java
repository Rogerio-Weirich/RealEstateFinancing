package util;

import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface implements InputProvider {
    //Attibute: responsible for reading user input
    private Scanner scanner;
    private ArrayList<Double> propertyValues;
    private ArrayList<String> propertyDetails;
    private ArrayList<String> propertyTypes;
    private int selectedIndex;                 // keeps the selected index

    //Constructor: initializes the interface with a given Scanner
    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        initProperties();  // Initializes the 6 properties
    }

    private void initProperties() {
        propertyValues  = new ArrayList<>();
        propertyDetails = new ArrayList<>();
        propertyTypes   = new ArrayList<>();

        // 6 properties with values (only properties, no taxes)
        propertyValues.add(550000.0); // property 1
        propertyTypes.add("Plot");
        propertyDetails.add( //Plot
                "Plot: Cidade Industrial, Curitiba - PR"
        );

        propertyValues.add(350000.0); // property 2
        propertyTypes.add("Plot");
        propertyDetails.add( //Plot
                "Plot: Centro, Curitiba - PR"
        );
        propertyValues.add(400000.0); // property 3
        propertyTypes.add("House");
        propertyDetails.add( //House
                "Townhouse: JD. Social, Curitiba - PR"
        );
        propertyValues.add(375000.0); // property 4
        propertyTypes.add("House");
        propertyDetails.add( //House
                "Loft: Botânico, Curitiba - PR"
        );
        propertyValues.add(625000.0); // property 5
        propertyTypes.add("Apartment");
        propertyDetails.add( //Apartment
                "Penthouse: Batel, Curitiba - PR"
        );
        propertyValues.add(475000.0); // property 6
        propertyTypes.add("Apartment");
        propertyDetails.add( //Apartment
                "Apartment: Mercês, Curitiba - PR"
        );
    }

    //Method:
    public double getPropertyValue() {
        // display select property menu
        System.out.println("\n=== SELECT A PROPERTY ===");
        for (int i = 0; i < propertyValues.size(); i++) { // iterate property list to display details
            double value = propertyValues.get(i);         // gets property value
            String details = propertyDetails.get(i);      // gets corresponding description
            // format and show: number (1 - based), description and real valeus
            System.out.printf("%d. %s - R$ %, .2f%n", (i + 1), details, value);
        }
        System.out.println("==========================");

        int choice;
        do { // loop to verify if the entry is integer
            System.out.print("Select the property [1 - 6]: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Enter a valid number.");
                scanner.next(); // discard if invalid entry
                System.out.print("Select the property [1 - 6]: ");
            }
            choice = scanner.nextInt(); // reads the valid number
            if (choice < 1 || choice > 6) { // validates the range of choice
                System.out.println("Choose between [1 - 6]!");
            }
        } while (choice < 1 || choice > 6); // repeat until choice is between 1 - 6

        selectedIndex = choice - 1;               // keeps the selected index, transforming from 0 to 1
        return propertyValues.get(selectedIndex); // return selected property value
    }

    public String getPropertyDescription() { // return the selected property description
        return propertyDetails.get(selectedIndex);
    }

    public String getPropertyType() { // return the selected property type
        return propertyTypes.get(selectedIndex);
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
            scanner.nextLine();
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
            scanner.nextLine();
            if (value <= 0) { // check if the value is positive
                System.out.println("The Annual Interest Rate has to be more than Zero.");
            }
        } while (value <= 0); // repeat if the value is not positive
        return value;
    }
}
