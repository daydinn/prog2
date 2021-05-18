package Domain;

import java.util.*;

import UserInterface.CUI;
import valueObjects.Cart;
import valueObjects.Changelog;
import valueObjects.Customer;
import valueObjects.Employee;
import valueObjects.Item;
import valueObjects.Storage;

public class Eshop {

	private static Storage storage;
	//buero
	private static Changelog changelog;
	private static Cart cart;
	private static Item item;
	static List<String> log = new ArrayList<String>();
	private static Employee employee;
	private static Customer customer;

	public static void main(String args[]) {
		CUI.main(args);
	}
	
}
