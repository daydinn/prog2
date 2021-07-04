package Persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Valueobjects.Changelog;
import Valueobjects.Customer;
import Valueobjects.Employee;
import Valueobjects.Item;


public class FilePersistenceManager implements PersistenceManager {

	private BufferedReader reader = null;
	private PrintWriter writer = null;
	
	
	
	/**
	 * creates a buffered reder that can read something from a file
	 */
	@Override
	public void openForReading(String file) throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(file));
	}
	/**
	 * creates a buffered writer that can write something to a file
	 */
	@Override
	public void openForWriting(String file)  throws IOException {
		writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
	}
	/**
	 * schlieﬂt den buffered Reader und writer
	 */
	@Override
	public boolean close() {
		if (writer != null) {
			writer.close();
		}
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
}
	
	
	/**
	 * function to load a Item
	 */
	@Override
	public Item loadItems() throws IOException {
		//reads Name
		String name = readLine();
		if(name == null) {
			return null;
		}
			//reads Item number
		String numberString = "";
		try {
			numberString = readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int number = Integer.parseInt(numberString);
	
		//reads the price of Item
		String priceString = "";
		try {
			priceString = readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double price = Double.parseDouble(priceString);
	
		//reads stock
		String stockString = "";
		try {
			stockString = readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int stock = Integer.parseInt(stockString);
	
		//reads minimumstock
		String minimumStockString = "";
		try {
			minimumStockString = readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int minimumStock = Integer.parseInt(minimumStockString);
		
		//reads bulk
		String bulkString = "";
		try {
			bulkString = readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int bulk = Integer.parseInt(bulkString);
	
	
		return new Item(name, number, price, stock, minimumStock, bulk);
	
	}
	
	
	
	/**
	 * function to load an employee
	 */
	@Override
	public Employee loadEmployee() throws IOException {
		//reads username
		String username = readLine();
		if(username == null) {
			return null;
		}
		
		//reads password
		String password = readLine();
		if(password == null) {
			return null;
		}
		
		//reads firstname
		String firstname = readLine();
		if(firstname == null) {
			return null;
		}
		 //reads lastname
		String lastname = readLine();
		if(lastname == null) {
			return null;
		}
		
		//reads the adress
		String email = readLine();
		if(email == null) {
			return null;
		}
		
		
		
		//reads employee nr
				String employeeNrString = "";
				try {
					employeeNrString = readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				int employeeNr = Integer.parseInt(employeeNrString);
		
		return new Employee(username, password, firstname, lastname, email,employeeNr);
	
	}
	
	
	
	/**
	 * funktion to load a customer
	 */
	@Override
	public Customer loadCustomer() throws IOException {
		//reads firstname
		String username = readLine();
		if(username == null) {
			return null;
		}
		
		//reads password
		String password = readLine();
		if(password == null) {
			return null;
		}
		
		//reads firstname
		String firstname = readLine();
		if(firstname == null) {
			return null;
		}
		 //reads lastname
		String lastname = readLine();
		if(lastname == null) {
			return null;
		}
		
		//reads adress
		String adress = readLine();
		if(adress == null) {
			return null;
		}
		
		
		
		//reads employee nr
				String customerNrString = "";
				try {
					customerNrString = readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				int customerNr = Integer.parseInt(customerNrString);
		
		return new Customer(username, password, firstname, lastname, adress,customerNr);
	
	}
	
	
	/**
	 * function for saving/writing an Item
	 */
	@Override
	public boolean saveItems(Item i)  throws IOException {
		writeLine(i.getName());
		writeLine(i.getNumber()+ "");
		writeLine(i.getPrice()+ "");
		writeLine(i.getStock()+"");
		writeLine(i.getminimumStock()+"");
		writeLine(i.getBulk()+"");
		
		return true;
	}
	/**
	 * function for saving/writing an employee
	 */
	@Override
	public boolean saveEmployee(Employee e)  throws IOException {
		writeLine(e.getUsername());
		writeLine(e.getPassword());
		writeLine(e.getFirstname());
		writeLine(e.getLastname());
		writeLine(e.getEmail());
		writeLine(e.getEmployeeNr()+"");
		
		return true;
	}
	
	/**
	 * function for saving/writing a customer
	 */
	@Override
	public boolean saveCustomer(Customer c)  throws IOException {
		writeLine(c.getUsername());
		writeLine(c.getPassword());
		writeLine(c.getFirstname());
		writeLine(c.getLastname());
		writeLine(c.getAdress());
		writeLine(c.getCustomerNr()+"");
		
		return true;
	}
	
	/**
	 * funktion for saving/writing a log
	 */
	@Override
	public boolean saveLog(String log) throws IOException {
		
		writeLine(log);
		return true;
	}
	
	/**
	 * function for reading a log
	 */
	@Override
	public String readLog(){

		String input = "";

		try {
			input = readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return input;
	}
	
	/**
	 * reads a line in a text file and returns it
	 * @return
	 * @throws IOException
	 */
	private String readLine() throws IOException {
		if(reader != null) {
			return reader.readLine();
		} else {
			return "";
		}
	}
	/**
	 * writes a line in a text file
	 * @param daten
	 */
	private void writeLine(String data) {
		if(writer != null) {
			writer.println(data);
		}
	}
	
	/**
	 * loads the changelog, reads it out line by line and thereby builds the changelog
	 */
	public Changelog loadChangelogNew() throws IOException{
		
		//einlesen des Usernamens
		String username = readLine();
		if(username == null) {
			return null;
		}
				
		//einlesen des passworts
		String password = readLine();
		if(password == null) {
			return null;
		}
		
		//einlesen des vornamens
		String firstname = readLine();
		if(firstname == null) {
			return null;
		}
		 //einlesen des nachnamen
		String lastname = readLine();
		if(lastname == null) {
			return null;
		}
		
		//einelsen des wohnorts
		String adress = readLine();
		if(adress == null) {
			return null;
		}
				
		
				
		//einlesen der Nr
		String nrString = readLine();
		int nr = Integer.parseInt(nrString);
				
		//einelesen der Nachricht
		String message = readLine();
		if(message == null) {
			return null;
		}
		
		//einelsen des Typs
		String typString = readLine();
		boolean typ = Boolean.parseBoolean(typString);
		
		//einlesen der Zeit
		String Time = readLine();
		if(Time == null) {
			return null;
		}
		
		
		
		
		if(typ) {
			Employee e = new Employee(username, password, firstname, lastname, adress,nr);
			return new Changelog(e, message, typ, Time);
		} else {
			Customer c = new Customer(username, password, firstname, lastname, adress,nr);
			return new Changelog(c, message, typ, Time);
		} 
		
		
		
	}

	/**
	 * save/write the changelog line by line
	 */
	public boolean saveChangelog(Changelog c) {
		if(c.getTyp()) {
			writeLine(c.getEmployee().getFirstname());
			writeLine(c.getEmployee().getPassword());
			writeLine(c.getEmployee().getFirstname());
			writeLine(c.getEmployee().getLastname());
			writeLine(c.getEmployee().getEmail());
			writeLine(c.getEmployee().getEmployeeNr()+"");
			writeLine(c.getMessage());
			System.out.println(c.getMessage());
			writeLine(c.getTyp()+"");
			writeLine(c.getTime());
		} else {
			writeLine(c.getCustomer().getUsername());
			writeLine(c.getCustomer().getPassword());
			writeLine(c.getCustomer().getFirstname());
			writeLine(c.getCustomer().getLastname());
			writeLine(c.getCustomer().getAdress());
			writeLine(c.getCustomer().getCustomerNr()+"");
			writeLine(c.getMessage());
			System.out.println(c.getMessage() );
			writeLine(c.getTyp()+"");
			writeLine(c.getTime());
		}
		
		return true;
	}
	

	
	
}
