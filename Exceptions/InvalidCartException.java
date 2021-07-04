package Exceptions;


public class InvalidCartException extends Exception {

	public InvalidCartException() {
		super("Incorrect input!");
	}
	public InvalidCartException(String s) {
		super(s);
	}
	
	
	
}
