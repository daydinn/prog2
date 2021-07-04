package LoginFunctions;

import java.util.List;

import Exceptions.IncorrectLoginDataException;
import Valueobjects.Customer;
import Valueobjects.Employee;

public class LoginCustomer {
	
	
	
	
	public LoginCustomer() {}
	
	private int currentNumber;
	
	/**
	 * Checks whether the username and password are correct, if not an exception is thrown
	 * @param list
	 * @param username
	 * @param password
	 * @return
	 * @throws IncorrectLoginDataException
	 */
	public boolean login(List<Customer> list, String username, String password) throws IncorrectLoginDataException {
		boolean x = false;
		for(Customer c : list) {
			if(!(c.getUsername().equals(username) && c.getPassword().equals(password))) {
				x = false;
			} else {
				currentNumber = c.getCustomerNr();
				x = true;
				return true;
				
			}
		}
		if(!x) {
			throw new IncorrectLoginDataException("Username or Password are uncorrect!");	
		}
		return false;
	}
	
	public int getNumber() {return currentNumber;} //returns the customer number;
	
}
