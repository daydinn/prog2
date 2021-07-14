package Exceptions;

/**
 * Used by: CheckNameCustomer
 * 
 *
 */
public class InvalidCustomerNameException extends Exception{

	public InvalidCustomerNameException() {
		super("Incorrect customer name!");
	}
	public InvalidCustomerNameException(String s) {
		super(s);
	}
	
}
