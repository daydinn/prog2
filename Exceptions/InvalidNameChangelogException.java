package Exceptions;

public class InvalidNameChangelogException extends Exception {
	
	public InvalidNameChangelogException() {
		super("Uncorrect Name!");
	}
	public InvalidNameChangelogException(String s) {
		super(s);
	}
	
	
}
