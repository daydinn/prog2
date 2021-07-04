package Exceptions;


public class InvalidCartNameException extends Exception{

	
	public InvalidCartNameException() {
		super("Incorrect input!");
	}
	public InvalidCartNameException(String s) {
		super(s);
	}
	
}
