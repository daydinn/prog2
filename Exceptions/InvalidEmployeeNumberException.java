package Exceptions;

/**
 * Used by: CheckNumberEmployee
 * 
 *
 */
public class InvalidEmployeeNumberException extends Exception {

	public InvalidEmployeeNumberException() {
		super("Incorrect employee nr!");
	}
	public InvalidEmployeeNumberException(String s) {
		super(s);
	}
	
}