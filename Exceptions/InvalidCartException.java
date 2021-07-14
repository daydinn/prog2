package Exceptions;

/**
 *Used by: CheckCart,CheckCartChange,CheckCartDelete
 *
 *
 */
public class InvalidCartException extends Exception {

	public InvalidCartException() {
		super("Incorrect input!");
	}
	public InvalidCartException(String s) {
		super(s);
	}
	
	
	
}