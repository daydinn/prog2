package LoginFunctions;

import java.util.List;

import Exceptions.IncorrectLoginDataException;
import Valueobjects.Employee;

public class LoginEmployee {
	
	public LoginEmployee() {}
	
	private int currentNumber;
	
	/**
	 * Checks the user password and username , if they are correct, true is returned, if not an exception is thrown
	 * @param list
	 * @param username
	 * @param password
	 * @return
	 * @throws IncorrectLoginDataException
	 */
	public boolean login(List<Employee> list, String username, String password) throws IncorrectLoginDataException {
		boolean x = false;
		for(Employee e : list) {
			if(!(e.getUsername().equals(username) && e.getPassword().equals(password))) {
				x = false;
			} else {
				currentNumber = e.getEmployeeNr();
				x = true;
				return true;
			}
		}
		if(!x) {
			throw new IncorrectLoginDataException("Username or password are uncorrect!");	
		}
		return false;
	}
	
	public int getNumber() {return currentNumber;} //returns the employee number;
	
}
