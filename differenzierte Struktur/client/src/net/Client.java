package net;

import java.net.*;
import java.util.List;
import java.io.*;
import valueObjects.Changelog;
import valueObjects.Customer;
import valueObjects.Employee;
import valueObjects.Item;

public class Client implements interfaces.ShopInterface {
	
	PrintWriter pr;
	InputStreamReader in;
	BufferedReader bf;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	
	/*   TODO change from main to constructor for better usability
	 */
	public static void main(String args[]) throws IOException {
		Client clientObj = new Client();
		Socket clientSocket = new Socket("localhost", 4999);

		PrintWriter pr = new PrintWriter(clientSocket.getOutputStream());
		pr.println("is it working?");
		pr.flush();

		InputStreamReader in = new InputStreamReader(clientSocket.getInputStream());
		BufferedReader bf = new BufferedReader(in);

		String str = bf.readLine();
		System.out.println("server: " + str);

        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
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
		oos.writeObject(c);
		oos.flush();
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
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
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
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
		oos.writeObject(e);
		oos.flush();
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
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
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
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
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
		pr.flush();
		
		return (Customer) ois.readObject();
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
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
		pr.flush();
		
		return (Employee) ois.readObject();
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
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
		pr.flush();
		
		List<Customer> retList = null;//new ArrayList<Customer>();
		while(ois.readObject() != null) {
			retList.add((Customer) ois.readObject());
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
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
		pr.flush();
		
		List<Employee> retList = null;//new ArrayList<Employee>();
		while(ois.readObject() != null) {
			retList.add((Employee) ois.readObject());
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
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
		pr.flush();
		
		List<Employee> retList = null;//new ArrayList<Employee>();
		while(ois.readObject() != null) {
			retList.add((Employee) ois.readObject());
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
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
		pr.flush();
		
		List<Customer> retList = null;//new ArrayList<Employee>();
		while(ois.readObject() != null) {
			retList.add((Customer) ois.readObject());
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
		oos.writeObject(i);
		oos.flush();
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
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
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
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
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
		pr.flush();
		
		List<Item> retList = null;//new ArrayList<Customer>();
		while(ois.readObject() != null) {
			retList.add((Item) ois.readObject());
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
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
		pr.flush();
		
		return (Item) ois.readObject();
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
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
		pr.flush();
		
		List<Item> retList = null;//new ArrayList<Employee>();
		while(ois.readObject() != null) {
			retList.add((Item) ois.readObject());
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
		oos.writeObject(c);
		oos.flush();
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
		pr.flush();
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
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
		pr.flush();
		
		List<Changelog> retList = null;//new ArrayList<Employee>();
		while(ois.readObject() != null) {
			retList.add((Changelog) ois.readObject());
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
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
		pr.flush();
		
		List<Changelog> retList = null;//new ArrayList<Customer>();
		while(ois.readObject() != null) {
			retList.add((Changelog) ois.readObject());
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
		pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
		pr.flush();
		
		return (Changelog) ois.readObject();
	}
}
