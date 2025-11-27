package util;

import model.Financing;
import model.Plot;
import model.House;
import model.Apartment;

import java.io.*;
import java.util.ArrayList;

public class FinancingPersistence {
    private static final String TEXT_FILE = "financings.txt";
    private static final String BINARY_FILE = "financings.ser";

    public static void saveToTextFile(ArrayList<Financing> financings) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEXT_FILE))) {
            for (Financing f : financings) {
                StringBuilder sb = new StringBuilder();
                // appends common financing data separated by semicolons
                sb.append(f.getPropertyValue()).append(";")
                  .append(f.calculateTotalValue()).append(";")
                  .append(f.getAnnualInterestRate()).append(";")
                  .append(f.getFinancingTermInYears());

                if (f instanceof Plot p) { //appends specific data for plot
                    sb.append(";Plot;").append(p.getZoneType());
                } else if (f instanceof House h) { //appends specific data for house
                    sb.append(";House;").append(h.getBuiltAreaSize())
                      .append(";").append(h.getLandSize());
                } else if (f instanceof Apartment a) { //appends specific data for ap
                    sb.append(";Apartment;").append(a.getGarageSpots())
                      .append(";").append(a.getFloorNumber());
                }
                writer.println(sb.toString());
            }
            System.out.println("Financings suscessfully saved at: " + TEXT_FILE);
        } catch (IOException e) {
            System.err.println("Error saving text file: " + e.getMessage());
        }
    }

    // saves the entire list using Java serialization
    public static void saveToBinaryFile(ArrayList<Financing> financings) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BINARY_FILE))) {
            oos.writeObject(financings);
            System.out.println("Financings successfully serialized at: " + BINARY_FILE);
        } catch (IOException e) {
            System.err.println("Error serializing financings: ");
        }
    }

    // loads and displays content from text file
    public static void loadFromTextFile() {
        File file = new File(TEXT_FILE);
        if (!file.exists()) {
            System.out.println("Text file not found: " + TEXT_FILE);
            return;
        }

        System.out.println("\n=== LOADED DATA FROM TEXT FILE ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 1;
            // splits the line into array using delimiter and semicolon
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                System.out.printf("Financing %d:%n", count++);
                System.out.printf(" Property Value: R$ %,.2f%n", Double.parseDouble(parts[0]));
                System.out.printf(" Total Value Financed: R$ %,.2f%n", Double.parseDouble(parts[1]));
                System.out.printf(" Annual Interest Rate: %.2f%% %n", Double.parseDouble(parts[2]));
                System.out.printf(" Term: %s years%n", parts[3]);

                String type = parts[4];
                if ("Plot".equals(type)) {
                    System.out.println("    Type: Plot");
                    System.out.println("    Zone: " + parts[5]);
                } else if ("House".equals(type)) {
                    System.out.println("    Type: House");
                    System.out.printf("     Built Area: %.2f m²%n", Double.parseDouble(parts[5]));
                    System.out.printf("     Land Size: %.2f m²%n", Double.parseDouble(parts[6]));
                } else if ("Apartment".equals(type)) {
                    System.out.println("    Type: Apartment");
                    System.out.println("    Garage Spots: " + parts[5]);
                    System.out.println("    Floor Number: " + parts[6]);
                }
                System.out.println("----------------------------------------");
            }
        } catch (IOException e) {
            System.err.println("Error reading text file: " + e.getMessage());
        }
    }

    //loads financings from binary file at program start
    @SuppressWarnings("unchecked")
    public static ArrayList<Financing> loadFromBinaryFile() {
        File file = new File(BINARY_FILE);
        if (!file.exists()) {
            System.out.println("Binary file not found: " + BINARY_FILE);
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ArrayList<Financing> loaded = (ArrayList<Financing>) ois.readObject();
            System.out.println("Financings successfully deserialized! Total: " + loaded.size());

            System.out.println("\n=== SUMMARY OF DESERIALIZED FINANCINGS ===");
            for (int i = 0; i < loaded.size(); i++) {
                Financing f = loaded.get(i);
                String type = f instanceof Plot ? "Plot" :
                              f instanceof House ? "House" : "Apartment";
                System.out.printf("%d. %s - R$ %,.2f (Total: R$ %,.2f)%n",
                        i+1, type, f.getPropertyValue(), f.calculateTotalValue());
            }
            return loaded;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error deserializing: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
