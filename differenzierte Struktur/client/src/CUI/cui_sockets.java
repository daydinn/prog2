package CUI;

import valueObjects.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Exceptions.InvalidCartException;
import Exceptions.InvalidCustomerNameException;
import Exceptions.InvalidCustomerNumberException;
import Exceptions.InvalidEmployeeNameException;
import Exceptions.InvalidEmployeeNumberException;
import Exceptions.InvalidItemNameException;
import Exceptions.InvalidItemNumberException;
import net.Client;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class cui_sockets {

	private Client client;
	private Scanner in;
	private Cart cart;

	public cui_sockets() throws IOException {
		client = new Client(4999);
		in = new Scanner(System.in);
		cart = new Cart(null);
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		cui_sockets cui = new cui_sockets();
		cui.run();
	}

	public void run() throws ClassNotFoundException, IOException {
		String input = "";

		do {
			displayMenu();
		} while (input != "q");
	}

	public void displayMenu() throws ClassNotFoundException, IOException {
		/*
		 * if i have time. if not, i can't waste valuable time on this
		 * System.out.println("");
		 * System.out.println("Please log in or register ......");
		 * System.out.println("To login, press  'A'");
		 * System.out.println("To register, press  'R'"); System.out.println("");
		 * System.out.print(">>");
		 */

		String input = "";

		System.out.println("");
		System.out.println("Please select whether you are Customer or Employee......");
		System.out.println("For Customer, press  'C'");
		System.out.println("For Employee, press  'E'");
		System.out.println("");
		System.out.print(">>");

		input = in.nextLine();

		switch (input) {
		case "c":
			//
		case "C":
			displayMenuC();
			break;
		case "e":
			//
		case "E":
			displayMenuE();
			break;

		default:
			System.out.println("Unrecognized input, please try again.");
		}
	}

	private void displayMenuC() throws ClassNotFoundException, IOException {
		String input = "";

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

		input = in.nextLine();

		switch (input) {
		case "1":
			for (Item i : client.getItemStock()) {
				System.out.println(i.toString());
			}
			break;
		case "2":
			System.out.println("Search by number or by name?");
			System.out.println("By number: type 'nu'");
			System.out.println("By name: type 'na'");
			System.out.print(">>");

			switch (in.nextLine()) {
			case "nu":
				System.out.println("Please input the item number you are looking for:");
				System.out.print(">>");
				System.out.println(client.searchItemNr(in.nextInt()).toString());
				break;
			case "na":
				System.out.println("Please input the item name you are looking for:");
				System.out.print(">>");
				for (Item i : client.searchItemName(in.nextLine())) {
					System.out.println(i.toString());
				}
				break;
			default:
				System.out.println("ERROR: Unrecognized input.");
			}
			break;
		case "3": // Sort items by name
			System.out.println("");
			System.out.println("Items are sorted by name...");
			System.out.println("");

			sortNameItemList(client.getItemStock());
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			System.out.println("");
			displayMenuC();
			break;

		case "4": // Sort Items by Number
			System.out.println("");
			System.out.println("Items are sorted by number...");
			System.out.println("");

			sortNumberItemList(client.getItemStock());
			System.out.println("");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			displayMenuC();
			break;

		case "5": // Add an item to Shopping Cart
			System.out.println("");
			System.out.println("Please enter the item number of the item: ");
			System.out.println("");
			System.out.print(">>");
			int itemNumber = in.nextInt();

			System.out.println("");
			System.out.println("Please enter the amount of items: ");
			System.out.println("");
			System.out.print(">>");
			int itemAmount = in.nextInt();

			try {

				for (cartItem ti : cart.getCart()) {
					if (ti.getItem().getNumber() == itemNumber) {
						ti.setAmount(ti.getAmount() + itemAmount);
						System.out.println(
								"---------Cart-------------------------------------------------------------------------------------------------------------");
						cart.output();
						System.out.println("");
						System.out.println("");
						System.out.println("totalprice =" + cartGetTotalprice());
						System.out.println(
								"--------------------------------------------------------------------------------------------------------------------------");
						System.out.println("");
						displayMenuC();
					}
				}
			} catch (Exception ex) {
				System.out.println("");
				System.out.println("Error: invalid entries");
				System.out.println("");
				// client.add(new
				// Changelog(customerManagement.searchByNumber(currentCustomer).get(0),
				// "Incorrect entry while adding to shopping cart", false));
				System.out.println("");
				displayMenuC();
			}

			try {
				if (checkCart(itemNumber, itemAmount)) {
					cart.addItem(itemNumber, itemAmount);

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

					// client.add(new
					// Changelog(customerManagement.searchByNumber(currentCustomer).get(0),"The
					// Item: " + itemNumber + " has been added " + itemAmount + "times to the Cart",
					// false));
					System.out.println("");
					displayMenuC();

				}
			} catch (InvalidCartException ex) {
				System.out.println("");
				System.out.println("Error");
				System.out.println("Uncorrect Entry!");
				// logmanager.add(new
				// Changelog(customerManagement.searchByNumber(currentCustomer).get(0),
				// "Incorrect entry while adding to shopping cart", false));
				System.out.println(ex.getMessage());
				System.out.println("");
				cart.output();
				System.out.println("");
				displayMenuC();

			}
			break;

		case "6": // change the amount of items in Cart

			System.out.println("");
			System.out.println("Please enter the item number of the item: ");
			System.out.println("");
			System.out.print(">>");
			itemNumber = in.nextInt();

			System.out.println("");
			System.out.println("Please enter the amount of Items ");
			System.out.println("");
			System.out.print(">>");
			itemAmount = in.nextInt();

			cart.output();

			try {

				if (checkCartChange(itemNumber, itemAmount)) {
					cart.changeStockofItem(itemNumber, itemAmount);
					System.out.println("");
					// logmanager.add(new
					// Changelog(customerManagement.searchByNumber(currentCustomer).get(0),"The
					// amount of Item:" + itemNumber + " has been tryed change Item Amount to " +
					// itemAmount, true));
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
					displayMenuC();
				}
			} catch (InvalidCartException ex) {
				System.out.println(ex.getMessage());

				// logmanager.add(new
				// Changelog(customerManagement.searchByNumber(currentCustomer).get(0),
				// "Uncorrect entry while deleting an Item", true));

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
				displayMenuC();

			}

			break;

		case "7": // Empty Shoppingcart
			// logmanager.add(new
			// Changelog(customerManagement.searchByNumber(currentCustomer).get(0), " has
			// emptied the cart!", false));
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
			displayMenuC();
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

			displayMenuC();

			break;
		case "9": // Buy Shoppingcart
			String customerName = "Herr " + "Tester";// customerManagement.searchByNumber(currentCustomer).get(0).getLastname();
			String Date = new Date().toGMTString();
			String customerNr = "" + "123";// customerManagement.searchByNumber(currentCustomer).get(0).getCustomerNr();

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
			System.out.println("Are you sure you want to buy these Items for " + cartGetTotalprice() + "\u20AC?");
			System.out.println("Enter 'Y' for Yes | 'N' for No ");
			System.out.println("");
			System.out.print(">>");
			yesorno = in.nextLine();

			while (!yesorno.equals("N") || !yesorno.equals("Y")) {
				System.out.println("Invalid entry, please try again");
				System.out.println("");
				System.out.print(">>");
				yesorno = in.nextLine();

				if (yesorno.equals("Y")) {
					// logmanager.add(new
					// Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Items
					// in Cart has been bought!", false));
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
					/*
					 * try { storage.writeItems(); // save/write the new item inventory } catch
					 * (IOException e1) { e1.printStackTrace(); }
					 */
					displayMenuC();

				}

				if (yesorno.equals("N")) {
					System.out.println("");
					System.out.println("The items are still in cart if you change your mind");
					System.out.println("");
					displayMenuC();

				}
			}

			if (yesorno.equals("Y")) {
				// logmanager.add(new
				// Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Items
				// has been bought!", false));
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
					// storage.writeItems(); // save/write the new item inventory
					displayMenuC();

				} catch (IOException e1) {
					e1.printStackTrace();
				}

				displayMenuC();

			}

			if (yesorno.equals("N")) {
				System.out.println("");
				System.out.println("The items are still in cart");
				System.out.println("");
				displayMenuC();
			}

			break;
		case "10": // Logout

			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = dateFormat.format(date);
			System.out.println("Date: " + strDate);
			// logmanager.add(new
			// Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "has
			// logged out", false));

			System.out.println("You are now logging out ...");

			System.out.println("Customer Nr: " + "| " + "123"/* currentCustomer */ + " |");
			System.out.println(strDate);

			// speicher changelog
			/*
			 * try { logmanager.writeData("Log"); } catch (IOException e) {
			 * 
			 * }
			 */

			// shopLogin();

			System.exit(0);

			break;

		default:
			System.out.println("");
			System.out.println("Incorrect entry, please try again ...");
			/*
			 * try {
			 * logmanager.writeData("There was an incorrect entry in the customer menu"); }
			 * catch (IOException e) {
			 * 
			 * e.printStackTrace(); }
			 */
			System.out.println("");
			displayMenuC();
		}
	}

	private void displayMenuE() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		String input = "";

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

		input = in.nextLine();
		switch (input) {
		case "1": // output all items
			System.out.println("");
			for (Item i : client.getItemStock()) {
				System.out.println(i.toString());
			}
			System.out.println("");

			displayMenuE();
			break;

		case "2": {// add an Item

			System.out.println("");
			System.out.println("Please enter the item name: ");
			System.out.println("");
			System.out.print(">>");
			String itemName = in.nextLine();

			System.out.println("");
			System.out.println("");
			int iNum = newNumberItem(client.getItemStock());
			System.out.println("New item gets the number : " + iNum);
			System.out.println("");

			System.out.println("");
			System.out.println("Please enter the price of the item : ");
			System.out.println("");
			System.out.print(">>");
			double itemPrice = in.nextDouble();

			System.out.println("");
			System.out.println("Please enter the item stock: ");
			System.out.println("");
			System.out.print(">>");
			int itemStock = in.nextInt();

			System.out.println("");
			System.out.println("Please enter the minimum stock of item : ");
			System.out.println("");
			System.out.print(">>");
			int itemMinimumStock = in.nextInt();

			System.out.println("");
			System.out.println("Please enter the Bulk : ");
			System.out.println("");
			System.out.print(">>");
			int itemBulk = in.nextInt();

			System.out.println("");

			if (!itemName.isEmpty() && iNum != 0) { // if all fields are filled correctly
				client.add(new Item(itemName, iNum, itemPrice, itemStock, itemMinimumStock, itemBulk)); // add new item
																										// to the
																										// Storage
				// logmanager.add(new
				// Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "The
				// Item: " + itemName + "with Nr " + iNum + " has been succesfully added.",
				// true));
				// throw the exception
			}

			else {
				// logmanager.add(new
				// Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "You can
				// not add the Item: " + itemName + "with Nr " + iNum + " ", true));
			}
			/*
			 * try { storage.writeItems(); // saves the items in the TXT file
			 * logmanager.writeData("log"); } catch (IOException e1) { e1.printStackTrace();
			 * }
			 */
			break;
		}

		case "3": { // delete Items
			System.out.println("");
			System.out.println("Please enter the item number of the item to be deleted: ");
			System.out.println("");
			System.out.print(">>");
			int itemNumber = in.nextInt();

			try { // try and catch for the correctness of the item number
				if (checkNumber(itemNumber)) { // if yes delete item
					client.ideleting(itemNumber); // deletes the item from the storage
					// logmanager.add(new
					// Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "The
					// Item with number : " + itemNumber + " has been deleted!", true));
					/*
					 * try { storage.writeItems(); // saves the items in the TXT file } catch
					 * (IOException e1) { e1.printStackTrace(); }
					 */
				}
			} catch (InvalidItemNumberException ex) { // if not, catch the exception and give an error message
				System.out.println("");
				// logmanager.add(new
				// Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
				// ex.getMessage(), true)); // schriebt den Fehler im Changelog and outputs
				// error message
				System.out.println("Please enter a valid number"); // error message if the item number is wrong in the
																	// CUI
				displayMenuE();

			}
			break;
		}

		case "4": // search for an Item
			System.out.println("");
			System.out.println("Itemname:    ");
			System.out.print(">>");
			String itemName = in.nextLine();
			try {
				if (checkNameItem(itemName)) {
					List<Item> searchlist = client.searchItemName(itemName);
					System.out.println("");
					System.out.println("Results ->");
					System.out.println(searchlist);
					displayMenuE();
				}
			} catch (InvalidItemNameException ex) {
				System.out.println("");
				System.out.println(ex.getMessage());
				System.out.println("Please enter a valid item name!");

				// logmanager.add(new
				// Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "There
				// was an error while searching for an item", false));
				displayMenuE();
			}

			break;

		case "5": // Sort items by name
			System.out.println("");
			sortNameItemList(client.getItemStock());
			System.out.println("");

			displayMenuE();
			break;

		case "6": // Sort items by number
			System.out.println("");

			sortNumberItemList(client.getItemStock());
			System.out.println("");

			displayMenuE();
			break;

		case "7": {// Change the amount of items

			getItemlist(client.getItemStock());
			System.out.println("");
			System.out.println("Please enter the number of item: ");
			System.out.println("");
			System.out.print(">>");
			int itemNumber = in.nextInt();

			System.out.println("");
			System.out.println("Please enter the item stock: ");
			System.out.println("");
			System.out.print(">>");
			int itemStock = in.nextInt();

			try { // try to change the item Stocks
				if (checkNumberStock(itemNumber, itemStock)) { // if the entry was correct ..

					getItemlist(client.getItemStock());
					System.out.println("");
					System.out.println("Stock changed!"); // report the successful change
					/*
					 * logmanager.add(new
					 * Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
					 * "The Item: " + iNum + " hast been incrased by : " + iSto, true)); try {
					 * storage.writeItems(); // save/write the new item inventory } catch
					 * (IOException e1) { e1.printStackTrace(); }
					 */
				}
			} catch (InvalidItemNumberException ex) { // if the entry was not correct ..
				// System.out.println("Incorrect item number!"); //errormessage
				System.out.println(ex.getMessage()); // output the error message in the console
				// logmanager.add(new
				// Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
				// ex.getMessage() + " while increasing the stock", true));
			}
			displayMenuE();

			break;
		}

		case "8": // create new employee
			System.out.println("");
			shopEmployeeRegistration();
			System.out.println("");
			/*try {
				customerManagement.writeCustomers();
			} catch (IOException e) {

				e.printStackTrace();
			}*/
			displayMenuE();
			break;
		case "9": // show all employees
			System.out.println("");
			getEmployeelist(client.getEmployeeStock());
			System.out.println("");
			displayMenuE();
			break;
		case "10": {// Delete employee
			System.out.println("");
			System.out.println("Please enter the employee number of the employee to be deleted: ");
			System.out.println("");
			System.out.print(">>");
			int eNumber = in.nextInt();
			client.edeleting(eNumber);
			System.out.println("");
			System.out.println("The employee has been deleted.");
			/*
			try {
				if (checkNumberEmployee(eNumber)) {
					employeeManagement.deleteEmployee(eNumber);

					System.out.println(employeeManagement.getAllEmployees());
					logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
							"The employee with the number: " + eNumber + " has been deleted.", true));
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

			}*/

			System.out.println("");
			displayMenuE();
			break;
		}

		case "11": // Search an employee by Nr
			System.out.println("");
			System.out.println("EmployeeNr:    ");
			int eNumber = in.nextInt();

			try {
				if (checkNumberEmployee(eNumber)) {
					System.out.println("");
					Employee e = client.searchEmployeeNr(eNumber);
					System.out.println(e);
					System.out.println("");
					displayMenuE();
					System.out.println("");
				}
			} catch (InvalidEmployeeNumberException ex) {
				System.out.println("Invalid Nr!");
				System.out.println(ex.getMessage());
				System.out.println("");
				//logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "Search Error! " + ex.getMessage(), true));
				displayMenuE();
				System.out.println("");
			}
			System.out.println("");
			displayMenuE();
			break;

		case "12": // Search an employee by Name
			System.out.println("");
			System.out.println("Employeename:    ");
			String eName = in.nextLine();

			try {
				if (checkNameEmp(eName)) {
					System.out.println("");
					List<Employee> elist = client.searchEmployeeName(eName);
					System.out.println(elist);
					System.out.println("");
					displayMenuE();
					System.out.println("");

				}
			} catch (InvalidEmployeeNameException ex) {
				System.out.println("Invalid Name!");
				System.out.println("");
				System.out.println(ex.getMessage());
				//logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),"Search Error! " + ex.getMessage(), true));
			}

			System.out.println("");
			displayMenuE();
			break;

		case "13": // create new customer
			System.out.println("");
			shopCustomerRegistration(true);
			System.out.println("");
			/*try {
				customerManagement.writeCustomers();
			} catch (IOException e) {

				e.printStackTrace();
			}*/
			displayMenuE();
			break;
		case "14": {// Delete customers
			System.out.println("");
			System.out.println("Please enter the customer number of the customer to be deleted: ");
			System.out.println("");
			System.out.print(">>");
			int cNumber = in.nextInt();
			client.cdeleting(cNumber);
			System.out.println("");
			System.out.println("The customer has been deleted successfully.");
			/*try {
				if (checkNumberCustomer(cNumber)) {
					client.deleteCustomer(cNumber);
					logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
							"The Customer with Number: " + cNumber + " has been deleted", true));
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
			}*/
			System.out.println("");
			displayMenuE();

			break;
		}
		case "15": // Show all customers
			System.out.println("");
			getCustomerlist(client.getCustomerStock());
			System.out.println("");
			displayMenuE();
			break;
		case "16": // search customers by nr

			System.out.println("");
			System.out.println("CustomerNr:    ");
			int cNumber = in.nextInt();

			try {
				if (checkNumberCustomer(cNumber)) {
					System.out.println("");
					Customer c= client.searchCustomerNr(cNumber);
					System.out.println(c);
					System.out.println("");
					displayMenuE();
					System.out.println("");
				}
			} catch (InvalidCustomerNumberException ex) {
				System.out.println("Invalid Nr!");
				System.out.println(ex.getMessage());
				System.out.println("");
				//logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Search Error! " + ex.getMessage(), true));
				displayMenuE();
				System.out.println("");
			}
			System.out.println("");
			displayMenuE();
			break;

		case "17": // search customers by name
			System.out.println("");
			System.out.println("Customername:    ");
			String cName = in.nextLine();

			try {
				if (checkNameCus(cName)) {
					System.out.println("");
					List<Customer> clist = client.searchCustomerName(cName);
					System.out.println(clist);
					System.out.println("");
					displayMenuE();
					System.out.println("");

				}
			} catch (InvalidCustomerNameException ex) {
				System.out.println("Invalid Name!");
				System.out.println("");
				System.out.println(ex.getMessage());
				//logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Search Error! " + ex.getMessage(), true));
			}

			System.out.println("");
			displayMenuE();
			break;

		case "18": // logout
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = dateFormat.format(date);
			System.out.println("Date: " + strDate);
			/*logmanager.add(
					new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "has logged out", true));
			getLog();
			System.out.println("You are now logging out ...");
			shopLogin();

			try {
				logmanager.writeData("Log");
			} catch (IOException e) {

				e.printStackTrace();
			}*/

			break;
		case "19": // output changelog
			System.out.println("");
			//getLog();
			System.out.println("Sorry, changelogs are currently out of order as the system undergoes maintenance");
			System.out.println("");

			displayMenuE();
			break;
		case "20": // sort by date
			/*try {
				logmanager.readData("Log");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (log.isEmpty()) {
				System.out.println("List is empty.");
			}
			sortDateChangelogliste(logmanager.getChangelog());
			 */
			System.out.println("");
			System.out.println("Sorry, changelogs are currently out of order as the system undergoes maintenance");
			System.out.println("");
			displayMenuE();
			break;

		default:
			System.out.println("Incorrect entry!");

			//logmanager.add(new Changelog(system, "ncorrect entry", true));
			System.out.println("Please try again");
			displayMenu();

		}
		displayMenu();
	}

	private void sortNumberItemList(List<Item> list) {
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
			System.out.println(i.toString());
		}
	}

	private void sortNameItemList(List<Item> list) {
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

	private double cartGetTotalprice() {
		double totalprice;
		double retPrice = 0;

		List<cartItem> cartList = cart.getCart();
		for (int i = 0; i < cartList.size(); i++) {

			totalprice = cartList.get(i).getItem().getPrice() * cartList.get(i).getAmount();
			retPrice = totalprice + retPrice;
		}

		return retPrice;
	}

	private boolean checkCart(int itemNumber, int itemAmount)
			throws InvalidCartException, ClassNotFoundException, IOException {
		boolean x = false;
		for (Item i : client.getItemStock()) {
			// System.out.println(i.getNumber() == itemAmount && itemAmount <=
			// i.getStock());
			// System.out.println(!(i.getNumber() == itemNumber));
			// System.out.println(i.getStock() <= itemAmount);
			if (i.getNumber() == itemNumber && itemAmount <= i.getStock() && itemAmount >= i.getBulk()) {
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
			List<String> list = new ArrayList<String>();
			list.add(" Itemname:  " + names + " ");
			list.add(" Nr:  " + numbers + " ");
			list.add(" Amount:  " + "x" + amounts + " ");
			list.add(" Price:  " + prices + "E ");
			System.out.println(list);

		}
		return true;

	}

	public int newNumberItem(List<Item> list) {
		// outputs the highest item number, i
		Item maxByNumber = list.stream().max(Comparator.comparing(Item::getNumber))
				.orElseThrow(NoSuchElementException::new);
		// the highest customer number
		int newNumber = maxByNumber.getNumber() + 1;
		// returns the new number
		return newNumber;

	}

	private boolean checkNumber(int iNum) throws InvalidItemNumberException, ClassNotFoundException, IOException {
		boolean x = false;
		for (Item i : client.getItemStock()) { // goes through the item list
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

	private boolean checkNameItem(String iName) throws InvalidItemNameException, ClassNotFoundException, IOException {
		boolean x = false;
		for (Item i : client.getItemStock()) {
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

	private void getItemlist(List<Item> ilist) throws ClassNotFoundException, IOException {

		ilist = client.getItemStock();
		if (ilist.isEmpty()) {
			System.out.println("List is empty.");
		} else {
			for (Item i : ilist) {
				System.out.println(i);
			}
		}

	}

	private boolean checkNumberStock(int iNum, int iSto)
			throws InvalidItemNumberException, ClassNotFoundException, IOException {
		boolean x = false;
		for (Item i : client.getItemStock()) {
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

	public void shopEmployeeRegistration() throws ClassNotFoundException, IOException {
		String username = "";
		String password = "";
		String firstname = "";
		String lastname = "";
		String email = "";

		int employeeNr;
		List<Employee> list;
		list = client.getEmployeeStock();

		System.out.println("");
		System.out.println("Please enter a username: ");
		System.out.println("");
		System.out.print(">>");
		username = in.nextLine();

		System.out.println("");
		System.out.println("Please set a password: ");
		System.out.println("");
		System.out.print(">>");
		password = in.nextLine();

		System.out.println("");
		System.out.println("Please enter a first name: ");
		System.out.println("");
		System.out.print(">>");
		firstname = in.nextLine();

		System.out.println("");
		System.out.println("Please enter a last name: ");
		System.out.println("");
		System.out.print(">>");
		lastname = in.nextLine();

		System.out.println("");
		System.out.println("Please enter your email adress: ");
		System.out.println("");
		System.out.print(">>");
		email = in.nextLine();

		System.out.println("");
		System.out.println("");
		employeeNr = newNumberEmployee(client.getEmployeeStock());
		System.out.println("You have been given the number : " + employeeNr);
		System.out.println("");

		if (!username.isEmpty()) {
			client.add(new Employee(username, password, firstname, lastname, email, employeeNr));
			//logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "Employee : " + username + " | " + employeeNr + " has been registered.", true));

		}
		/*try {
			employeeManagement.writeEmployees();
		} catch (IOException e1) {

			e1.printStackTrace();
		}*/

		System.out.println("");
		System.out.println(
				"New employee with username -> " + username + " and nr-> " + employeeNr + " has been created.");
		getEmployeelist(client.getEmployeeStock());
		System.out.println("");
		displayMenuE();

	}

	public int newNumberEmployee(List<Employee> list) {
		// it compares all customers in the list with getCustomerNr
		Employee maxByNumber = list.stream().max(Comparator.comparing(Employee::getEmployeeNr))
				.orElseThrow(NoSuchElementException::new);
		// the highest customer number
		int newNumber = maxByNumber.getEmployeeNr() + 1;
		// returns the new number
		return newNumber;
	}
	
	private void getEmployeelist(List<Employee> elist) {

		if (elist.isEmpty()) {
			System.out.println("List is empty.");
		} else {
			for (Employee e : elist) {
				System.out.println(e);
			}
		}
	}
	
	private boolean checkNumberEmployee(int eNumber) throws InvalidEmployeeNumberException, ClassNotFoundException, IOException {
		boolean x = false;
		for (Employee e : client.getEmployeeStock()) {
			if (e.getEmployeeNr() == eNumber) {
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
	
	private boolean checkNameEmp(String eName) throws InvalidEmployeeNameException, ClassNotFoundException, IOException {
		boolean x = false;
		for (Employee e : client.getEmployeeStock()) {
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
	
	public void shopCustomerRegistration(boolean b) throws ClassNotFoundException, IOException {
		String username = "";
		String password = "";
		String firstname = "";
		String lastname = "";
		String adress = "";

		int customerNr;
		List<Customer> list;
		list = client.getCustomerStock();

		System.out.println("");
		System.out.println("Please enter your username:");
		System.out.println("");
		System.out.print(">>");
		username = in.nextLine();

		System.out.println("");
		System.out.println("Please set a password:");
		System.out.println("");
		System.out.print(">>");
		password = in.nextLine();

		System.out.println("");
		System.out.println("Please enter your first name:");
		System.out.println("");
		System.out.print(">>");
		firstname = in.nextLine();

		System.out.println("");
		System.out.println("Please enter your last name: ");
		System.out.println("");
		System.out.print(">>");
		lastname = in.nextLine();

		System.out.println("");
		System.out.println("Please enter your Adress: ");
		System.out.println("");
		System.out.print(">>");
		adress = in.nextLine();

		System.out.println("");
		System.out.println("");
		customerNr = newNumberCustomer(list);
		System.out.println("You have been given the number : " + customerNr);
		System.out.println("");

		if (!username.isEmpty()) {
			client.add(new Customer(username, password, firstname, lastname, adress, customerNr));
			/*try {
				customerManagement.writeCustomers();
			} catch (IOException e1) {
				e1.printStackTrace();
			}*/

		}
		System.out.println("");
		System.out
				.println("New Customer with username -> " + username + " and nr-> " + customerNr + " has been created");
		System.out.println("");
		getCustomerlist(list);
		/*if (!b) {
			shopLoginCustomer();
			logmanager.add(new Changelog(system,
					"the customer: " + username + " | " + customerNr + " has been created.", true));
		} else {
			logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0),
					"the customer: " + username + " | " + customerNr + " has been created.", true));
		}*/

	}
	
	public int newNumberCustomer(List<Customer> list) {
		// it compares all customers in the list with getCustomerNr
		Customer maxByNumber = list.stream().max(Comparator.comparing(Customer::getCustomerNr))
				.orElseThrow(NoSuchElementException::new);
		// the highest customer number
		int newNumber = maxByNumber.getCustomerNr() + 1;
		// returns the new number
		return newNumber;
	}
	
	private void getCustomerlist(List<Customer> clist) throws ClassNotFoundException, IOException {

		clist = client.getCustomerStock();
		if (clist.isEmpty()) {
			System.out.println("List is empty.");
		} else {
			for (Customer c : clist) {
				System.out.println(c);
			}
		}
	}
	
	private boolean checkNumberCustomer(int cNum) throws InvalidCustomerNumberException, ClassNotFoundException, IOException {
		boolean x = false;
		for (Customer c : client.getCustomerStock()) {
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
	
	private boolean checkNameCus(String cName) throws InvalidCustomerNameException, ClassNotFoundException, IOException {
		boolean x = false;
		for (Customer c : client.getCustomerStock()) {
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
}
