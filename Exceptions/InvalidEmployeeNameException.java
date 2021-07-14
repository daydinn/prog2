package Exceptions;

/**
 * Used by: CheckNameEmployee
 * 
 *
 */
public class InvalidEmployeeNameException extends Exception{

	public InvalidEmployeeNameException() {
		super("Incorrect employee name!");
	}
	public InvalidEmployeeNameException(String s) {
		super(s);
	}
	
}
