package Funktionen;

import java.util.List;

import valueObjects.*;
import Exceptions.*;

public class LoginCustomer {
	
	
	
public LoginCustomer() {}
	
private int currentNumber;
	
	/**
	 * Prüft ob Username und Passwort stimmen, falls nicht wird eine Exception geworfen
	 * @param liste
	 * @param username
	 * @param passwort
	 * @return
	 * @throws FlascheAnmeldedatenException
	 */
public boolean login(List<Customer> customerList, String firstname, String password) throws WrongLoginInformationsException {
		boolean x = false;
		for(Customer c : customerList) {
			if(!(c.getFirstname().equals(firstname) && c.getPassword().equals(password))) {
				x = false;
			} else {
				currentNumber = c.getCustomerNr();
				x = true;
				return true;
				
			}
		}
		if(!x) {
			throw new WrongLoginInformationsException("Firstname or password are wrong!!");	
		}
		return false;
	}
	
	public int getNumber() {return currentNumber;} //gibt die Kundennummer zurück;
	
}