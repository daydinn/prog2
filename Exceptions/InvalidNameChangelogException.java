package Exceptions;

public class InvalidNameChangelogException extends Exception {


public InvalidNameChangelogException() {
super("Wrong Name");
}
public InvalidNameChangelogException(String s) {
super(s);
}

}
