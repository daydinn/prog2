package net;

import java.net.*;
import java.util.List;
import java.io.*;
import valueObjects.Changelog;
import valueObjects.Customer;
import valueObjects.Employee;
import valueObjects.Item;

public class Client implements interfaces.ShopInterface {
	
	Socket clientSocket;
	PrintWriter pr;
	InputStreamReader in;
	BufferedReader bf;
	//ObjectOutputStream oos;
	//ObjectInputStream ois;

	
	public Client(int port) throws IOException{
		clientSocket = new Socket("localhost", port);
		
		pr = new PrintWriter(clientSocket.getOutputStream());
		pr.println("Connection Established.");
		pr.flush();
		
		in = new InputStreamReader(clientSocket.getInputStream());
		bf = new BufferedReader(in);
		
		System.out.println("Server: " + bf.readLine());
	}

	// -----------------------------------------------USERMANAGER-------------------------------------------------------//

	
	/**
	 * Description: adds a customer to Customer Stock
	 * 
	 * @param c
	 * @throws IOException 
	 */
	public void add(Customer c) throws IOException {
		pr.println("add customer");
		pr.flush();
		/*
		oos.writeObject(c);
		oos.flush();
		*/
		pr.println(c.getCustomerNr());
		pr.flush();
		pr.println(c.getUsername());
		pr.flush();
		pr.println(c.getPassword());
		pr.flush();
		pr.println(c.getFirstname());
		pr.flush();
		pr.println(c.getLastname());
		pr.flush();
		pr.println(c.getAdress());
		pr.flush();
	}

	/**
	 * Description: deletes a customer, who has the given number
	 * 
	 * @param cNumber
	 */
	public void cdeleting(int cNumber) {
		pr.println("delete customer");
		pr.flush();
		pr.println(cNumber);
		pr.flush();
	}

	/**
	 * Description: add a employee to Employee stock
	 * 
	 * @param m
	 * @throws IOException 
	 */
	public void add(Employee e) throws IOException {
		pr.println("add employee");
		pr.flush();
		/*
		oos.writeObject(e);
		oos.flush();
		*/
		pr.println(e.getEmployeeNr());
		pr.flush();
		pr.println(e.getUsername());
		pr.flush();
		pr.println(e.getPassword());
		pr.flush();
		pr.println(e.getFirstname());
		pr.flush();
		pr.println(e.getLastname());
		pr.flush();
		pr.println(e.getEmail());
		pr.flush();
	}

	/**
	 * Description: deletes a employee, who has the given number
	 * 
	 * @param mNummer
	 */
	public void edeleting(int eNumber) {
		pr.println("delete employee");
		pr.flush();
		pr.println(eNumber);
		pr.flush();
	}

	/**
	 * Description: searches in the customer list for a number
	 * 
	 * @param nr
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public Customer searchCustomerNr(int nr) throws ClassNotFoundException, IOException {
		pr.println("search customer number");
		pr.flush();
		pr.println(nr);
		pr.flush();
		
		int CustomerNr = Integer.parseInt(bf.readLine());
		String Username = bf.readLine();
		String Password = bf.readLine();
		String Firstname = bf.readLine();
		String Lastname = bf.readLine();
		String Adress = bf.readLine();
		
		return new Customer(Username, Password, Firstname, Lastname, Adress, CustomerNr);
	}

	/**
	 * Description: searches in the employee list for a number
	 * 
	 * @param nr
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public Employee searchEmployeeNr(int nr) throws ClassNotFoundException, IOException{
		pr.println("search employee number");
		pr.flush();
		pr.println(nr);
		pr.flush();

		int EmployeeNr = Integer.parseInt(bf.readLine());
		String Username = bf.readLine();
		String Password = bf.readLine();
		String Firstname = bf.readLine();
		String Lastname = bf.readLine();
		String Email = bf.readLine();
		
		return new Employee(Username, Password, Firstname, Lastname, Email, EmployeeNr);
	}

	/**
	 * Description: searches in the customer list for a Name
	 * 
	 * @param name
	 * @return searchResult
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */

	public List<Customer> searchCustomerName(String name) throws ClassNotFoundException, IOException{
		pr.println("search customer name");
		pr.flush();
		pr.println(name);
		pr.flush();
		
		List<Customer> retList = null;//new ArrayList<Customer>();
		
		while(bf.readLine() != "\u00EB") {

			int CustomerNr = Integer.parseInt(bf.readLine());
			String Username = bf.readLine();
			String Password = bf.readLine();
			String Firstname = bf.readLine();
			String Lastname = bf.readLine();
			String Adress = bf.readLine();
			
			retList.add(new Customer(Username, Password, Firstname, Lastname, Adress, CustomerNr));
		}
		
		return retList;
	}

	/**
	 * Description: searches in the employee list for a Name
	 * 
	 * @param name
	 * @return searchResult
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */

	public List<Employee> searchEmployeeName(String name) throws ClassNotFoundException, IOException{
		pr.println("search employee name");
		pr.flush();
		pr.println(name);
		pr.flush();
		
		List<Employee> retList = null;//new ArrayList<Employee>();
		
		while(bf.readLine() != "\u00EB") {
			int EmployeeNr = Integer.parseInt(bf.readLine());
			String Username = bf.readLine();
			String Password = bf.readLine();
			String Firstname = bf.readLine();
			String Lastname = bf.readLine();
			String Email = bf.readLine();
		
			retList.add(new Employee(Username, Password, Firstname, Lastname, Email, EmployeeNr));
		}
		
		return retList;
	}

	/**
	 * Description: outputs employees as a list
	 * 
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public List<Employee> getEmployeeStock() throws ClassNotFoundException, IOException{
		pr.println("search employee all");
		pr.flush();
		
		List<Employee> retList = null;//new ArrayList<Employee>();
		
		while(bf.readLine() != "\u00EB") {
			int EmployeeNr = Integer.parseInt(bf.readLine());
			String Username = bf.readLine();
			String Password = bf.readLine();
			String Firstname = bf.readLine();
			String Lastname = bf.readLine();
			String Email = bf.readLine();
		
			retList.add(new Employee(Username, Password, Firstname, Lastname, Email, EmployeeNr));
		}
		
		
		return retList;
	}

	/**
	 * Description: outputs customers as a list
	 * 
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public List<Customer> getCustomerStock() throws ClassNotFoundException, IOException{
		pr.println("search customer all");
		pr.flush();
		
		List<Customer> retList = null;//new ArrayList<Employee>();
		
		while(bf.readLine() != "\u00EB") {

			int CustomerNr = Integer.parseInt(bf.readLine());
			String Username = bf.readLine();
			String Password = bf.readLine();
			String Firstname = bf.readLine();
			String Lastname = bf.readLine();
			String Adress = bf.readLine();
			
			retList.add(new Customer(Username, Password, Firstname, Lastname, Adress, CustomerNr));
		}
		
		return retList;
	}

	// ---------------------------------------------------------STORAGEMANAGER------------------------------------------------------------//

	/**
	 * adds an item to the inventory
	 * 
	 * @param a
	 * @throws IOException 
	 */

	public void add(Item i) throws IOException {
		pr.println("add item");
		pr.flush();
		/*
		oos.writeObject(i);
		oos.flush();
		*/
		pr.println(i.getName());
		pr.flush();
		pr.println(i.getNumber());
		pr.flush();
		pr.println(i.getPrice());
		pr.flush();
		pr.println(i.getStock());
		pr.flush();
		pr.println(i.getminimumStock());
		pr.flush();
		pr.println(i.getBulk());
		pr.flush();
	}

	/**
	 * deletes an item that has the given number
	 * 
	 * @param nummer
	 */
	public void ideleting(int number) {
		pr.println("delete item");
		pr.flush();
		pr.println(number);
		pr.flush();
	}

	/**
	 * Searches the item inventory for a name
	 * 
	 * @param name
	 * @return a list of search results
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public List<Item> searchItemName(String name) throws ClassNotFoundException, IOException{
		pr.println("search item name");
		pr.flush();
		pr.println(name);
		pr.flush();
		
		List<Item> retList = null;//new ArrayList<Customer>();
		while(bf.readLine() != "\u00EB") {

			String iname = bf.readLine();
			int number = Integer.parseInt(bf.readLine());
			double price = Double.parseDouble(bf.readLine());
			int stock = Integer.parseInt(bf.readLine());
			int minimumStock = Integer.parseInt(bf.readLine());
			int bulk = Integer.parseInt(bf.readLine());
			
			retList.add(new Item(iname, number, price, stock, minimumStock, bulk));
		}
		
		return retList;
	}

	/**
	 * searches the list for the given item number
	 * 
	 * @param nr
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public Item searchItemNr(int nr) throws ClassNotFoundException, IOException{
		pr.println("search item number");
		pr.flush();
		pr.println(nr);
		pr.flush();
		
		String iname = bf.readLine();
		int number = Integer.parseInt(bf.readLine());
		double price = Double.parseDouble(bf.readLine());
		int stock = Integer.parseInt(bf.readLine());
		int minimumStock = Integer.parseInt(bf.readLine());
		int bulk = Integer.parseInt(bf.readLine());
		
		return new Item(iname, number, price, stock, minimumStock, bulk);
	}

	/**
	 * returns a list of the item inventory
	 * 
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public List<Item> getItemStock() throws ClassNotFoundException, IOException{
		pr.println("search item all");
		pr.flush();
		
		List<Item> retList = null;//new ArrayList<Customer>();
		while(bf.readLine() != "\u00EB") {

			String iname = bf.readLine();
			int number = Integer.parseInt(bf.readLine());
			double price = Double.parseDouble(bf.readLine());
			int stock = Integer.parseInt(bf.readLine());
			int minimumStock = Integer.parseInt(bf.readLine());
			int bulk = Integer.parseInt(bf.readLine());
			
			retList.add(new Item(iname, number, price, stock, minimumStock, bulk));
		}
		
		return retList;
	}

	// ---------------------------------------------------------CHANGELOGMANAGER------------------------------------------------------------//

	/**
	 * Description: add a new Changelog
	 * 
	 * @param c
	 * @throws IOException 
	 */
	public void add(Changelog c) throws IOException {
		pr.println("add changelog");
		pr.flush();
		/*
		oos.writeObject(c);
		oos.flush();
		*/
		pr.println(c.getTyp());
		pr.flush();
		pr.println(c.getMessage());
		pr.flush();
		pr.println(c.getTime());
		pr.flush();
		
		if(c.getTyp()) {
			pr.println(c.getEmployee().getEmployeeNr());
			pr.flush();
			pr.println(c.getEmployee().getUsername());
			pr.flush();
			pr.println(c.getEmployee().getPassword());
			pr.flush();
			pr.println(c.getEmployee().getFirstname());
			pr.flush();
			pr.println(c.getEmployee().getLastname());
			pr.flush();
			pr.println(c.getEmployee().getEmail());
			pr.flush();
		}else {
			pr.println(c.getCustomer().getCustomerNr());
			pr.flush();
			pr.println(c.getCustomer().getUsername());
			pr.flush();
			pr.println(c.getCustomer().getPassword());
			pr.flush();
			pr.println(c.getCustomer().getFirstname());
			pr.flush();
			pr.println(c.getCustomer().getLastname());
			pr.flush();
			pr.println(c.getCustomer().getAdress());
			pr.flush();
		}
	}

	/**
	 * Gibt den aktuellen Changelog aus
	 * 
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public List<Changelog> getChangelog() throws ClassNotFoundException, IOException{
		pr.println("search changelog all");
		pr.flush();
		
		List<Changelog> retList = null;//new ArrayList<Employee>();
		while(bf.readLine() != "\u00EB") {
			boolean type = Boolean.parseBoolean(bf.readLine());
			String message = bf.readLine();
			String time = bf.readLine();
			if(type) {
				int EmployeeNr = Integer.parseInt(bf.readLine());
				String Username = bf.readLine();
				String Password = bf.readLine();
				String Firstname = bf.readLine();
				String Lastname = bf.readLine();
				String Email = bf.readLine();
				
				Employee e = new Employee(Username, Password, Firstname, Lastname, Email, EmployeeNr);
				
				retList.add(new Changelog(e, message, type, time));
			}else {
				int CustomerNr = Integer.parseInt(bf.readLine());
				String Username = bf.readLine();
				String Password = bf.readLine();
				String Firstname = bf.readLine();
				String Lastname = bf.readLine();
				String Adress = bf.readLine();
				
				Customer c = new Customer(Username, Password, Firstname, Lastname, Adress, CustomerNr);
				
				retList.add(new Changelog(c, message, type, time));
			}
		}
		
		return retList;
	}

	/**
	 * searches the changelog for a name and returns all entries with the name.
	 * 
	 * @param name
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws InvalidNameChangelogException
	 */
	public List<Changelog> searchChangelogName(String name) throws ClassNotFoundException, IOException{
		pr.println("search changelog name");
		pr.flush();
		pr.println(name);
		pr.flush();
		
		List<Changelog> retList = null;//new ArrayList<Employee>();
		while(bf.readLine() != "\u00EB") {
			boolean type = Boolean.parseBoolean(bf.readLine());
			String message = bf.readLine();
			String time = bf.readLine();
			if(type) {
				int EmployeeNr = Integer.parseInt(bf.readLine());
				String Username = bf.readLine();
				String Password = bf.readLine();
				String Firstname = bf.readLine();
				String Lastname = bf.readLine();
				String Email = bf.readLine();
				
				Employee e = new Employee(Username, Password, Firstname, Lastname, Email, EmployeeNr);
				
				retList.add(new Changelog(e, message, type, time));
			}else {
				int CustomerNr = Integer.parseInt(bf.readLine());
				String Username = bf.readLine();
				String Password = bf.readLine();
				String Firstname = bf.readLine();
				String Lastname = bf.readLine();
				String Adress = bf.readLine();
				
				Customer c = new Customer(Username, Password, Firstname, Lastname, Adress, CustomerNr);
				
				retList.add(new Changelog(c, message, type, time));
			}
		}
		
		return retList;
	}

	/**
	 * searches the changelog for a nr and returns all entries with the nr.
	 * 
	 * @param nr
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws InvalidNumberChangelogException
	 */

	public Changelog searchChangelogNr(int nr) throws ClassNotFoundException, IOException{
		pr.println("search changelog number");
		pr.flush();
		pr.println(nr);
		pr.flush();
		
		boolean type = Boolean.parseBoolean(bf.readLine());
		String message = bf.readLine();
		String time = bf.readLine();
		if(type) {
			int EmployeeNr = Integer.parseInt(bf.readLine());
			String Username = bf.readLine();
			String Password = bf.readLine();
			String Firstname = bf.readLine();
			String Lastname = bf.readLine();
			String Email = bf.readLine();
			
			Employee e = new Employee(Username, Password, Firstname, Lastname, Email, EmployeeNr);
			
			return new Changelog(e, message, type, time);
		}else {
			int CustomerNr = Integer.parseInt(bf.readLine());
			String Username = bf.readLine();
			String Password = bf.readLine();
			String Firstname = bf.readLine();
			String Lastname = bf.readLine();
			String Adress = bf.readLine();
			
			Customer c = new Customer(Username, Password, Firstname, Lastname, Adress, CustomerNr);
			
			return new Changelog(c, message, type, time);
		}
	}
}
