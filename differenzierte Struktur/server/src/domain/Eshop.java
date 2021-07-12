package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import exceptions.InvalidCartException;
import exceptions.InvalidCartNameException;
import exceptions.InvalidCustomerNameException;
import exceptions.InvalidCustomerNumberException;
import exceptions.InvalidEmployeeNameException;
import exceptions.InvalidEmployeeNumberException;
import exceptions.InvalidItemNameException;
import exceptions.InvalidItemNumberException;
import exceptions.InvalidNameChangelogException;
import exceptions.InvalidNumberChangelogException;
import valueObjects.*;

public class Eshop implements interfaces.ShopInterface {

	private static Employee system;
	private int currentCustomer;;
	private int currentEmployee;
	private Storage storage;
	private EmployeeManagement employeeManagement;
	private CustomerManagement customerManagement;
	private ChangelogManager logmanager;
	private Cart cart;
	private String datei = "";

	/**
	 * Beschriebung: wird nicht mehr ben�tigt, kommt aus der CUI
	 * 
	 * @param liste
	 */
	private void outputEmmployeeList(List<Employee> list) {
		if (list.isEmpty()) {
			System.out.println("List is empty.");
		} else {
			for (Employee m : list) {
				System.out.println(m);
			}
		}
	}

	/**
	 * Beschreibgung: wird nicht mehr ben�tigt, kommt aus der CUI
	 * 
	 * @param liste
	 */
	private void outputCustomerLsit(List<Customer> list) {
		if (list.isEmpty()) {
			System.out.println("List is empty.");
		} else {
			for (Customer c : list) {
				System.out.println(c);
			}
		}
	}

	/**
	 * wird nicht mehr ben�tigt, hat in der CUI eine Liste ausgegeben
	 * 
	 * @param liste ist die Liste die ausgegegeben werden soll
	 */
	private void outputItemlist(List<Item> list) {
		if (list.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			for (Item i : list) {
				System.out.println(i);
			}
		}
	}

	public int newNumberCustomer(List<Customer> list) {
		// outputs the highest customer number, it compares all customers in the list
		// with getCustomerNr
		Customer maxByNumber = list.stream().max(Comparator.comparing(Customer::getCustomerNr))
				.orElseThrow(NoSuchElementException::new);
		// the highest customer number
		int newNumber = maxByNumber.getCustomerNr() + 1;
		// returns the new number
		return newNumber;
	}

	public int newNumberMitarbeiter(List<Employee> liste) {
		Employee maxByNumber = liste.stream().max(Comparator.comparing(Employee::getEmployeeNr))
				.orElseThrow(NoSuchElementException::new);
		int newNumber = maxByNumber.getEmployeeNr() + 1;
		return newNumber;
	}

	/**
	 * @return true: Item for the number has been found. false: no item has been
	 *         found and Exception is thrown
	 * @throws InvalidArtikelNummerException
	 */
	public boolean checkNumber(int iNum) throws InvalidItemNumberException {
		boolean x = false;
		for (Item i : storage.getAllItems()) { // goes through the item list
			System.out.println(i.getNumber() + " | " + iNum);
			if (i.getNumber() == iNum) { // Checks whether the item number and Inr match
				x = true; // if yes, true
				break;
			} else {
				x = false; // if no false
			}
		}
		if (!x) {
			throw new InvalidItemNumberException(); // throws the exception
		}
		return x;
	}

	/**
	 * 
	 * @return true: A name has been found. false: no item with the name has been
	 *         found.
	 * @throws InvalidArtikelNameException
	 */
	public boolean checkName(String iName) throws InvalidItemNameException {
		boolean x = false;
		for (Item i : storage.getAllItems()) {
			if (i.getName().equals(iName)) {
				x = true;
				break;
			} else {
				x = false;
			}
		}
		if (!x) {
			throw new InvalidItemNameException();
		}
		return x;
	}

	/**
	 * 
	 * @return true: A name has been found. false: no item with the name has been
	 *         found.
	 * @throws InvalidCustomerNameException
	 *
	 */
	public boolean checkNameCus(String cName) throws InvalidCustomerNameException {
		boolean x = false;
		for (Customer c : customerManagement.getAllCustomers()) {
			if (c.getFirstname().equals(cName)) {
				x = true;
				break;
			} else {
				x = false;
			}
		}
		if (!x) {
			throw new InvalidCustomerNameException();
		}
		return x;
	}

	/**
	 * 
	 * @return true: A name has been found. false: no item with the name has been
	 *         found.
	 * 
	 * @throws InvalidEmployeeNameException
	 *
	 */
	public boolean checkNameEmp(String eName) throws InvalidEmployeeNameException {
		boolean x = false;
		for (Employee e : employeeManagement.getAllEmployees()) {
			System.out.println(e.getFirstname() + " | " + eName);
			if (e.getFirstname().equals(eName)) {
				x = true;
				break;
			} else {
				x = false;
			}
		}
		if (!x) {
			throw new InvalidEmployeeNameException();
		}
		return x;
	}

	/**
	 * 
	 * @return true: Item number and item stock match, inventory is increased.
	 *         false: exception is thrown
	 * @throws InvalidArtikelNummerException
	 */
	public boolean checkNumberStock(int iNum, int iSto) throws InvalidItemNumberException {
		boolean x = false;
		for (Item i : storage.getAllItems()) {
			if (i.getNumber() == iNum) {
				i.setStock(iSto);
				x = true;
				break;
			} else {
				x = false;
			}
		}
		if (!x) {
			throw new InvalidItemNumberException();
		}
		return x;
	}

	/**
	 * 
	 * @return true: Employee number and number match. False: exception is thrown
	 * @throws InvalidMitarbeiterNummerException
	 */
	public boolean checkNumberEmployee(int eNum) throws InvalidEmployeeNumberException {
		boolean x = false;
		for (Employee e : employeeManagement.getAllEmployees()) {
			if (e.getEmployeeNr() == eNum) {
				x = true;
				break;
			} else {
				x = false;
			}
		}
		if (!x) {
			throw new InvalidEmployeeNumberException();
		}
		return x;
	}

	/**
	 * 
	 * @return true: Customer number and number match. False: exception is thrown
	 * @throws InvalidKundenNummerException
	 */
	public boolean checkNumberCustomer(int cNum) throws InvalidCustomerNumberException {
		boolean x = false;
		for (Customer c : customerManagement.getAllCustomers()) {
			if (c.getCustomerNr() == cNum) {
				x = true;
				break;
			} else {
				x = false;
			}
		}
		if (!x) {
			throw new InvalidCustomerNumberException();
		}
		return x;
	}

	/**
	 * 
	 * @return true: Item name and name match. False: exception is thrown
	 * @throws InvalidArtikelNameException
	 */
	public boolean checkNameItem(String iName) throws InvalidItemNameException {
		boolean x = false;
		for (Item i : storage.getAllItems()) {
			System.out.println(i.getName() + " | " + iName);
			if (i.getName().equals(iName)) {
				x = true;
				break;
			} else {
				x = false;
			}
		}
		if (!x) {
			throw new InvalidItemNameException();
		}
		return x;
	}

	/**
	 * 
	 * @return true: Item number and number & number and stock match. false:
	 *         exception is thrown
	 * @throws InvalidWarenkorbException
	 */
	public boolean checkCart(int iNum, int iAmo) throws InvalidCartException {
		boolean x = false;
		for (Item i : storage.getAllItems()) {
			System.out.println(i.getNumber() == iAmo && iAmo <= i.getStock());
			System.out.println(!(i.getNumber() == iNum));
			System.out.println(i.getStock() <= iAmo);
			if (i.getNumber() == iNum && iAmo <= i.getStock() && iAmo >= i.getBulk()) {
				x = true;
				break;
			} else {
				x = false;
			}
		}
		if (!x) {
			throw new InvalidCartException();
		}
		return x;
	}

	/**
	 * 
	 * @return true: Item number and amount match the values. False: exception is
	 *         thrown.
	 * @throws InvalidWarenkorbException
	 */
	public boolean checkCartDelete(int tiNum, int tiAmo) throws InvalidCartException {
		boolean x = false;
		for (cartItem ti : cart.getCart()) {
			if (ti.getItem().getNumber() == tiNum && ti.getAmount() >= tiAmo) {
				x = true;
				break;
			} else {
				x = false;
			}

		}
		if (!x) {
			throw new InvalidCartException();
		}
		return x;
	}

	/**
	 * 
	 * @return true: an Item has been found in the search. False: exception is
	 *         thrown.
	 * @throws InvalidWarenkorbNameException
	 */
	public boolean checkCartSearch(String aName) throws InvalidCartNameException {
		boolean x = false;
		for (cartItem ti : cart.getCart()) {
			if (ti.getItem().getName().equals(aName)) {
				x = true;
				break;
			} else {
				x = false;
			}
		}
		if (!x) {
			throw new InvalidCartNameException();
		}
		return x;
	}

	@Override
	public void add(Customer c) throws IOException {
		customerManagement.add(c);
		
	}

	@Override
	public void cdeleting(int cNumber) {
		customerManagement.deleteCustomer(cNumber);
		
	}

	@Override
	public void add(Employee e) throws IOException {
		employeeManagement.add(e);
		
	}

	@Override
	public void edeleting(int eNumber) {
		employeeManagement.deleteEmployee(eNumber);
		
	}

	@Override
	public Customer searchCustomerNr(int nr) throws IOException, ClassNotFoundException {
		Customer retC = customerManagement.searchByNumber(nr).get(0);
		return retC;
	}

	@Override
	public Employee searchEmployeeNr(int nr) throws ClassNotFoundException, IOException {
		Employee retE = employeeManagement.searchByNumber(nr).get(0);
		return retE;
	}

	@Override
	public List<Customer> searchCustomerName(String name) throws ClassNotFoundException, IOException {
		List<Customer> retCList = customerManagement.searchByName(name);
		return retCList;
	}

	@Override
	public List<Employee> searchEmployeeName(String name) throws ClassNotFoundException, IOException {
		List<Employee> retEList = employeeManagement.searchByName(name);
		return retEList;
	}

	@Override
	public List<Employee> getEmployeeStock() throws ClassNotFoundException, IOException {
		return employeeManagement.getEmployeeStock();
	}

	@Override
	public List<Customer> getCustomerStock() throws ClassNotFoundException, IOException {
		return customerManagement.getCustomerStock();
	}

	@Override
	public void add(Item i) throws IOException {
		storage.add(i);
		
	}

	@Override
	public void ideleting(int number) {
		storage.deleteItem(number);
		
	}

	@Override
	public List<Item> searchItemName(String name) throws ClassNotFoundException, IOException {
		List<Item> retIList = storage.searchByName(name);
		return retIList;
	}

	@Override
	public Item searchItemNr(int nr) throws ClassNotFoundException, IOException {
		Item retI = storage.searchByNumber(nr).get(0);
		return retI;
	}

	@Override
	public List<Item> getItemStock() throws ClassNotFoundException, IOException {
		return storage.getItemStock();
	}

	@Override
	public void add(Changelog c) throws IOException {
		logmanager.add(c);
		
	}

	@Override
	public List<Changelog> getChangelog() throws ClassNotFoundException, IOException {
		return logmanager.getChangelog();
	}

	@Override
	public List<Changelog> searchChangelogName(String name) throws ClassNotFoundException, IOException, InvalidNameChangelogException {
		List<Changelog> retClList = logmanager.searchChangelogName(name);
		return retClList;
	}

	@Override
	public Changelog searchChangelogNr(int nr) throws ClassNotFoundException, IOException, InvalidNumberChangelogException {
		Changelog retCl = logmanager.searchChangelogNr(nr).get(0);
		return retCl;
	}
}
