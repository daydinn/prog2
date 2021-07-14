package Exceptions;

/**
 *
 * Used by: Shop Login Employee, Shop Login Customer
 *
 */
public class IncorrectLoginDataException extends Exception {
	public IncorrectLoginDataException(String s) {
		super(s);
	}
}
