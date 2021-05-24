package Exceptions;

public class InvalidCartNameException extends Exception {


//Exception für Suche im Warenkorb fals ein falscher Name eingegeben wird
	
	
public  InvalidCartNameException() {
super("Wrong entry");

}
	
public InvalidCartNameException(String s) {
super(s);
}
	
	
}
	
	
	
