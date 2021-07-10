package CUI;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JLabel;

import Domain.ChangelogManager;
import Exceptions.IncorrectLoginDataException;
import Exceptions.InvalidCartException;
import Exceptions.InvalidCartNameException;
import Exceptions.InvalidCustomerNameException;
import Exceptions.InvalidCustomerNumberException;
import Exceptions.InvalidEmployeeNameException;
import Exceptions.InvalidEmployeeNumberException;
import Exceptions.InvalidItemNameException;
import Exceptions.InvalidItemNumberException;

import LoginFunctions.LoginCustomer;
import LoginFunctions.LoginEmployee;

import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import Valueobjects.Cart;
import Valueobjects.Changelog;
import Valueobjects.Customer;
import Valueobjects.CustomerManagement;
import Valueobjects.Employee;
import Valueobjects.EmployeeManagement;
import Valueobjects.Item;
import Valueobjects.Storage;
import Valueobjects.cartItem;



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
	
	
	
	private static Employee system;

	  private int currentCustomer;
	  private int currentEmployee;

	public CUI(String dItems, String dEmployees, String dCustomers, String dLog) {
		
		try {
			storage = new Storage(dItems);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//changelog.writeData(" Storage has been created");
		
		try {
			employeeManagement = new EmployeeManagement(dEmployees);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//changelog.writeData("Employee Management has been created");
		
		try {
			customerManagement = new CustomerManagement(dCustomers);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//changelog.writeData("Customer Management has been created");
		
		
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
	
	public String readEntry() {
		// read the console entries
		String input = "";
		Scanner sc = new Scanner(System.in);
		input = sc.nextLine();
		
		// Function to end the program
		if(input.equals("quit")) {
			System.out.println("the program is now terminated...");
			
			try {
				logmanager.writeData("The program is ended.");
				logmanager.writeData("Log");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			System.exit( 0 );
		}
		return input;
	}
	
	



	public void run() {
		// Variable for inputs from the console
		String input = "";
	
		//Main for the user interface
		do {
			getMenu();
		} while (!input.equals("q"));
	
	}


	//Startmenu
	public void getMenu() {
		String input = "";
		System.out.println("");
		System.out.println("Please log in or register ......");
		System.out.println("To login, press  'A'");
		System.out.println("To register, press  'R'");
		System.out.println("");
		System.out.print(">>");
		input = readEntry();
		switch(input) {
		case "A": //Login
			shopLogin();
			break;
		case "R": //Register
			shopCustomerRegistration(false);
			break;		
		default:
			System.out.println("Incorrect entry!");
			getMenu();
		}
	}
	
	public void employeeMenu() {
		String input = "";
		String itemName = "";
		String itemNumber = "";
		String itemPrice = "";
		String itemStock = "";
		String itemMinimumStock = "";
		String eNumber = "";
		String cNumber = "";
		String itemAmount = "";
		String itemBulk= "";
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
		System.out.println("1. Show items: '1': ");
		System.out.println("2. Add an Item: '2': ");
		System.out.println("3. Delete an Item: '3': ");
		
		System.out.println("4. Search an Item: '4': ");
		System.out.println("5. Sort Items by Name: '5': ");
		System.out.println("6. Sort Items by number: '6': ");
		System.out.println("7. Change Amount of Item: ");
		System.out.println("8. Add item to shopping cart: '8': ");
		System.out.println("9. Remove item from shopping cart: '9': ");
		System.out.println("10. Empty cart: '10': ");
		System.out.println("11. Show Shopping Cart: '11': ");
		System.out.println("12. Buy Shopping cart: '12': ");
		System.out.println("13. Show changelog: '13': ");
		System.out.println("14. Create new employee: '14': ");
		System.out.println("15. Show all employees: '15': ");
		System.out.println("16. Delete an employee: '16': ");
		System.out.println("17. Search an employee: '17': ");
		System.out.println("18. Create a customer: '18': ");
		System.out.println("19. Delete a customer: '19': ");
		System.out.println("20. Show all customers: '20': ");
		System.out.println("21. Find a customer: '21': ");
		System.out.println("22. Save Customerlist: '22': ");
		System.out.println("23. Save employee list: '23': ");
		System.out.println("24. Logout: '24': ");
		System.out.println("25. Output ChangeLog: '25': ");
		System.out.println("26. Save ChangeLog:: '26': ");
		System.out.println("-----------------------------------------------");
		System.out.println("");
		System.out.print(">>");
		
		input = readEntry();
		switch(input) {
		case "1": //output all items
			System.out.println("");
			getItemlist(ilist);
			System.out.println("");
			try {
				logmanager.writeData("All items has been  displayed");
			} catch (IOException e9) {
				
				e9.printStackTrace();
			}
			employeeMenu();
			break;
		
		case "2": //add an Item
			
			
			System.out.println("");
			System.out.println("Please enter the item name: ");
			System.out.println("");
			System.out.print(">>");
			itemName = readEntry();
			
			System.out.println("");
			System.out.println("Please enter the item number: ");
			System.out.println("");
			System.out.print(">>");
			itemNumber = readEntry();
			iNum = Integer.parseInt(itemNumber);
			for (Item i: storage.getAllItems()) { // goes through all items
            while (i.getNumber() == iNum) { // if the item number already exists
            System.out.println("The item number already exists!"); //error message
            System.out.println("try again...");
           itemNumber = readEntry();
			iNum = Integer.parseInt(itemNumber);
			}
			}
			
			
			
			System.out.println("");
			System.out.println("Please enter the price of the item : ");
			System.out.println("");
			System.out.print(">>");
			itemPrice = readEntry();
			iPri = Double.parseDouble(itemPrice);
			
			System.out.println("");
			System.out.println("Please enter the item stock: ");
			System.out.println("");
			System.out.print(">>");
			itemStock = readEntry();
			iSto = Integer.parseInt(itemStock);
			
			System.out.println("");
			System.out.println("Please enter the minimum stock of item : ");
			System.out.println("");
			System.out.print(">>");
			itemMinimumStock = readEntry();
			iMin = Integer.parseInt(itemMinimumStock);
			
			System.out.println("");
			System.out.println("Please enter the Bulk : ");
			System.out.println("");
			System.out.print(">>");
			itemBulk = readEntry();
			iBulk = Integer.parseInt(itemBulk);
			
			
			
			
			
			
			System.out.println("");
			System.out.println("The item has been successfully created.");
			   try {
		              for (Item i: storage.getAllItems()) { // goes through all items
		                if (i.getNumber() == iNum) { // if the item number already exists
		                   System.out.println("The item number already exists!"); //error message
		                  logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "you tried to insert an existing item!", true));
		                   throw new InvalidItemNumberException(); //throw the exception

		                }
		              }
		            } catch (InvalidItemNumberException ex) {
		              System.out.println(ex.getMessage()); // catches the exception and displays it in the console
		            }
			       if (!itemName.isEmpty()) { // if all fields are filled correctly
		              storage.addAnItem(itemName, iNum, iPri, iSto, iMin, iBulk); // add new item to the Storage
		              logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "The Item: " + itemName + " has been added.", true));
		              
		              try {
		                storage.writeItems(); // saves the new stock in the txt file
		              } catch (IOException e1) {

		                e1.printStackTrace();
		              }
		              System.out.println("");
		              getItemlist(ilist); //show all items
		              employeeMenu();     // go back to the menu           
		            
		            }
                    break;
		        
		
		
		
		
		case "3": //delete Items
			System.out.println("");
			System.out.println("Please enter the item number of the item to be deleted: ");
			System.out.println("");
			System.out.print(">>");
			itemNumber = readEntry();
			iNum = Integer.parseInt(itemNumber);
			
			 try { // try and catch for the correctness of the item number
	              if (checkNumber(iNum)) { //if yes delete item
	                storage.deleteItem(iNum); //deletes the item from the storage
	                logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "The Item with number : " + iNum + " has been deleted!", true));
	                 try {
	                  storage.writeItems(); //saves the items in the TXT file
	                } catch (IOException e1) {
	                  e1.printStackTrace();
	                }
	              }
	            } catch (InvalidItemNumberException ex) { // if not, catch the exception and give an error message
	              System.out.println("");
	              logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), ex.getMessage(), true)); //schriebt den Fehler im Changelog and outputs error message
	              System.out.println("Please enter a valid number"); // error message if the item number is wrong in the CUI
	              employeeMenu(); 
	             
	            }
		    break;
		    
		case "4": //search for an Item
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
	              
	              logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "There was an error while searching for an item", false));
	              employeeMenu();
	            }
			  
			  break;
		
		case "5": //Sort items by name
			 System.out.println("");
			 sortNameItemList(storage.getAllItems());
			System.out.println("");
			
			employeeMenu();
			break;
		
		case "6": //Sort items  by number
			System.out.println("");
			 
			sortNumberItemList(storage.getAllItems());
			System.out.println("");
			
			employeeMenu();
			break;
		
		
		case "7": //Change the amount of items
		
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
	          
	           
	            storage.getAllItems(); //update the Table
	            getItemlist(ilist);	
	            System.out.println("");
	            System.out.println("Stock changed!"); //report the successful change
	            logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "The Item: " + iNum + " hast been incrased by : " + iSto, true));
	            try {
	              storage.writeItems(); // save/write the new item inventory
	            } catch (IOException e1) {
	              e1.printStackTrace();
	            }
	          }
	        } catch (InvalidItemNumberException ex) { // if the entry was not correct ..
	          //System.out.println("Incorrect item number!"); //errormessage
	          System.out.println(ex.getMessage()); //output the error message in the console
	          logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), ex.getMessage() + " while increasing the stock", true));
	        }
		   employeeMenu();
		
		
		
		
		
		
			
			
			
			
			break;
		case "8": //add an item to cart
			System.out.println("");
			System.out.println("Please enter the item number of the Item: ");
			System.out.println("");
			System.out.print(">>");
			itemNumber = readEntry();
			iNum = Integer.parseInt(itemNumber);
			
			System.out.println("");
			System.out.println("Please enter die Amount of Item: ");
			System.out.println("");
			System.out.print(">>");
			itemAmount = readEntry();
			iAmo = Integer.parseInt(itemAmount);
			
			System.out.println("");
			cart.addItem(iNum, iAmo);
			System.out.println("The item has been added to the cart.");
			System.out.println("");
			cart.output();
			System.out.println("");
			employeeMenu();
			break; 
		case "9": //Change amount of Items
			System.out.println("");
			System.out.println("Please enter the number of items: ");
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
			
			System.out.println("");
			cart.delItem(iNum, iAmo);
			System.out.println("The item has been removed from the shopping cart.");
			System.out.println("");
			cart.output();
			System.out.println("");
			employeeMenu();
			break;
		case "10": // Empty cart
			System.out.println("");
			System.out.println("The shopping cart has been emptied.");
			System.out.println("");
			cart.empty();
			logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), " has emptied the  cart!", false));
			cart.output();
			employeeMenu();
			break;
		case "11": //View Shopping Cart
			System.out.println("");
			cart.output();
			System.out.println("");
			employeeMenu();
			break;
		case "12": //Buy Shopping CART
			System.out.println("");
			for (cartItem ti: cart.getCart()) {
			System.out.println(ti.getItem().getName());
			}
			break;
		case "13": // Show changelog
			break;
		case "14": //create new employee
			System.out.println("");
			shopEmployeeRegistration();
			System.out.println("");
			employeeMenu();
			break;
		case "15": //show all employees
			System.out.println("");
			getAllEmployees(elist);
			System.out.println("");
			employeeMenu();
			break;
		case "16": //Delete employee
			System.out.println("");
			System.out.println("Please enter the employee number of the employee to be deleted: ");
			System.out.println("");
			System.out.print(">>");
			eNumber = readEntry();
			eNum= Integer.parseInt(eNumber);
			employeeManagement.deleteEmployee(eNum);
			System.out.println("");
			System.out.println("The employee has been deleted.");
			try {
				logmanager.writeData("The employee with the employee number " + eNum + " has been deleted");
			} catch (IOException e6) {
				
				e6.printStackTrace();
			}
			System.out.println("");
			employeeMenu();
			break;
		case "17": //Search for employees
			System.out.println("");
			System.out.println("Employeename:    ");
			eNumber = readEntry();
			eNum = Integer.parseInt(eNumber);
			elist = employeeManagement.searchByNumber(eNum);
			getAllEmployees(elist);
			System.out.println("");
			employeeMenu();
			break;
		case "18": //create new customer
			System.out.println("");
			shopCustomerRegistration(true);
			System.out.println("");
			employeeMenu();
			break;
		case "19": //Delete customers
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
				logmanager.writeData("The customer with the customer number " + cNum + " has been deleted");
			} catch (IOException e5) {
				
				e5.printStackTrace();
			}
			System.out.println("");
			employeeMenu();
			break;
		case "20": //Show all customers
			System.out.println("");
			getAllCustomers(clist);
			System.out.println("");
			employeeMenu();
			break;
		case "21": //search customers
			System.out.println("");
			System.out.println("Customer number:   ");
			cNumber = readEntry();
			cNum = Integer.parseInt(cNumber);
			clist = customerManagement.searchByNumber(cNum);
			getAllEmployees(elist);
			System.out.println("");
			employeeMenu();
			break;
		case "22": //save customer list
			try {
				customerManagement.writeCustomers();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			System.out.println("");
			System.out.println("The customer list has been saved.");
			try {
				logmanager.writeData("The customer list has been saved.");
			} catch (IOException e4) {
				
				e4.printStackTrace();
			}
			System.out.println("");
			employeeMenu();
			break;
		case "23": //Save employee list
			try {
				employeeManagement.writeEmployees();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("");
			System.out.println("The employee list has been saved.");
			try {
				logmanager.writeData("The employee list has been saved.");
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			System.out.println("");
			employeeMenu();
			break;
		case "24": //logout
			System.out.println("");
			try {
				logmanager.writeData("The user has logged off");
				logmanager.writeData("Log");
			} catch (IOException e2) {
			
				e2.printStackTrace();
			}
			getLog();
			System.out.println("You are now logging out ...");
			System.out.println("");
			
			shopLogin();
			break;
		case "25": //output changelog
			System.out.println("");
			getLog();
			System.out.println("");
			try {
				logmanager.writeData("The changelog hasbeen displayed ...");
			} catch (IOException e1) {
			
				e1.printStackTrace();
			}
			employeeMenu();
			break;
		case "26": //save changelog
			System.out.println("");
			try {
				logmanager.writeData("Log");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			System.out.println("");
			try {
				logmanager.writeData("The changelog has beensaved ...");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			employeeMenu();
			break;
		default:
			System.out.println("");
			try {
				logmanager.writeData("Incorrect entry in the employee menu");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			System.out.println("Incorrect entry, please try again ...");
			System.out.println("");
			employeeMenu();
		}
		
	}
	
	public void customerMenu() {
		String input = "";
		String itemName = "";
		String itemNumber ="";
		int iNum;
		String itemAmount ="";
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
		switch(input) {
		case "1": //show all items
			System.out.println("All Items :");
			System.out.println("");
			getItemlist(ilist);
			System.out.println("");
			
			customerMenu();
			break;
		
		
		
		
		case "2": //search an Item
		
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
	              
	              logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "There was an error while searching for an item", false));
	             customerMenu();
	            }	
			break; 
		
		
		
		case "3": //Sort items  by name
			System.out.println("");
		   System.out.println("Items are sorted by name...");
			System.out.println("");
			
		   
		   sortNameItemList(storage.getAllItems());
		   System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		   System.out.println("");
		   customerMenu();
			break;
		
	
	    case "4": //Sort Items by Number
	    	System.out.println("");
			System.out.println("Items are sorted by number...");
	    	System.out.println("");
	    	
			 
			sortNumberItemList(storage.getAllItems());
			System.out.println("");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			customerMenu();
			break;
		
	    case "5": //Add an item to Shopping Cart
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
		        	 
		         
				 for(cartItem ti: cart.getCart()) {
			        	if(ti.getItem().getNumber() == iNum) {
			        	ti.setAmount(ti.getAmount()+iAmo);
			        	System.out.println("---------Cart-------------------------------------------------------------------------------------------------------------");
			            cart.output();
			            System.out.println("");
						System.out.println("");
						System.out.println("totalprice =" + cartGetTotalprice());
			            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
			            System.out.println("");
			        	customerMenu();
			        	}
			        	}
		         }
		         catch(Exception ex){
		        System.out.println("");
		        System.out.println("Error: invalid entrys");
		        System.out.println("");
		        logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Incorrect entry while adding to shopping cart", false));
		        System.out.println("");
		        customerMenu();
		         }
				 
				 
				 
		         try {
				 if (checkCart(iNum, iAmo)) {
		            cart.addItem(iNum, iAmo);
		            
		            System.out.println("---------Cart-------------------------------------------------------------------------------------------------------------");
		            cart.output();
		            System.out.println("");
					System.out.println("");
					System.out.println("totalprice =" + cartGetTotalprice());
		            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		            System.out.println("");
		            System.out.println("");
		           
		            System.out.println("Item added.");
		            System.out.println("");
		           
		            
		            logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "The Item: " + iNum + " has been added  " + iAmo + "times to the Cart", false));
		            System.out.println("");
		            customerMenu();

		            
		          }
		        } catch (InvalidCartException ex) {
		        System.out.println("");
		        System.out.println("Error");
		        System.out.println("Uncorrect Entry!");
		         logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Incorrect entry while adding to shopping cart", false));
		          System.out.println(ex.getMessage());
		          System.out.println("");
				cart.output();
					System.out.println("");
					customerMenu();

		        }
			break;
		
	    case "6": //change the amount of items in Cart
			
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
		            logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "The amount of Item:" + iNum + " has been changed to " + iAmo, true));
		            System.out.println("");
		            System.out.println("---------Cart-------------------------------------------------------------------------------------------------------------");
		            cart.output();
		            System.out.println("");
					System.out.println("");
					System.out.println("totalprice =" + cartGetTotalprice());
		            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		            System.out.println("");
		            customerMenu();
		          }
		        } catch (InvalidCartException ex) {
		          System.out.println(ex.getMessage());
		        
		          logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Uncorrect entry while deleting an Item", true));
		        		
		          System.out.println("");
		          System.out.println("---------Cart-------------------------------------------------------------------------------------------------------------");
		            cart.output();
		            System.out.println("");
					System.out.println("");
					System.out.println("totalprice =" + cartGetTotalprice());
		            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		            System.out.println("");
		            customerMenu();
		        
		         }
               
			
			break;
		
	    case "7": //Empty Shoppingcart
			 logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), " has emptied the  cart!", false));
		     cart.empty();
		     System.out.println("");
		     System.out.println("---------Cart-------------------------------------------------------------------------------------------------------------");
	         cart.output();
	         System.out.println("");
			 System.out.println("");
			 System.out.println("totalprice =" + cartGetTotalprice());
	         System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		     System.out.println("");
			customerMenu();
			break;
		
		
		case "8": //Show Shoppingcart
			
			
			
			System.out.println("");
			System.out.println("---------Cart-------------------------------------------------------------------------------------------------------------");
			
			cart.output();
			
			System.out.println("");
			System.out.println("");
			System.out.println("totalprice =" + cartGetTotalprice());
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            System.out.println("");
			
			customerMenu();
		 
			break;
		case "9": //Buy Shoppigcart
			 String customerName= "Herr " + customerManagement.searchByNumber(currentCustomer).get(0).getLastname();
			 String Date = new Date().toGMTString();
			 String customerNr = "" + customerManagement.searchByNumber(currentCustomer).get(0).getCustomerNr();
			
           System.out.println("---------Cart-------------------------------------------------------------------------------------------------------------");
			
			cart.output();
			System.out.println("");
			System.out.println("");
			System.out.println("totalprice =" + cartGetTotalprice());
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            System.out.println("");
			
			
			String yesorno;
			System.out.println("Are you sure to buy theese Items for "+cartGetTotalprice()+ "� ?");
			System.out.println("Enter 'Y' for Yes | 'N'+ for No ");
			System.out.println("");
			System.out.print(">>");
			yesorno = readEntry();
			
			
			while(!yesorno.equals("N") || !yesorno.equals("Y")) {
				System.out.println("Invalid entry, please try again");
				System.out.println("");
				System.out.print(">>");
				yesorno = readEntry();		
			
			
			
			if(yesorno.equals("Y")) {
				 logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Items has been bought!", false));
		         cart.buy();
		         
				System.out.println("");
				
			    System.out.println("Items in the shopping carthas been bought for "+ cartGetTotalprice()+"�  "+"\n"+"\n"+ "Bill :"  );
			    System.out.println("");
			    System.out.println("...................BILL............................................................................ ");
			    
			    System.out.println(
			    "Customer Name: "+customerName+"\n"+
			    "Customer Nr: "+customerNr+"\n"+ 
			    "Date of Bill: "+Date	);
			    System.out.println("");
				
			    System.out.print(
			    "Totalprice: "+cartGetTotalprice()+
			    "\n"+
				billGetItems());
			    System.out.println("");
			    System.out.println( "Thankyou for your oder!"+"\n"+"Kind regards, Eshop Team");
				
				System.out.println("");
				System.out.println("............................................................................................... ");
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
			
			if(yesorno.equals("N")) {
				System.out.println("");
				System.out.println("The items are still in cart if you change your mind");
				System.out.println("");
				customerMenu();
			
			}
			}
			
			if(yesorno.equals("Y")) {
				 logmanager.add(new Changelog(customerManagement.searchByNumber(currentCustomer).get(0), "Items has been bought!", false));
		         cart.buy();
		         
				System.out.println("");
				
			    System.out.println("Items in the shopping carthas been bought for "+ cartGetTotalprice()+"�  "+"\n"+"\n"+ "Bill :"  );
			    System.out.println("");
			    System.out.println("...................BILL............................................................................ ");
			    
			    System.out.println(
			    "Customer Name: "+customerName+"\n"+
			    "Customer Nr: "+customerNr+"\n"+ 
			    "Date of Bill: "+Date	);
			    System.out.println("");
				
			    System.out.print(
			    "Totalprice: "+cartGetTotalprice()+
			    "\n"+
				billGetItems());
			    System.out.println("");
			    System.out.println( "Thankyou for your oder!"+"\n"+"Kind regards, Eshop Team");
				
				System.out.println("");
				System.out.println("............................................................................................... ");
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
			
			if(yesorno.equals("N")) {
				System.out.println("");
				System.out.println("The items are still in cart");
				System.out.println("");
				customerMenu();
			}
			
			
			    
			break;
		case "10": //Logout
			System.out.println("");
			try {
				//logmanager.writeData("The user has logged off");
				logmanager.writeData("Log");
			} catch (IOException e2) {
			
				e2.printStackTrace();
			}
			//getLog();
			System.out.println("You are now logging out ...");
			System.out.println("");
			
			
				
				
			
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
		switch(input) {
		case "C": //Customer
			shopLoginCustomer();
			break;
		case "E": //Employee
			shopLoginEmployee();
			break;
		default:
			System.out.println("Incorrect entry!");
			try {
				logmanager.writeData("There was an incorrect entry in the start menu");
				System.out.println("Please try again");
				shopLogin();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			getMenu();
		}
	}
	
	
	
	
	
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
		LoginEmployee a  = new LoginEmployee();
	  
	
		try {
			a.login(employeeManagement.getAllEmployees(), username, password);
			currentEmployee = a.getNumber();
			System.out.print(currentEmployee);
			employeeMenu();
			logmanager.add(new Changelog(employeeManagement.searchByNumber(a.getNumber()).get(0), username + "  has logged in ", true));
		
		 } catch (IncorrectLoginDataException ex) {
	          System.out.println("Username or Password is uncorrect!");
	          logmanager.add(new Changelog(system, "Login failed!", true));
	        }
		
		
		
	
	
			
	}
			
			
		
	
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
		
		  try {
	          a.login(customerManagement.getAllCustomers(), username, password);
	          currentCustomer = a.getNumber();
	          System.out.println("| " + currentCustomer + " |");
	          customerMenu();
	          logmanager.add(new Changelog(customerManagement.searchByNumber(a.getNumber()).get(0), username+   "  has logged in.", false));
	          System.out.println("Customer : " + username +" has logged in");
	        } catch (IncorrectLoginDataException ex) {
	          System.out.println("Username or Password is uncorrect!");
	          logmanager.add(new Changelog(system, "Login failed!", true));
	        }

	      }
  

		
	/**
	   * Used by: CustomerMenu & getMenu
	   * Description: The CUI for customer registration is generated.
	   * @param b indicates whether you are already logged in or whether the program works  before login
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
	    System.out.println("You have been given the number : " +customerNr);
	    System.out.println("");
		
		
		if(!username.isEmpty()) {
	customerManagement.addACustomer(username, password, firstname, lastname, adress, customerNr);
	    try {
        customerManagement.writeCustomers();
        } catch (IOException e1) {

        e1.printStackTrace();
       }
		
		
		}
		System.out.println("");
		System.out.println("New Customer with username -> "+username+ " and nr-> "+customerNr+ " has been created");
		System.out.println("");
		if (!b) {
	            shopLoginCustomer();
	            logmanager.add(new Changelog(system, "the customer: " + username + " | " + customerNr + " has been created.", true));
	          } else {
	            logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "the customer: " + username + " | " + customerNr + " has been created.", true));
	          }
	
	}
		
		
		
	
		
		
		
	/**
	   * Used by: EmployeeMenu & getMenu
	   * Description: The CUi for customer registration is generated.
	   * @param b indicates whether the function is called from the employee menu or not
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
		System.out.println("You have been given the number : " +employeeNr);
		System.out.println("");
		
		
		
		if(!username.isEmpty()) {
		employeeManagement.addAnEmployee(username, password, firstname, lastname, email, employeeNr);
		}
		 try {
	            employeeManagement.writeEmployees();
	          } catch (IOException e1) {
	            
	            e1.printStackTrace();
	          }
         logmanager.add(new Changelog(employeeManagement.searchByNumber(currentEmployee).get(0), "Employee : " + username + " | " + employeeNr + " has been registered.", true));

         
		 System.out.println(""); 
		 System.out.println("New employee with username -> " + username +" and nr-> "+employeeNr+  " has been created.");
		 System.out.println("");
		 
	}

		
				
		
		
		
			
	
	
	 // automatic number assignment

	  public int newNumberCustomer(List < Customer > list) {
	    // outputs the highest customer number, it compares all customers in the list with getCustomerNr
	    Customer maxByNumber = list.stream().max(Comparator.comparing(Customer::getCustomerNr)).orElseThrow(NoSuchElementException::new);
	    // the highest customer number 
	    int newNumber = maxByNumber.getCustomerNr() + 1;
	    // returns the new number
	    return newNumber;
	  }

	  public int newNumberEmployee(List < Employee > liste) {
	    Employee maxByNumber = liste.stream().max(Comparator.comparing(Employee::getEmployeeNr)).orElseThrow(NoSuchElementException::new);
	    int newNumber = maxByNumber.getEmployeeNr() + 1;
	    return newNumber;
	  }
	
	
	
	
	
	
	
	
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
	
	
	//private static void getCart() {
 	//List<cartItem> cartList = cart.getCart();
 	//if(cartList.isEmpty()) {
 	//System.out.println("Cart is empty!");
 	//}else {
 	
 	//for(cartItem ci : cartList) {
 	//System.out.println(ci);
 	//}
	//}
	//}
	
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
		
	private static void sortNumberItemList(List<Item> list) {
		if (list.isEmpty()) {
			System.out.println("List is empty.");
		} else {
			
			Collections.sort(list, new Comparator<Item>() {
				  @Override
				  public int compare(Item u1, Item u2) {
				  
					  int x = Integer.compare(u1.getNumber(),u2.getNumber());
					  
				    return x;
				  }
				});
				
			}
			for (Item i : list) {
				System.out.println(i);
			}
		}
	
	
	private static void getAllEmployees(List<Employee> list) {
		if (list.isEmpty()) {
			System.out.println("List is empty.");
		} else {
			for (Employee e : list) {
				System.out.println(e);
			}
		}
	}

	private static void getAllCustomers(List<Customer> list) {
		if (list.isEmpty()) {
			System.out.println("List is empty.");
		} else {
			for (Customer c : list) {
				System.out.println(c);
			}
		}
	}
	
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
	
	
	public static void main(String[] args) {
		
		CUI cui;
		
		cui = new CUI("It","Emp","Cus","Log");
		
		
		cui.run();
	
	}
	
	
	
	
	
	
	//--------------------------------------------------Check Methods---------------------------------------------------------

	  /**
	   * @return true: Item for the number has been found. false: no item has been found and Exception is thrown
	   * @throws InvalidArtikelNummerException
	   */
	  private boolean checkNumber(int iNum) throws InvalidItemNumberException {
	    boolean x = false;
	    for (Item i: storage.getAllItems()) { // goes through the item list
	      System.out.println(i.getNumber() + " | " + iNum);
	      if (i.getNumber() == iNum) { // Checks whether the item number and Inr match
	        x = true; // if yes, true
	        break;
	      } else {
	        x = false; //if no false
	      }
	    }
	    if (!x) {
	      throw new InvalidItemNumberException(); //throws the exception
	    }
	    return x;
	  }

	  /**
	   * 
	   * @return true: A name has been found. false: no item with the name has been found.
	   * @throws InvalidArtikelNameException
	   */
	  private boolean checkName(String iName) throws InvalidItemNameException {
	    boolean x = false;
	    for (Item i: storage.getAllItems()) {
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
	   * @return true: A name has been found. false: no item with the name has been found.
	   * @throws InvalidCustomerNameException 
	   *
	   */
	  private boolean checkNameCus(String cName) throws InvalidCustomerNameException {
	    boolean x = false;
	    for (Customer c: customerManagement.getAllCustomers()) {
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
	   * @return true: A name has been found. false: no item with the name has been found.
	   * 
	   * @throws InvalidEmployeeNameException 
	   *
	   */
	  private boolean checkNameEmp(String eName) throws InvalidEmployeeNameException {
	    boolean x = false;
	    for (Employee e: employeeManagement.getAllEmployees()) {
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
	   * @return true: Item number and item stock match, inventory is increased. false: exception is thrown
	   * @throws InvalidArtikelNummerException
	   */
	  private boolean checkNumberStock(int iNum, int iSto) throws InvalidItemNumberException {
	    boolean x = false;
	    for (Item i: storage.getAllItems()) {
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
	    for (Employee e: employeeManagement.getAllEmployees()) {
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
	    for (Customer c: customerManagement.getAllCustomers()) {
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
	    for (Item i: storage.getAllItems()) {
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
	    for (Item i: storage.getAllItems()) {
	      //System.out.println(i.getNumber() == iAmo && iAmo <= i.getStock());
	      //System.out.println(!(i.getNumber() == iNum));
	      //System.out.println(i.getStock() <= iAmo);
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
	   * @return true: Item number and amount match the values. False: exception is thrown.
	   * @throws InvalidWarenkorbException
	   */
	  private boolean checkCartChange(int tiNum, int tiAmo) throws InvalidCartException {
	    boolean x = false;
	    for (cartItem ti: cart.getCart()) {
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
	   * @return true: Item number and amount match the values. False: exception is thrown.
	   * @throws InvalidWarenkorbException
	   */
	  private boolean checkCartDelete(int tiNum, int tiAmo) throws InvalidCartException {
	    boolean x = false;
	    for (cartItem ti: cart.getCart()) {
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
	   * @return true: an Item has been  found in the search. False: exception is thrown.
	   * @throws InvalidWarenkorbNameException
	   */
	  private boolean checkCartSearch(String aName) throws InvalidCartNameException {
	    boolean x = false;
	    for (cartItem ti: cart.getCart()) {
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
	 
/**
*calculates the total price of items in Cart
*   
*@return dummy   
*/
private double cartGetTotalprice() {
  double totalprice ;
  double dummy= 0;

 List<cartItem> cartList = cart.getCart();
	for(int i = 0; i< cartList.size(); i++) {
	
	totalprice = cartList.get(i).getItem().getPrice() * cartList.get(i).getAmount();
    dummy= totalprice + dummy;
	}
	
	return dummy;
}

private boolean billGetItems(){


	
List<cartItem> cartList = cart.getCart();	
for(int i = 0; i< cartList.size(); i++) {
String names = cartList.get(i).getItem().getName();
int nrs = cartList.get(i).getItem().getNumber();
String numbers = String.valueOf(nrs);

int amts = cartList.get(i).getAmount();
String amounts = String.valueOf(amts);

double prcs = cartList.get(i).getItem().getPrice() * cartList.get(i).getAmount();
String prices = String.valueOf(prcs);
List<String> list = new ArrayList();
list.add(" Itemname:  " + names+" " );
list.add(" Nr:  "+ numbers+" " );
list.add(" Amount:  "+"x"+amounts+" " );
list.add(" Price:  "+ prices+"� " );
System.out.println(list);

}
return true;

	
	
	

}
	
}	


	  
	