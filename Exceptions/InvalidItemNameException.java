package Exceptions;

public class InvalidItemNameException extends Exception {

	public InvalidItemNameException() {
		super("Incorrect item name");
	}
	public InvalidItemNameException(String s) {
		super(s);
	}
	
	
}
