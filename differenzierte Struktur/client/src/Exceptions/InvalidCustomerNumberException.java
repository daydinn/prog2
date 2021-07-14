package Exceptions;

public class InvalidCustomerNumberException extends Exception{

	public InvalidCustomerNumberException() {
		super("Incorrect customer number!");
	}
	public InvalidCustomerNumberException(String s) {
		super(s);
	}
	
}
