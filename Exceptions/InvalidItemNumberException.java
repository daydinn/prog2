package Exceptions;

/**
 * 
 * Used by: CheckNumber,CheckNumberStock
 *
 */
public class InvalidItemNumberException extends Exception {
	
	public InvalidItemNumberException() {
		super("Incorrect item number");
	}
	public InvalidItemNumberException(String s) {
		super(s);
	}
	
	
}
