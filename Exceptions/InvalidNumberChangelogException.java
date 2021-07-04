package Exceptions;

public class InvalidNumberChangelogException extends Exception {
	
	public InvalidNumberChangelogException() {
		super("Uncorrect Number!");
	}
	public InvalidNumberChangelogException(String s) {
		super(s);
	}
	
	
}
