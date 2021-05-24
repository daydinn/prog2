package Exceptions;

public class InvalidItemNumberException extends Exception{


public InvalidItemNumberException() {
super("Incorrect Item number");

}

public InvalidItemNumberException(String s) {
super(s);
}



}
