package interfaces;

import java.io.IOException;
import java.util.List;

import exceptions.InvalidNameChangelogException;
import exceptions.InvalidNumberChangelogException;
import valueObjects.Changelog;
import valueObjects.Customer;
import valueObjects.Employee;
import valueObjects.Item;

public interface ShopInterface {
	// -----------------------------------------------USERMANAGER-------------------------------------------------------//

	
	void add(Customer c) throws IOException;

	/**
	 * Description: deletes a customer, who has the given number
	 * 
	 * @param cNumber
	 */
	void cdeleting(int cNumber);

	/**
	 * Description: add a employee to Employee stock
	 * 
	 * @param m
	 * @throws IOException 
	 */
	void add(Employee e) throws IOException;

	/**
	 * Description: deletes a employee, who has the given number
	 * 
	 * @param mNummer
	 */
	void edeleting(int eNumber);

	/**
	 * Description: searches in the customer list for a NR
	 * 
	 * @param nr
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	Customer searchCustomerNr(int nr) throws IOException, ClassNotFoundException;

	/**
	 * Description: searches in the employee list for a NR
	 * 
	 * @param nr
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	Employee searchEmployeeNr(int nr) throws ClassNotFoundException, IOException;

	/**
	 * Description: searches in the customer list for a Name
	 * 
	 * @param name
	 * @return searchResult
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */

	List<Customer> searchCustomerName(String name) throws ClassNotFoundException, IOException;

	/**
	 * Description: searches in the employee list for a Name
	 * 
	 * @param name
	 * @return searchResult
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */

	List<Employee> searchEmployeeName(String name) throws ClassNotFoundException, IOException;

	/**
	 * Description: outputs employees as a list
	 * 
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	List<Employee> getEmployeeStock() throws ClassNotFoundException, IOException;

	/**
	 * Description: outputs customers as a list
	 * 
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	List<Customer> getCustomerStock() throws ClassNotFoundException, IOException;

	// ---------------------------------------------------------STORAGEMANAGER------------------------------------------------------------//


	/**
	 * adds an item to the inventory
	 * 
	 * @param a
	 * @throws IOException 
	 */

	void add(Item i) throws IOException;

	/**
	 * deletes an item that has the given number
	 * 
	 * @param nummer
	 */
	void ideleting(int number);

	/**
	 * Searches the item inventory for a name
	 * 
	 * @param name
	 * @return a list of search results
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	List<Item> searchItemName(String name) throws ClassNotFoundException, IOException;

	/**
	 * searches the list for the given item number
	 * 
	 * @param nr
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	Item searchItemNr(int nr) throws ClassNotFoundException, IOException;

	/**
	 * returns a list of the item inventory
	 * 
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	List<Item> getItemStock() throws ClassNotFoundException, IOException;

	// ---------------------------------------------------------CHANGELOGMANAGER------------------------------------------------------------//

	/**
	 * Description: add a new Changelog
	 * 
	 * @param c
	 * @throws IOException 
	 */
	void add(Changelog c) throws IOException;

	/**
	 * Gibt den aktuellen Changelog aus
	 * 
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	List<Changelog> getChangelog() throws ClassNotFoundException, IOException;


	/**
	 * searches the changelog for a name and returns all entries with the name.
	 * 
	 * @param name
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws InvalidNameChangelogException
	 */
	List<Changelog> searchChangelogName(String name) throws ClassNotFoundException, IOException, InvalidNameChangelogException;

	/**
	 * searches the changelog for a nr and returns all entries with the nr.
	 * 
	 * @param nr
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws InvalidNumberChangelogException
	 */

	Changelog searchChangelogNr(int nr) throws ClassNotFoundException, IOException, InvalidNumberChangelogException;
}
