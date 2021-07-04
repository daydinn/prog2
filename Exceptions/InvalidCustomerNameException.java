package Exceptions;

public class InvalidCustomerNameException extends Exception{

	public InvalidCustomerNameException() {
		super("Incorrect customer name!");
	}
	public InvalidCustomerNameException(String s) {
		super(s);
	}
	
}
