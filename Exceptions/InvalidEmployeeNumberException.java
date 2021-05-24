package Exceptions;

public class InvalidEmployeeNumberException extends Exception {

public InvalidEmployeeNumberException() {
super("Incorrect Employee Nr");
}

public InvalidEmployeeNumberException(String s) {
super(s);
}

	
}




