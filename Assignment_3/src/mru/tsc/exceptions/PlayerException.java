package mru.tsc.exceptions;

// checks for invalid player range for the board game
	public class PlayerException extends Exception {
	    public PlayerException() {
	        super("Minimum number of players cannot be greater than the maximum.");
	    }
	    // constructor with the message
	    public PlayerException(String message) {
	        super(message);
	    }

}
