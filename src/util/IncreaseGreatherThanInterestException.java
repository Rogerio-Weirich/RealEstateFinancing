package util;
/*
Custom exception when fixed increase in House financing is greater than half monthly interest amount.
Ensures the additional fee does not disproportionately affect the financing
 */
public class IncreaseGreatherThanInterestException extends Exception {
    public IncreaseGreatherThanInterestException(String message) {super(message);}
}
