package mru.tsc.exceptions;


// checks for a valid price, cannot be in the negatives
public class PriceException extends Exception {
    public PriceException() {
        super("Price cannot be negative.");
    }

    // constructor with a message
    public PriceException(String message) {
        super(message);
    }
    
}
