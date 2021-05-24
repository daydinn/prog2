package Exceptions;

public class InvalidCartException extends Exception {


//Exception für Fehlerhafte eingaben im Warenkorb	
	
	
	
	public InvalidCartException() {
super("Wrong entry");
	
}
public InvalidCartException(String s) {
super(s);
}


}
