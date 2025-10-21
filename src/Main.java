import java.util.Scanner;

class Financing {
    double propertyValue;
    int financingTermInYears;
    double annualInterestRate;

    Financing(double targetPropertyValue, int financingTermInYears, double annualInterestRate) {
        this.propertyValue = targetPropertyValue;
        this.financingTermInYears = financingTermInYears;
        this.annualInterestRate = annualInterestRate;
    }

    double calculateMonthlyValue() {
        return (this.propertyValue / (this.financingTermInYears * 12)) * (1 + (this.annualInterestRate / 12));
    }

    double calculateTotalValue() {
        return this.calculateMonthlyValue() * this.financingTermInYears * 12;
    }
}

class UserInterface {
    double getPropertyValue() {
        return 0;
    }

    int getFinancingTerm() {
        return 0;
    }

    double getAnnualInterestRate() {
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        UserInterface userInterface = new UserInterface();

        System.out.println(
                "Welcome to Weikyr's Property Financing!"
        );

        double interestRate = userInterface.getAnnualInterestRate();
        int financingTermInYears = userInterface.getFinancingTerm();
        double propertyValue = userInterface.getPropertyValue();

        Financing newFinancing = new Financing(propertyValue, financingTermInYears, interestRate);
        System.out.println(newFinancing.calculateMonthlyValue());
    }
}