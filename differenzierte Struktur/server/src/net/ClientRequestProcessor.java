package net;

import java.io.IOException;
import java.util.*;
import valueObjects.*;

public class ClientRequestProcessor {
	 private Server server;
	 private static domain.Eshop eshop;
	 
	 public ClientRequestProcessor() throws IOException {
		 server = new Server(4999);
		 eshop = new domain.Eshop();
	 }
	 
	 public static void main(String args[]) throws IOException, NumberFormatException, ClassNotFoundException {
		 ClientRequestProcessor crp = new ClientRequestProcessor();
		 
		 while(true) {
			 switch(crp.server.bf.readLine()) {
			 	case "add customer":{
			 		int CustomerNr = Integer.parseInt(crp.server.bf.readLine());
					String Username = crp.server.bf.readLine();
					String Password = crp.server.bf.readLine();
					String Firstname = crp.server.bf.readLine();
					String Lastname = crp.server.bf.readLine();
					String Adress = crp.server.bf.readLine();
					
					eshop.add(new Customer(Username, Password, Firstname, Lastname, Adress, CustomerNr));
			 	}
			 	break;
			 	
			 	case "delete customer":
			 		eshop.cdeleting(Integer.parseInt(crp.server.bf.readLine()));
			 	break;
			 	
			 	case "add employee":{
			 		int EmployeeNr = Integer.parseInt(crp.server.bf.readLine());
					String Username = crp.server.bf.readLine();
					String Password = crp.server.bf.readLine();
					String Firstname = crp.server.bf.readLine();
					String Lastname = crp.server.bf.readLine();
					String Email = crp.server.bf.readLine();
					
					eshop.add(new Employee(Username, Password, Firstname, Lastname, Email, EmployeeNr));
			 	}
			 	break;
			 	
			 	case "delete employee":
			 		eshop.edeleting(Integer.parseInt(crp.server.bf.readLine()));
			 	break;
			 	
			 	case "search customer number":
			 		Customer resultC = eshop.searchCustomerNr(Integer.parseInt(crp.server.bf.readLine()));
			 		crp.server.pr.println(resultC.getCustomerNr());
			 		crp.server.pr.flush();
			 		crp.server.pr.println(resultC.getUsername());
			 		crp.server.pr.flush();
			 		crp.server.pr.println(resultC.getFirstname());
			 		crp.server.pr.flush();
			 		crp.server.pr.println(resultC.getLastname());
			 		crp.server.pr.flush();
			 		crp.server.pr.println(resultC.getAdress());
			 		crp.server.pr.flush();
			 	break;
			 	
			 	case "search employee number":
			 		Employee resultE = eshop.searchEmployeeNr(Integer.parseInt(crp.server.bf.readLine()));
			 		crp.server.pr.println(resultE.getEmployeeNr());
			 		crp.server.pr.flush();
			 		crp.server.pr.println(resultE.getUsername());
			 		crp.server.pr.flush();
			 		crp.server.pr.println(resultE.getFirstname());
			 		crp.server.pr.flush();
			 		crp.server.pr.println(resultE.getLastname());
			 		crp.server.pr.flush();
			 		crp.server.pr.println(resultE.getEmail());
			 		crp.server.pr.flush();
			 	break;
			 	
			 	case "search customer name":
			 		List<Customer> resultCList = eshop.searchCustomerName(crp.server.bf.readLine());
			 		for(Customer c : resultCList) {
				 		crp.server.pr.println(c.getCustomerNr());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(c.getUsername());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(c.getFirstname());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(c.getLastname());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(c.getAdress());
				 		crp.server.pr.flush();
			 		}

			 		crp.server.pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
			 		crp.server.pr.flush();
			 	break;
			 	case "search employee name":
			 		List<Employee> resultEList = eshop.searchEmployeeName(crp.server.bf.readLine());
			 		for(Employee e : resultEList) {
				 		crp.server.pr.println(e.getEmployeeNr());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(e.getUsername());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(e.getFirstname());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(e.getLastname());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(e.getEmail());
				 		crp.server.pr.flush();
			 		}

			 		crp.server.pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
			 		crp.server.pr.flush();
			 	break;
			 	case "search customer all":
			 		List<Customer> resultCListAll = eshop.getCustomerStock();
			 		for(Customer c : resultCListAll) {
				 		crp.server.pr.println(c.getCustomerNr());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(c.getUsername());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(c.getFirstname());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(c.getLastname());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(c.getAdress());
				 		crp.server.pr.flush();
			 		}

			 		crp.server.pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
			 		crp.server.pr.flush();
			 	break;
			 	case "search employee all":
			 		List<Employee> resultEListAll = eshop.getEmployeeStock();
			 		for(Employee e : resultEListAll) {
				 		crp.server.pr.println(e.getEmployeeNr());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(e.getUsername());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(e.getFirstname());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(e.getLastname());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(e.getEmail());
				 		crp.server.pr.flush();
			 		}

			 		crp.server.pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
			 		crp.server.pr.flush();
			 	break;
			 	case "add item":
			 		String iname = crp.server.bf.readLine();
					int number = Integer.parseInt(crp.server.bf.readLine());
					double price = Double.parseDouble(crp.server.bf.readLine());
					int stock = Integer.parseInt(crp.server.bf.readLine());
					int minimumStock = Integer.parseInt(crp.server.bf.readLine());
					int bulk = Integer.parseInt(crp.server.bf.readLine());
					
					eshop.add(new Item(iname, number, price, stock, minimumStock, bulk));
			 	break;
			 	case "delete item":
			 		eshop.ideleting(Integer.parseInt(crp.server.bf.readLine()));
			 	break;
			 	case "search item number":
			 		Item resultI = eshop.searchItemNr(Integer.parseInt(crp.server.bf.readLine()));
			 		crp.server.pr.println(resultI.getName());
			 		crp.server.pr.flush();
			 		crp.server.pr.println(resultI.getNumber());
			 		crp.server.pr.flush();
			 		crp.server.pr.println(resultI.getPrice());
			 		crp.server.pr.flush();
			 		crp.server.pr.println(resultI.getStock());
			 		crp.server.pr.flush();
			 		crp.server.pr.println(resultI.getminimumStock());
			 		crp.server.pr.flush();
			 		crp.server.pr.println(resultI.getBulk());
			 		crp.server.pr.flush();
			 	break;
			 	case "search item name":
			 		List<Item> resultIList = eshop.searchItemName(crp.server.bf.readLine());
			 		for(Item i : resultIList) {
				 		crp.server.pr.println(i.getName());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(i.getNumber());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(i.getPrice());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(i.getStock());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(i.getminimumStock());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(i.getBulk());
				 		crp.server.pr.flush();
			 		}

			 		crp.server.pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
			 		crp.server.pr.flush();
			 	break;
			 	case "search item all":
			 		List<Item> resultIListAll = eshop.getItemStock();
			 		for(Item i : resultIListAll) {
				 		crp.server.pr.println(i.getName());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(i.getNumber());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(i.getPrice());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(i.getStock());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(i.getminimumStock());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(i.getBulk());
				 		crp.server.pr.flush();
			 		}

			 		crp.server.pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
			 		crp.server.pr.flush();
			 	break;
			 	case "add changelog":
			 		boolean type = Boolean.parseBoolean(crp.server.bf.readLine());
			 		String message = crp.server.bf.readLine();
			 		String time = crp.server.bf.readLine();
			 		
			 		if(type) {
			 			int EmployeeNr = Integer.parseInt(crp.server.bf.readLine());
						String Username = crp.server.bf.readLine();
						String Password = crp.server.bf.readLine();
						String Firstname = crp.server.bf.readLine();
						String Lastname = crp.server.bf.readLine();
						String Email = crp.server.bf.readLine();
						
						Employee e = new Employee(Username, Password, Firstname, Lastname, Email, EmployeeNr);
						
						eshop.add(new Changelog(e, message, type, time));
			 		}else {
			 			int CustomerNr = Integer.parseInt(crp.server.bf.readLine());
						String Username = crp.server.bf.readLine();
						String Password = crp.server.bf.readLine();
						String Firstname = crp.server.bf.readLine();
						String Lastname = crp.server.bf.readLine();
						String Adress = crp.server.bf.readLine();
						
						Customer c = new Customer(Username, Password, Firstname, Lastname, Adress, CustomerNr);
						
						eshop.add(new Changelog(c, message, type, time));
			 		}
			 	break;
			 	case "search changelog all":
			 		List<Changelog> resultClListAll = eshop.getChangelog();
			 		for(Changelog c : resultClListAll) {
				 		
				 		crp.server.pr.println(c.getTyp());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(c.getMessage());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(c.getTime());
				 		crp.server.pr.flush();
						
						if(c.getTyp()) {
							crp.server.pr.println(c.getEmployee().getEmployeeNr());
							crp.server.pr.flush();
							crp.server.pr.println(c.getEmployee().getUsername());
							crp.server.pr.flush();
							crp.server.pr.println(c.getEmployee().getPassword());
							crp.server.pr.flush();
							crp.server.pr.println(c.getEmployee().getFirstname());
							crp.server.pr.flush();
							crp.server.pr.println(c.getEmployee().getLastname());
							crp.server.pr.flush();
							crp.server.pr.println(c.getEmployee().getEmail());
							crp.server.pr.flush();
						}else {
							crp.server.pr.println(c.getCustomer().getCustomerNr());
							crp.server.pr.flush();
							crp.server.pr.println(c.getCustomer().getUsername());
							crp.server.pr.flush();
							crp.server.pr.println(c.getCustomer().getPassword());
							crp.server.pr.flush();
							crp.server.pr.println(c.getCustomer().getFirstname());
							crp.server.pr.flush();
							crp.server.pr.println(c.getCustomer().getLastname());
							crp.server.pr.flush();
							crp.server.pr.println(c.getCustomer().getAdress());
							crp.server.pr.flush();
						}
				 		crp.server.pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
				 		crp.server.pr.flush();
					}
			 	break;
			 	case "search changelog name":
			 		List<Changelog> resultClList = eshop.searchChangelogName(crp.server.bf.readLine());
			 		for(Changelog c : resultClList) {
				 		
				 		crp.server.pr.println(c.getTyp());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(c.getMessage());
				 		crp.server.pr.flush();
				 		crp.server.pr.println(c.getTime());
				 		crp.server.pr.flush();
						
						if(c.getTyp()) {
							crp.server.pr.println(c.getEmployee().getEmployeeNr());
							crp.server.pr.flush();
							crp.server.pr.println(c.getEmployee().getUsername());
							crp.server.pr.flush();
							crp.server.pr.println(c.getEmployee().getPassword());
							crp.server.pr.flush();
							crp.server.pr.println(c.getEmployee().getFirstname());
							crp.server.pr.flush();
							crp.server.pr.println(c.getEmployee().getLastname());
							crp.server.pr.flush();
							crp.server.pr.println(c.getEmployee().getEmail());
							crp.server.pr.flush();
						}else {
							crp.server.pr.println(c.getCustomer().getCustomerNr());
							crp.server.pr.flush();
							crp.server.pr.println(c.getCustomer().getUsername());
							crp.server.pr.flush();
							crp.server.pr.println(c.getCustomer().getPassword());
							crp.server.pr.flush();
							crp.server.pr.println(c.getCustomer().getFirstname());
							crp.server.pr.flush();
							crp.server.pr.println(c.getCustomer().getLastname());
							crp.server.pr.flush();
							crp.server.pr.println(c.getCustomer().getAdress());
							crp.server.pr.flush();
						}
				 		crp.server.pr.println("\u00EB"); //character that's not on most english/german keyboards to signify end of input
				 		crp.server.pr.flush();
					}
			 	break;
			 	case "search changelog number":
			 		Changelog c = eshop.searchChangelogNr(Integer.parseInt(crp.server.bf.readLine()));
			 		
			 		crp.server.pr.println(c.getTyp());
			 		crp.server.pr.flush();
			 		crp.server.pr.println(c.getMessage());
			 		crp.server.pr.flush();
			 		crp.server.pr.println(c.getTime());
			 		crp.server.pr.flush();
					
					if(c.getTyp()) {
						crp.server.pr.println(c.getEmployee().getEmployeeNr());
						crp.server.pr.flush();
						crp.server.pr.println(c.getEmployee().getUsername());
						crp.server.pr.flush();
						crp.server.pr.println(c.getEmployee().getPassword());
						crp.server.pr.flush();
						crp.server.pr.println(c.getEmployee().getFirstname());
						crp.server.pr.flush();
						crp.server.pr.println(c.getEmployee().getLastname());
						crp.server.pr.flush();
						crp.server.pr.println(c.getEmployee().getEmail());
						crp.server.pr.flush();
					}else {
						crp.server.pr.println(c.getCustomer().getCustomerNr());
						crp.server.pr.flush();
						crp.server.pr.println(c.getCustomer().getUsername());
						crp.server.pr.flush();
						crp.server.pr.println(c.getCustomer().getPassword());
						crp.server.pr.flush();
						crp.server.pr.println(c.getCustomer().getFirstname());
						crp.server.pr.flush();
						crp.server.pr.println(c.getCustomer().getLastname());
						crp.server.pr.flush();
						crp.server.pr.println(c.getCustomer().getAdress());
						crp.server.pr.flush();
					}
			 	break;
			 }
		 }
	 }
}
