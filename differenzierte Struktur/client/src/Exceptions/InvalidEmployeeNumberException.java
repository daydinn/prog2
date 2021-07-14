package Exceptions;

public class InvalidEmployeeNumberException extends Exception {

	public InvalidEmployeeNumberException() {
		super("Incorrect employee nr!");
	}
	public InvalidEmployeeNumberException(String s) {
		super(s);
	}
	
}