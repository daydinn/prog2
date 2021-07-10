package exceptions;

public class InvalidEmployeeNameException extends Exception{

	public InvalidEmployeeNameException() {
		super("Incorrect employee name!");
	}
	public InvalidEmployeeNameException(String s) {
		super(s);
	}
	
}
