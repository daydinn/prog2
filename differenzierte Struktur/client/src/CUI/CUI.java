package CUI;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Stream;

import javax.swing.JLabel;


import Exceptions.IncorrectLoginDataException;
import Exceptions.InvalidCartException;
import Exceptions.InvalidCartNameException;
import Exceptions.InvalidCustomerNameException;
import Exceptions.InvalidCustomerNumberException;
import Exceptions.InvalidEmployeeNameException;
import Exceptions.InvalidEmployeeNumberException;
//import Exceptions.InvalidItemAddException;
import Exceptions.InvalidItemNameException;
import Exceptions.InvalidItemNumberException;

import LoginFunctions.LoginCustomer;
import LoginFunctions.LoginEmployee;
import valueObjects.*;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

//Eshop

public class CUI {

	private static Storage storage;
	private static EmployeeManagement employeeManagement;
	private static CustomerManagement customerManagement;
	private static Changelog changelog;
	private static ChangelogManager logmanager;
	private Cart cart;
	static List<String> log = new ArrayList<String>();
	private List<Item> ilist;
	private List<Customer> clist;
	private List<Employee> elist;
	private List<Changelog> chlist;

	private static Employee system;

	private int currentCustomer;
	private int currentEmployee;

	/**
	 * Description: loads value objects by reading them from their text files
	 * 
	 * @param dItems
	 * @param dEmployees
	 * @param dCustomers
	 * @param dLog
	 */
	public CUI(String dItems, String dEmployees, String dCustomers, String dLog) {

		try {
			storage = new Storage(dItems);
		} catch (IOException e) {

			e.printStackTrace();
		}

		try {
			employeeManagement = new EmployeeManagement(dEmployees);
		} catch (IOException e) {

			e.printStackTrace();
		}

		try {
			customerManagement = new CustomerManagement(dCustomers);
		} catch (IOException e) {

			e.printStackTrace();
		}

		logmanager = new ChangelogManager();

		try {
			logmanager.readData("Log");
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			logmanager.writeData("Log");
		} catch (IOException e) {

			e.printStackTrace();
		}

		elist = employeeManagement.getAllEmployees();

		clist = customerManagement.getAllCustomers();

		ilist = storage.getAllItems();

		logmanager = new ChangelogManager();
		cart = new Cart(ilist);

	}

	/**
	 * Description: reads the console entries
	 * 
	 * @return input
	 */
	public String readEntry() {

		String input = "";
		Scanner sc = new Scanner(System.in);
		input = sc.nextLine();

		// Function to end the program
		if (input.equals("quit")) {
			System.out.println("the program is now terminated...");

			try {
				logmanager.writeData("The program is ended.");
				logmanager.writeData("Log");
			} catch (IOException e) {

				e.printStackTrace();
			}
			System.exit(0);
		}
		return input;
	}

	/**
	 * starts cui
	 */
	public void run() {
		// Variable for inputs from the console
		String input = "";

		// Main for the user interface
		do {
			getMenu();
		} while (!input.equals("q"));

	}

	/**
	 * Description: First Menu(Login or Register)
	 */
	public void getMenu() {
		String input = "";
		System.out.println("");
		System.out.println("Please log in or register ......");
		System.out.println("To login, press  'A'");
		System.out.println("To register, press  'R'");
		System.out.println("");
		System.out.print(">>");
		input = readEntry();
		switch (input) {
		case "A": // Login
			shopLogin();
			break;
		case "R": // Register
			shopCustomerRegistration(false);
			break;
		default:
			System.out.println("Incorrect entry!");
			getMenu();
		}
	}

	/**
	 * Description: Menu for employees
	 */
	public void employeeMenu() {
		String input = "";
		String itemName = "";
		String itemNumber = "";
		String itemPrice = "";
		String itemStock = "";
		String itemMinimumStock = "";
		String eNumber = "";
		String eName = "";
		String cNumber = "";
		String cName = "";
		String itemAmount = "";
		String itemBulk = "";
		int iAmo;
		int cNum;
		int eNum;
		int iMin;
		int iSto;
		int iNum;
		int iBulk;
		double iPri;

		System.out.println("");
		System.out.println("-----------------------------------------------");
		System.out.println("1. Output all items: '1': ");
		System.out.println("2. Add an Item: '2': ");
		System.out.println("3. Delete an Item: '3': ");
		System.out.println("4. Search an Item: '4': ");
		System.out.println("5. Sort Items by Name: '5': ");
		System.out.println("6. Sort Items by number: '6': ");
		System.out.println("7. Change Amount of Item: '7'  ");
		System.out.println("8. Create new employee: '8': ");
		System.out.println("9. Show all employees: '9': ");
		System.out.println("10. Delete an employee: '10': ");
		System.out.println("11. Search an employee by Nr: '11': ");
		System.out.println("12. Search an employee by Name: '12': ");
		System.out.println("13. Create a customer: '13': ");
		System.out.println("14. Delete a customer: '14': ");
		System.out.println("15. Show all customers: '15': ");
		System.out.println("16. Search a customer by Nr: '16': ");
		System.out.println("17. Search a customer by Name: '17': ");
		System.out.println("18. Logout: '18': ");
		System.out.println("19. Output ChangeLog: '19': ");
		System.out.println("20. Sort by Date: '20': ");
		System.out.println("-----------------------------------------------");
		System.out.println("");
		System.out.print(">>");

		input = readEntry();
		switch (input) {
		case "1": // output all items
			System.out.println("");
			getItemlist(ilist);
			System.out.println("");

			employeeMenu();
			break;

		case "2": // add an Item

			System.out.println("");
			System.out.println("Please enter the item name: ");
			System.out.println("");
			System.out.print(">>");
			itemName = readEntry();

			System.out.println("");
			System.out.println("");
			iNum = newNumberItem(storage.getAllItems());
			System.out.println("New item gets the number : " + iNum);
			System.out.println("");

			System.out.println("");
			System.out.println("Please enter the price of the item : ");
			System.out.println("");
			System.out.print(">>");
			itemPrice = readEntry();
			if (itemPrice.isEmpty()) {
				break;
			}
			iPri = Double.parseDouble(itemPrice);

			System.out.println("");
			System.out.println("Please enter the item stock: ");
			System.out.println("");
			System.out.print(">>");
			itemStock = readEntry();
			if (itemStock.isEmpty()) {
				break;
			}
			iSto = Integer.parseInt(itemStock);

			System.out.println("");
			System.out.println("Please enter the minimum stock of item : ");
			System.out.println("");
			System.out.print(">>");
			itemMinimumStock = readEntry();
			if (itemMinimumStock.isEmpty()) {
				break;
			}
			iMin = Integer.parseInt(itemMinimumStock);

			System.out.println("");
			System.out.println("Please enter the Bulk : ");
			System.out.println("");
			System.out.print(">>");
			itemBulk = readEntry();
			if (itemMinimumStock.isEmpty()) {
				break;
			}
			iBulk = Integer.parseInt(itemBulk);

			System.out.println("");

			if (!itemName.isEmpty() && iNum != 0) { // if all fields are filled correctly
				storage.addAnItem(itemName, iNum, iPri, iSto, iMin, iBulk); // add new item to the Storage
				logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
						"The Item: " + itemName + "with Nr " + iNum + " has been succesfully added.", true));
				// throw the exception
			}

			else {
				logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
						"You can not add the Item: " + itemName + "with Nr " + iNum + " ", true));
			}

			try {
				storage.writeItems(); // saves the items in the TXT file
				logmanager.writeData("log");
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			break;

		case "3": // delete Items
			System.out.println("");
			System.out.println("Please enter the item number of the item to be deleted: ");
			System.out.println("");
			System.out.print(">>");
			itemNumber = readEntry();
			iNum = Integer.parseInt(itemNumber);

			try { // try and catch for the correctness of the item number
				if (checkNumber(iNum)) { // if yes delete item
					storage.deleteItem(iNum); // deletes the item from the storage
					logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
							"The Item with number : " + iNum + " has been deleted!", true));
					try {
						storage.writeItems(); // saves the items in the TXT file
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} catch (InvalidItemNumberException ex) { // if not, catch the exception and give an error message
				System.out.println("");
				logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), ex.getMessage(),
						true)); // schriebt den Fehler im Changelog and outputs error message
				System.out.println("Please enter a valid number"); // error message if the item number is wrong in the
																	// CUI
				employeeMenu();

			}
			break;

		case "4": // search for an Item
			System.out.println("");
			System.out.println("Itemname:    ");
			System.out.print(">>");
			itemName = readEntry();
			try {
				if (checkNameItem(itemName)) {
					List<Item> searchlist = storage.searchByName(itemName);
					System.out.println("");
					System.out.println("Results ->");
					System.out.println(searchlist);
					employeeMenu();
				}
			} catch (InvalidItemNameException ex) {
				System.out.println("");
				System.out.println(ex.getMessage());
				System.out.println("Please enter a valid item name!");

				logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
						"There was an error while searching for an item", false));
				employeeMenu();
			}

			break;

		case "5": // Sort items by name
			System.out.println("");
			sortNameItemList(storage.getAllItems());
			System.out.println("");

			employeeMenu();
			break;

		case "6": // Sort items by number
			System.out.println("");

			sortNumberItemList(storage.getAllItems());
			System.out.println("");

			employeeMenu();
			break;

		case "7": // Change the amount of items

			getItemlist(ilist);
			System.out.println("");
			System.out.println("Please enter the number of item: ");
			System.out.println("");
			System.out.print(">>");
			itemNumber = readEntry();
			iNum = Integer.parseInt(itemNumber);

			System.out.println("");
			System.out.println("Please enter the item stock: ");
			System.out.println("");
			System.out.print(">>");
			itemStock = readEntry();
			iSto = Integer.parseInt(itemStock);

			try { // try to change the item Stocks
				if (checkNumberStock(iNum, iSto)) { // if the entry was correct ..

					storage.getAllItems(); // update the Table
					getItemlist(ilist);
					System.out.println("");
					System.out.println("Stock changed!"); // report the successful change
					logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
							"The Item: " + iNum + " hast been incrased by : " + iSto, true));
					try {
						storage.writeItems(); // save/write the new item inventory
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} catch (InvalidItemNumberException ex) { // if the entry was not correct ..
				// System.out.println("Incorrect item number!"); //errormessage
				System.out.println(ex.getMessage()); // output the error message in the console
				logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
						ex.getMessage() + " while increasing the stock", true));
			}
			employeeMenu();

			break;

		case "8": // create new employee
			System.out.println("");
			shopEmployeeRegistration();
			System.out.println("");
			try {
				customerManagement.writeCustomers();
			} catch (IOException e) {

				e.printStackTrace();
			}
			employeeMenu();
			break;
		case "9": // show all employees
			System.out.println("");
			getEmployeelist(elist);
			System.out.println("");
			employeeMenu();
			break;
		case "10": // Delete employee
			System.out.println("");
			System.out.println("Please enter the employee number of the employee to be deleted: ");
			System.out.println("");
			System.out.print(">>");
			eNumber = readEntry();
			eNum = Integer.parseInt(eNumber);
			employeeManagement.deleteEmployee(eNum);
			System.out.println("");
			System.out.println("The employee has been deleted.");
			try {
				if (checkNumberEmployee(eNum)) {
					employeeManagement.deleteEmployee(eNum);

					System.out.println(employeeManagement.getAllEmployees());
					logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
							"The employee with the number: " + eNum + " has been deleted.", true));
					try {
						employeeManagement.writeEmployees();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} catch (InvalidEmployeeNumberException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Please enter a valid employee number!");
				logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
						"Incorrect employee number while deleting!", true));

			}

			System.out.println("");
			employeeMenu();
			break;

		case "11": // Search an employee by Nr
			System.out.println("");
			System.out.println("EmployeeNr:    ");
			eNumber = readEntry();
			eNum = Integer.parseInt(eNumber);

			try {
				if (checkNumberEmployee(eNum)) {
					System.out.println("");
					elist = employeeManagement.searchByNumber(eNum);
					System.out.println(elist);
					System.out.println("");
					employeeMenu();
					System.out.println("");
				}
			} catch (InvalidEmployeeNumberException ex) {
				System.out.println("Invalid Nr!");
				System.out.println(ex.getMessage());
				System.out.println("");
				logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
						"Search Error! " + ex.getMessage(), true));
				employeeMenu();
				System.out.println("");
			}
			System.out.println("");
			employeeMenu();
			break;

		case "12": // Search an employee by Name
			System.out.println("");
			System.out.println("Employeename:    ");
			eName = readEntry();

			try {
				if (checkNameEmp(eName)) {
					System.out.println("");
					elist = employeeManagement.searchByName(eName);
					System.out.println(elist);
					System.out.println("");
					employeeMenu();
					System.out.println("");

				}
			} catch (InvalidEmployeeNameException ex) {
				System.out.println("Invalid Name!");
				System.out.println("");
				System.out.println(ex.getMessage());
				logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
						"Search Error! " + ex.getMessage(), true));
			}

			System.out.println("");
			employeeMenu();
			break;

		case "13": // create new customer
			System.out.println("");
			shopCustomerRegistration(true);
			System.out.println("");
			try {
				customerManagement.writeCustomers();
			} catch (IOException e) {

				e.printStackTrace();
			}
			employeeMenu();
			break;
		case "14": // Delete customers
			System.out.println("");
			System.out.println("Please enter the customer number of the customer to be deleted: ");
			System.out.println("");
			System.out.print(">>");
			cNumber = readEntry();
			cNum = Integer.parseInt(cNumber);
			customerManagement.deleteCustomer(cNum);
			System.out.println("");
			System.out.println("The customer has been deleted successfully.");
			try {
				if (checkNumberCustomer(cNum)) {
					customerManagement.deleteCustomer(cNum);
					logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
							"The Customer with Number: " + cNum + " has been deleted", true));
					System.out.println("");
					System.out.println(customerManagement.getAllCustomers());
					System.out.println("");
					try {
						customerManagement.writeCustomers();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} catch (InvalidCustomerNumberException ex) {
				System.out.println(ex.getMessage());
				logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
						"Error while deleting a customer", true));
				System.out.println("Please enter a valid customer number!");
			}
			System.out.println("");
			employeeMenu();

			break;
		case "15": // Show all customers
			System.out.println("");
			getCustomerlist(clist);
			System.out.println("");
			employeeMenu();
			break;
		case "16": // search customers by nr

			System.out.println("");
			System.out.println("CustomerNr:    ");
			cNumber = readEntry();
			cNum = Integer.parseInt(cNumber);

			try {
				if (checkNumberCustomer(cNum)) {
					System.out.println("");
					clist = customerManagement.searchByNumber(cNum);
					System.out.println(clist);
					System.out.println("");
					employeeMenu();
					System.out.println("");
				}
			} catch (InvalidCustomerNumberException ex) {
				System.out.println("Invalid Nr!");
				System.out.println(ex.getMessage());
				System.out.println("");
				logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0),
						"Search Error! " + ex.getMessage(), true));
				employeeMenu();
				System.out.println("");
			}
			System.out.println("");
			employeeMenu();
			break;

		case "17": // search customers by name
			System.out.println("");
			System.out.println("Customername:    ");
			cName = readEntry();

			try {
				if (checkNameCus(cName)) {
					System.out.println("");
					clist = customerManagement.searchByName(cName);
					System.out.println(clist);
					System.out.println("");
					employeeMenu();
					System.out.println("");

				}
			} catch (InvalidCustomerNameException ex) {
				System.out.println("Invalid Name!");
				System.out.println("");
				System.out.println(ex.getMessage());
				logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0),
						"Search Error! " + ex.getMessage(), true));
			}

			System.out.println("");
			employeeMenu();
			break;

		case "18": // logout
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = dateFormat.format(date);
			System.out.println("Date: " + strDate);
			logmanager.add(
					new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "has logged out", true));
			getLog();
			System.out.println("You are now logging out ...");
			shopLogin();

			try {
				logmanager.writeData("Log");
			} catch (IOException e) {

				e.printStackTrace();
			}

			break;
		case "19": // output changelog
			System.out.println("");
			getLog();
			System.out.println("");

			employeeMenu();
			break;
		case "20": // sort by date
			try {
				logmanager.readData("Log");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (log.isEmpty()) {
				System.out.println("List is empty.");
			}
			sortDateChangelogliste(logmanager.getChangelog());

			employeeMenu();
			break;

		default:
			System.out.println("Incorrect entry!");

			logmanager.add(new Changelog(system, "ncorrect entry", true));
			System.out.println("Please try again");
			shopLogin();

		}
		getMenu();
	}

	/**
	 * Description: Menu for customers
	 */
	public void customerMenu() {
		String input = "";
		String itemName = "";
		String itemNumber = "";
		int iNum;
		String itemAmount = "";
		int iAmo;

		getItemlist(ilist);

		System.out.println("");
		System.out.println("-----------------------------------------------");
		System.out.println("1. Output all items: '1': ");
		System.out.println("2. Search for an Item: '2': ");
		System.out.println("3. Sort items by name: '3': ");
		System.out.println("4. Sort items by number: '4': ");
		System.out.println("5. Add an item to shopping cart: '5': ");
		System.out.println("6. Change the stock of items in shopping cart '6': ");
		System.out.println("7. Empty cart: '7': ");
		System.out.println("8. View Shopping Cart: '8': ");
		System.out.println("9. Buy cart: '9': ");
		System.out.println("10. Sign out: '10': ");
		System.out.println("-----------------------------------------------");
		System.out.println("");
		System.out.print(">>");

		input = readEntry();
		switch (input) {
		case "1": // show all items
			System.out.println("All Items :");
			System.out.println("");
			getItemlist(ilist);
			System.out.println("");

			customerMenu();
			break;

		case "2": // search an Item

			System.out.println("");
			System.out.println("Itemname:    ");
			System.out.print(">>");
			itemName = readEntry();
			try {
				if (checkNameItem(itemName)) {
					List<Item> searchlist = storage.searchByName(itemName);
					System.out.println("");
					System.out.println("Results ------------------------------------->");
					System.out.println(searchlist);

					System.out.println("..............................................");
					System.out.println("");
					customerMenu();
				}
			} catch (InvalidItemNameException ex) {
				System.out.println("");
				System.out.println(ex.getMessage());
				System.out.println("Please enter a valid item name!");

				logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0),
						"There was an error while searching for an item", false));
				customerMenu();
			}
			break;

		case "3": // Sort items by name
			System.out.println("");
			System.out.println("Items are sorted by name...");
			System.out.println("");

			sortNameItemList(storage.getAllItems());
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("");
			customerMenu();
			break;

		case "4": // Sort Items by Number
			System.out.println("");
			System.out.println("Items are sorted by number...");
			System.out.println("");

			sortNumberItemList(storage.getAllItems());
			System.out.println("");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			customerMenu();
			break;

		case "5": // Add an item to Shopping Cart
			System.out.println("");
			System.out.println("Please enter the item number of the item: ");
			System.out.println("");
			System.out.print(">>");
			itemNumber = readEntry();
			iNum = Integer.parseInt(itemNumber);

			System.out.println("");
			System.out.println("Please enter the amount of items: ");
			System.out.println("");
			System.out.print(">>");
			itemAmount = readEntry();
			iAmo = Integer.parseInt(itemAmount);

			try {

				for (cartItem ti : cart.getCart()) {
					if (ti.getItem().getNumber() == iNum) {
						ti.setAmount(ti.getAmount() + iAmo);
						System.out.println(
								"---------Cart-------------------------------------------------------------------------------------------------------------");
						cart.output();
						System.out.println("");
						System.out.println("");
						System.out.println("totalprice =" + cartGetTotalprice());
						System.out.println(
								"--------------------------------------------------------------------------------------------------------------------------");
						System.out.println("");
						customerMenu();
					}
				}
			} catch (Exception ex) {
				System.out.println("");
				System.out.println("Error: invalid entrys");
				System.out.println("");
				logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0),
						"Incorrect entry while adding to shopping cart", false));
				System.out.println("");
				customerMenu();
			}

			try {
				if (checkCart(iNum, iAmo)) {
					cart.addItem(iNum, iAmo);

					System.out.println(
							"---------Cart-------------------------------------------------------------------------------------------------------------");
					cart.output();
					System.out.println("");
					System.out.println("");
					System.out.println("totalprice =" + cartGetTotalprice());
					System.out.println(
							"--------------------------------------------------------------------------------------------------------------------------");
					System.out.println("");
					System.out.println("");

					System.out.println("Item added.");
					System.out.println("");

					logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0),
							"The Item: " + iNum + " has been added  " + iAmo + "times to the Cart", false));
					System.out.println("");
					customerMenu();

				}
			} catch (InvalidCartException ex) {
				System.out.println("");
				System.out.println("Error");
				System.out.println("Uncorrect Entry!");
				logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0),
						"Incorrect entry while adding to shopping cart", false));
				System.out.println(ex.getMessage());
				System.out.println("");
				cart.output();
				System.out.println("");
				customerMenu();

			}
			break;

		case "6": // change the amount of items in Cart

			System.out.println("");
			System.out.println("Please enter the item number of the item: ");
			System.out.println("");
			System.out.print(">>");
			itemNumber = readEntry();
			iNum = Integer.parseInt(itemNumber);

			System.out.println("");
			System.out.println("Please enter the amount of Items ");
			System.out.println("");
			System.out.print(">>");
			itemAmount = readEntry();
			iAmo = Integer.parseInt(itemAmount);

			cart.output();

			try {

				if (checkCartChange(iNum, iAmo)) {
					cart.changeStockofItem(iNum, iAmo);
					System.out.println("");
					logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0),
							"The amount of Item:" + iNum + " has been tryed change Item Amount to " + iAmo, true));
					System.out.println("");
					System.out.println(
							"---------Cart-------------------------------------------------------------------------------------------------------------");
					cart.output();
					System.out.println("");
					System.out.println("");
					System.out.println("totalprice =" + cartGetTotalprice());
					System.out.println(
							"--------------------------------------------------------------------------------------------------------------------------");
					System.out.println("");
					customerMenu();
				}
			} catch (InvalidCartException ex) {
				System.out.println(ex.getMessage());

				logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0),
						"Uncorrect entry while deleting an Item", true));

				System.out.println("");
				System.out.println(
						"---------Cart-------------------------------------------------------------------------------------------------------------");
				cart.output();
				System.out.println("");
				System.out.println("");
				System.out.println("totalprice =" + cartGetTotalprice());
				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------------");
				System.out.println("");
				customerMenu();

			}

			break;

		case "7": // Empty Shoppingcart
			logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0),
					" has emptied the  cart!", false));
			cart.empty();
			System.out.println("");
			System.out.println(
					"---------Cart-------------------------------------------------------------------------------------------------------------");
			cart.output();
			System.out.println("");
			System.out.println("");
			System.out.println("totalprice =" + cartGetTotalprice());
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------");
			System.out.println("");
			customerMenu();
			break;

		case "8": // Show Shoppingcart

			System.out.println("");
			System.out.println(
					"---------Cart-------------------------------------------------------------------------------------------------------------");

			cart.output();

			System.out.println("");
			System.out.println("");
			System.out.println("totalprice =" + cartGetTotalprice());
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------");
			System.out.println("");

			customerMenu();

			break;
		case "9": // Buy Shoppigcart
			String customerName = "Herr " + customerManagement.searchByNumber(currentCustomer).get(0).getLastname();
			String Date = new Date().toGMTString();
			String customerNr = "" + customerManagement.searchByNumber(currentCustomer).get(0).getCustomerNr();

			System.out.println(
					"---------Cart-------------------------------------------------------------------------------------------------------------");

			cart.output();
			System.out.println("");
			System.out.println("");
			System.out.println("totalprice =" + cartGetTotalprice());
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------");
			System.out.println("");

			String yesorno;
			System.out.println("Are you sure to buy theese Items for " + cartGetTotalprice() + "� ?");
			System.out.println("Enter 'Y' for Yes | 'N'+ for No ");
			System.out.println("");
			System.out.print(">>");
			yesorno = readEntry();

			while (!yesorno.equals("N") || !yesorno.equals("Y")) {
				System.out.println("Invalid entry, please try again");
				System.out.println("");
				System.out.print(">>");
				yesorno = readEntry();

				if (yesorno.equals("Y")) {
					logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0),
							"Items in Cart has been bought!", false));
					cart.buy();

					System.out.println("");

					System.out.println("Items in the shopping carthas been bought for " + cartGetTotalprice() + "�  "
							+ "\n" + "\n" + "Bill :");
					System.out.println("");
					System.out.println(
							"...................BILL............................................................................ ");

					System.out.println("Customer Name: " + customerName + "\n" + "Customer Nr: " + customerNr + "\n"
							+ "Date of Bill: " + Date);
					System.out.println("");

					System.out.print("Totalprice: " + cartGetTotalprice() + "\n" + billGetItems());
					System.out.println("");
					System.out.println("Thankyou for your oder!" + "\n" + "Kind regards, Eshop Team");

					System.out.println("");
					System.out.println(
							"............................................................................................... ");
					System.out.println("");
					System.out.println("Cart has been emptied");
					cart.empty();

					try {
						storage.writeItems(); // save/write the new item inventory
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					customerMenu();

				}

				if (yesorno.equals("N")) {
					System.out.println("");
					System.out.println("The items are still in cart if you change your mind");
					System.out.println("");
					customerMenu();

				}
			}

			if (yesorno.equals("Y")) {
				logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0),
						"Items has been bought!", false));
				cart.buy();

				System.out.println("");

				System.out.println("Items in the shopping carthas been bought for " + cartGetTotalprice() + "�  " + "\n"
						+ "\n" + "Bill :");
				System.out.println("");
				System.out.println(
						"...................BILL............................................................................ ");

				System.out.println("Customer Name: " + customerName + "\n" + "Customer Nr: " + customerNr + "\n"
						+ "Date of Bill: " + Date);
				System.out.println("");

				System.out.print("Totalprice: " + cartGetTotalprice() + "\n" + billGetItems());
				System.out.println("");
				System.out.println("Thankyou for your oder!" + "\n" + "Kind regards, Eshop Team");

				System.out.println("");
				System.out.println(
						"............................................................................................... ");
				System.out.println("");
				System.out.println("Cart has been emptied");
				cart.empty();

				try {
					storage.writeItems(); // save/write the new item inventory
					customerMenu();

				} catch (IOException e1) {
					e1.printStackTrace();
				}

				customerMenu();

			}

			if (yesorno.equals("N")) {
				System.out.println("");
				System.out.println("The items are still in cart");
				System.out.println("");
				customerMenu();
			}

			break;
		case "10": // Logout

			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = dateFormat.format(date);
			System.out.println("Date: " + strDate);
			logmanager.add(
					new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "has logged out", false));

			System.out.println("You are now logging out ...");

			System.out.println("Customer Nr: " + "| " + currentCustomer + " |");
			System.out.println(strDate);

			// speicher changelog
			try {
				logmanager.writeData("Log");
			} catch (IOException e) {

			}

			shopLogin();

			break;

		default:
			System.out.println("");
			System.out.println("Incorrect entry, please try again ...");
			try {
				logmanager.writeData("There was an incorrect entry in the customer menu");
			} catch (IOException e) {

				e.printStackTrace();
			}
			System.out.println("");
			customerMenu();
		}
	}

	public void shopLogin() {
		String input = "";
		System.out.println("");
		System.out.println("Are you a customer or an employee? Please enter 'C' for customer and 'E' for employee.. ");
		System.out.println("");
		System.out.print(">>");
		input = readEntry();
		switch (input) {
		case "C": // Customer
			shopLoginCustomer();
			break;
		case "E": // Employee
			shopLoginEmployee();
			break;
		default:
			System.out.println("Incorrect entry!");

			logmanager.add(new Changelog(system, "ncorrect entry in the start menu", false));
			System.out.println("Please try again");
			shopLogin();

		}
		getMenu();
	}

	/**
	 * Description: Login for Employees
	 */
	public void shopLoginEmployee() {
		String username = "";
		String password = "";

		boolean login = false;
		List<Employee> elist;
		elist = employeeManagement.getAllEmployees();

		System.out.println("");
		System.out.println("Please enter your username: ");
		System.out.println("");
		System.out.print(">>");
		username = readEntry();

		System.out.println("Please enter your password: ");
		System.out.println("");
		System.out.print(">>");
		password = readEntry();
		LoginEmployee a = new LoginEmployee();

		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String strDate = dateFormat.format(date);

		try {
			a.login(employeeManagement.getAllEmployees(), username, password);
			currentEmployee = a.getNumber();
			System.out.print(currentEmployee);
			System.out.println("Employee : " + username + " has logged in");
			System.out.println("Employee Nr: " + "| " + currentEmployee + " |");
			System.out.println(strDate);
			System.out.println("");
			employeeMenu();
			logmanager.add(new Changelog(employeeManagement.searchByNumber(a.getNumber()).get(0),
					username + "  has logged in ", true));

		} catch (IncorrectLoginDataException ex) {
			System.out.println("Username or Password is uncorrect!");
			logmanager.add(new Changelog(system, "Login failed!", true));
		}

	}

	/**
	 * Login for customer
	 */
	public void shopLoginCustomer() {
		String username = "";
		String password = "";
		boolean login = false;
		List<Customer> list;
		list = customerManagement.getAllCustomers();
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter your username: ");
		System.out.println("");
		System.out.print(">>");
		username = readEntry();

		System.out.println("Please enter your password: ");
		System.out.println("");
		System.out.print(">>");
		password = readEntry();

		LoginCustomer a = new LoginCustomer();
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String strDate = dateFormat.format(date);

		try {

			a.login(customerManagement.getAllCustomers(), username, password);
			currentCustomer = a.getNumber();
			System.out.println("Customer : " + username + " has logged in");
			System.out.println("Customer Nr: " + "| " + currentCustomer + " |");
			System.out.println(strDate);
			System.out.println("");
			customerMenu();
			logmanager.add(new Changelog(customerManagement.searchByNumber(a.getNumber()).get(0),
					username + " username has logged in.", false));

		} catch (IncorrectLoginDataException ex) {
			System.out.println("Username or Password is uncorrect!");
			logmanager.add(new Changelog(system, "Login failed!", false));
		}

	}

	/**
	 * Used by: CustomerMenu & getMenu Description: The CUI for customer
	 * registration is generated.
	 * 
	 * @param b indicates whether you are already logged in or whether the program
	 *          works before login
	 */

	public void shopCustomerRegistration(boolean b) {
		String username = "";
		String password = "";
		String firstname = "";
		String lastname = "";
		String adress = "";

		int customerNr;
		List<Customer> list;
		clist = customerManagement.getAllCustomers();

		System.out.println("");
		System.out.println("Please enter your username:");
		System.out.println("");
		System.out.print(">>");
		username = readEntry();

		System.out.println("");
		System.out.println("Please set a password:");
		System.out.println("");
		System.out.print(">>");
		password = readEntry();

		System.out.println("");
		System.out.println("Please enter your first name:");
		System.out.println("");
		System.out.print(">>");
		firstname = readEntry();

		System.out.println("");
		System.out.println("Please enter your last name: ");
		System.out.println("");
		System.out.print(">>");
		lastname = readEntry();

		System.out.println("");
		System.out.println("Please enter your Adress: ");
		System.out.println("");
		System.out.print(">>");
		adress = readEntry();

		System.out.println("");
		System.out.println("");
		customerNr = newNumberCustomer(customerManagement.getAllCustomers());
		System.out.println("You have been given the number : " + customerNr);
		System.out.println("");

		if (!username.isEmpty()) {
			customerManagement.addACustomer(username, password, firstname, lastname, adress, customerNr);
			try {
				customerManagement.writeCustomers();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		System.out.println("");
		System.out
				.println("New Customer with username -> " + username + " and nr-> " + customerNr + " has been created");
		System.out.println("");
		getCustomerlist(clist);
		if (!b) {
			shopLoginCustomer();
			logmanager.add(new Changelog(system,
					"the customer: " + username + " | " + customerNr + " has been created.", true));
		} else {
			logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
					"the customer: " + username + " | " + customerNr + " has been created.", true));
		}

	}

	/**
	 * Used by: EmployeeMenu & getMenu Description: The CUi for customer
	 * registration is generated.
	 * 
	 * @param b indicates whether the function is called from the employee menu or
	 *          not
	 */

	public void shopEmployeeRegistration() {
		String username = "";
		String password = "";
		String firstname = "";
		String lastname = "";
		String email = "";

		int employeeNr;
		List<Employee> list;
		list = employeeManagement.getAllEmployees();

		System.out.println("");
		System.out.println("Please enter a username: ");
		System.out.println("");
		System.out.print(">>");
		username = readEntry();

		System.out.println("");
		System.out.println("Please set a password: ");
		System.out.println("");
		System.out.print(">>");
		password = readEntry();

		System.out.println("");
		System.out.println("Please enter a first name: ");
		System.out.println("");
		System.out.print(">>");
		firstname = readEntry();

		System.out.println("");
		System.out.println("Please enter a last name: ");
		System.out.println("");
		System.out.print(">>");
		lastname = readEntry();

		System.out.println("");
		System.out.println("Please enter your email adress: ");
		System.out.println("");
		System.out.print(">>");
		email = readEntry();

		System.out.println("");
		System.out.println("");
		employeeNr = newNumberEmployee(employeeManagement.getAllEmployees());
		System.out.println("You have been given the number : " + employeeNr);
		System.out.println("");

		if (!username.isEmpty()) {
			employeeManagement.addAnEmployee(username, password, firstname, lastname, email, employeeNr);
			logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
					"Employee : " + username + " | " + employeeNr + " has been registered.", true));

		}
		try {
			employeeManagement.writeEmployees();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		System.out.println("");
		System.out.println(
				"New employee with username -> " + username + " and nr-> " + employeeNr + " has been created.");
		getEmployeelist(elist);
		System.out.println("");
		employeeMenu();

	}

	// main
	public static void main(String[] args) {

		CUI cui;

		cui = new CUI("It", "Emp", "Cus", "Log");

		cui.run();

	}

	// -------------------------------------------Number Assignment
	// Functions-----------------------------------------------------------------

	/**
	 * Description: Automatic number assignment for customer, looks all
	 * numbers,compare them and gives the next higher number
	 * 
	 * @param list
	 * @return
	 */
	public int newNumberCustomer(List<Customer> list) {
		// it compares all customers in the list with getCustomerNr
		Customer maxByNumber = list.stream().max(Comparator.comparing(Customer::getCustomerNr))
				.orElseThrow(NoSuchElementException::new);
		// the highest customer number
		int newNumber = maxByNumber.getCustomerNr() + 1;
		// returns the new number
		return newNumber;
	}

	/**
	 * Description: Automatic number assignment for employees looks all numbers,
	 * compare them and gives the next higher number
	 * 
	 * @param liste
	 * @return
	 */
	public int newNumberEmployee(List<Employee> list) {
		// it compares all customers in the list with getCustomerNr
		Employee maxByNumber = list.stream().max(Comparator.comparing(Employee::getEmployeeNr))
				.orElseThrow(NoSuchElementException::new);
		// the highest customer number
		int newNumber = maxByNumber.getEmployeeNr() + 1;
		// returns the new number
		return newNumber;
	}

	/**
	 * Description:
	 * 
	 * @param list
	 * @return Automatic number assignment for items, looks all numbers,compare them
	 *         and gives the next higher number
	 */
	public int newNumberItem(List<Item> list) {
		// outputs the highest item number, i
		Item maxByNumber = list.stream().max(Comparator.comparing(Item::getNumber))
				.orElseThrow(NoSuchElementException::new);
		// the highest customer number
		int newNumber = maxByNumber.getNumber() + 1;
		// returns the new number
		return newNumber;

	}

	// --------------------------------------------------Check
	// Functions---------------------------------------------------------

	/**
	 * @return true: Item for the number has been found. false: no item has been
	 *         found and Exception is thrown
	 * @throws InvalidArtikelNummerException
	 */
	private boolean checkNumber(int iNum) throws InvalidItemNumberException {
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
	private boolean checkName(String iName) throws InvalidItemNameException {
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
	private boolean checkNameCus(String cName) throws InvalidCustomerNameException {
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
	private boolean checkNameEmp(String eName) throws InvalidEmployeeNameException {
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
	private boolean checkNumberStock(int iNum, int iSto) throws InvalidItemNumberException {
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
	private boolean checkNumberEmployee(int eNum) throws InvalidEmployeeNumberException {
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
	private boolean checkNumberCustomer(int cNum) throws InvalidCustomerNumberException {
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
	private boolean checkNameItem(String iName) throws InvalidItemNameException {
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
	 * @return true: Item number and stock match. false: exception is thrown
	 * @throws InvalidCartbException
	 */
	private boolean checkCart(int iNum, int iAmo) throws InvalidCartException {
		boolean x = false;
		for (Item i : storage.getAllItems()) {
			// System.out.println(i.getNumber() == iAmo && iAmo <= i.getStock());
			// System.out.println(!(i.getNumber() == iNum));
			// System.out.println(i.getStock() <= iAmo);
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
	private boolean checkCartChange(int tiNum, int tiAmo) throws InvalidCartException {
		boolean x = false;
		for (cartItem ti : cart.getCart()) {
			if (ti.getItem().getNumber() == tiNum) {
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
	private boolean checkCartDelete(int tiNum, int tiAmo) throws InvalidCartException {
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
	private boolean checkCartSearch(String aName) throws InvalidCartNameException {
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

	// ---------------------------------------------------Sort
	// Functions-------------------------------------------------------------------

	/**
	 * Description: Sorts item by name and outputs them in the console.
	 * 
	 * @param list
	 */
	private static void sortNameItemList(List<Item> list) {
		if (list.isEmpty()) {
			System.out.println("Liste is empty.");
		} else {

			Collections.sort(list, new Comparator<Item>() {
				@Override
				public int compare(Item u1, Item u2) {
					return u1.getName().compareTo(u2.getName());
				}
			});

		}
		for (Item i : list) {
			System.out.println(i);
		}
	}

	/**
	 * Description: sorts items by Nr and outputs them in the Console
	 * 
	 * @param list
	 */
	private static void sortNumberItemList(List<Item> list) {
		if (list.isEmpty()) {
			System.out.println("List is empty.");
		} else {

			Collections.sort(list, new Comparator<Item>() {
				@Override
				public int compare(Item u1, Item u2) {

					int x = Integer.compare(u1.getNumber(), u2.getNumber());

					return x;
				}
			});

		}
		for (Item i : list) {
			System.out.println(i);
		}
	}

	/**
	 * Description: sorts changelogs by date and returns them
	 * 
	 * @param liste
	 * @return
	 */
	private List<Changelog> sortDateChangelogliste(List<Changelog> liste) {
		if (liste.isEmpty()) {
			System.out.println("List is empty .");
		} else {

			Collections.sort(liste, new Comparator<Changelog>() {
				@Override
				public int compare(Changelog u1, Changelog u2) {
					return u1.getTime().compareTo(u2.getTime());
				}
			});

		}
		return liste;

	}

	// ----------------------------------------------------Get
	// Functions-------------------------------------------------------------------

	/**
	 * Description: Outputs the list of Employees in the console
	 * 
	 * @param elist
	 */
	private static void getEmployeelist(List<Employee> elist) {

		elist = employeeManagement.getAllEmployees();
		if (elist.isEmpty()) {
			System.out.println("List is empty.");
		} else {
			for (Employee e : elist) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Description: Outputs the list of Customer in the console
	 * 
	 * @param clist
	 */
	private static void getCustomerlist(List<Customer> clist) {

		clist = customerManagement.getAllCustomers();
		if (clist.isEmpty()) {
			System.out.println("List is empty.");
		} else {
			for (Customer c : clist) {
				System.out.println(c);
			}
		}
	}

	/**
	 * Description: Outputs the list of Items in the console
	 * 
	 * @param ilist
	 */
	private static void getItemlist(List<Item> ilist) {

		ilist = storage.getAllItems();
		if (ilist.isEmpty()) {
			System.out.println("List is empty.");
		} else {
			for (Item i : ilist) {
				System.out.println(i);
			}
		}

	}

	/**
	 * Description: Outputs the list of changelogs in the console
	 * 
	 * @param chlist
	 */
	private static void getClog(List<Changelog> chlist) {
		chlist = logmanager.getChangelog();
		if (chlist.isEmpty()) {
			System.out.println("List is empty");

		} else {
			for (Changelog i : chlist) {
				System.out.println(i);
			}
		}
	}

	/**
	 * Description: calculates the total price of items in Cart and returns it
	 * 
	 * @return dummy
	 */
	private double cartGetTotalprice() {
		double totalprice;
		double dummy = 0;

		List<cartItem> cartList = cart.getCart();
		for (int i = 0; i < cartList.size(); i++) {

			totalprice = cartList.get(i).getItem().getPrice() * cartList.get(i).getAmount();
			dummy = totalprice + dummy;
		}

		return dummy;
	}

	/**
	 * Description: outputs purchased items with their prices and quantities in the
	 * console for bill
	 * 
	 */
	private boolean billGetItems() {

		List<cartItem> cartList = cart.getCart();
		for (int i = 0; i < cartList.size(); i++) {
			String names = cartList.get(i).getItem().getName();
			int nrs = cartList.get(i).getItem().getNumber();
			String numbers = String.valueOf(nrs);

			int amts = cartList.get(i).getAmount();
			String amounts = String.valueOf(amts);

			double prcs = cartList.get(i).getItem().getPrice() * cartList.get(i).getAmount();
			String prices = String.valueOf(prcs);
			List<String> list = new ArrayList();
			list.add(" Itemname:  " + names + " ");
			list.add(" Nr:  " + numbers + " ");
			list.add(" Amount:  " + "x" + amounts + " ");
			list.add(" Price:  " + prices + "E ");
			System.out.println(list);

		}
		return true;

	}

	/**
	 * Description: Logmanager reads Changelog from Log file
	 */
	private static void getLog() {

		try {
			logmanager.readData("Log");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (log.isEmpty()) {
			System.out.println("List is empty.");
		} else {
			for (String l : log) {
				System.out.println(l);
			}
		}
	}

	/**
	 * Description: returns all changelogs
	 * 
	 * @param l
	 */
	private void getChangelogtable(List<Changelog> l) {

		for (int i = 0; i < l.size(); i++) {

			List<String> list = new ArrayList();

			if (l.get(i).getTyp()) {

				String enumbers = String.valueOf(l.get(i).getEmployee().getEmployeeNr());
				String efirstnames = l.get(i).getEmployee().getFirstname();
				String elastnames = l.get(i).getEmployee().getLastname();
				String times = l.get(i).getTime();
				String messages = l.get(i).getMessage();

				List<String> listEmp = new ArrayList();
				listEmp.add(" Nr:  " + enumbers + " ");
				listEmp.add(" Firstname:  " + efirstnames + " ");
				listEmp.add(" Lastname:  " + elastnames + " ");
				listEmp.add(" Message:  " + messages + "E ");
				listEmp.add(" Time:  " + times + "E ");
				System.out.println(listEmp);

			} else {
				String times = l.get(i).getTime();
				String messages = l.get(i).getMessage();

				String cnumbers = String.valueOf(l.get(i).getCustomer().getCustomerNr());
				String cfirstnames = String.valueOf(l.get(i).getCustomer().getFirstname());
				String clastnames = l.get(i).getCustomer().getLastname();

				List<String> listCus = new ArrayList();
				listCus.add(" Nr:  " + cnumbers + " ");
				listCus.add(" Firstname:  " + cfirstnames + " ");
				listCus.add(" Lastname:  " + clastnames + " ");
				listCus.add(" Message:  " + messages + "E ");
				listCus.add(" Time:  " + times + "E ");
				System.out.println(listCus);

			}

		}

	}

}