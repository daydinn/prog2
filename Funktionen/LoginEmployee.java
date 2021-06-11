package Funktionen;

import java.util.List;

import valueObjects.*;
import Exceptions.*;

public class LoginEmployee {
	public LoginEmployee() {}
	
	private int currentNumber;
	
	/**
	 * Überprüft die Benutzerdaten, falls diese stimmen wird true zurückgegeben, falls nicht wird eine exception geworfen
	 * @param liste
	 * @param username
	 * @param passwort
	 * @return
	 * @throws FlascheAnmeldedatenException
	 */
	public boolean login(List<Employee> employeeList, String firstname, String password) throws WrongLoginInformationsException {
		boolean x = false;
		for(Employee e : employeeList) {
			if(!(e.getFirstname().equals(firstname) && e.getPassword().equals(password))) {
				x = false;
			} else {
				currentNumber = e.getEmployeeNr();
				x = true;
				return true;
			}
		}
		if(!x) {
			throw new WrongLoginInformationsException("Firstname or password are wrong!");	
		}
		return false;
	}
	
	public int getNumber() {return currentNumber;} //gibt die Aktuelle Mitarbeiternummer zurück
	
}