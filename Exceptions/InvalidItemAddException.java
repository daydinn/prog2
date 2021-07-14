package Exceptions;

/**
 *
 * Used by: CUI,Guis
 *
 */

public class InvalidItemAddException extends Exception {
	
	public InvalidItemAddException() {
		super("There was an error while adding an Item");
	}
	
	
	
	public InvalidItemAddException(String s) {
		super(s);
	}
	
	
}
