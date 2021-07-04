package domain;

import java.io.IOException;

import java.util.*;

import Exceptions.InvalidNameChangelogException;
import Persistence.FilePersistenceManager;
import Persistence.PersistenceManager;
import Valueobjects.Changelog;
import Valueobjects.Customer;
import Valueobjects.Employee;
import Valueobjects.Item;

public class UserManager {

  /**
   * Description: is a manager class, which contains both customer and employee methods
   * 
   *
   */

  private List <Employee > employeeStock = new ArrayList < Employee > ();
  private List <Customer > customerStock = new ArrayList <Customer> ();
  private PersistenceManager um = new FilePersistenceManager();

  /**
   *Description: calls Persistencemanager to read the file in which the customers are
   * @param datei
   * @throws IOException
   */
  public void readCustomers(String datei) throws IOException {

	 um.openForReading(datei);
    Customer c;

    do {
      c = um.loadCustomer();
      if (c != null) {
        add(c);
      }
    } while (c != null);

  }
  /**
   *Description: calls Persistencemanager to write the customers to the file
   * @param datei
   * @throws IOException
   */
  
  
  
  public void writeCustomers(String datei) throws IOException {

	  um.openForWriting(datei);

    for (Customer c: customerStock) {
    	um.saveCustomer(c);
    }

    um.close();

  }

  /**
   *Description: calls Persistencemanager to read the file in which the employees are
   * @param datei
   * @throws IOException
   */
  public void readEmployees(String datei) throws IOException {

	  um.openForReading(datei);
    Employee e;

    do {
      e = um.loadEmployee();
      if (e != null) {
        add(e);
      }
    } while (e != null);

  }

  /**
   *Description: calls Persistencemanager to write the employee to the file
   * @param datei
   * @throws IOException
   */
  public void writeEmployees(String datei) throws IOException {

	  um.openForWriting(datei);

    for (Employee e: employeeStock) {
    	um.saveEmployee(e);
    }

    um.close();

  }
 

  /**
   *Description: adds a customer to Customer Stock
   * @param k
   */
  public void add(Customer c) {
   customerStock.add(c);
  }

  /**
   *Description:  deletes a customer, who has the given number
   * @param cNumber
   */
  public void cdeleting(int cNumber) {
    Iterator < Customer > iter = customerStock.iterator();
    while (iter.hasNext()) {
      Customer c = iter.next();
      if (c.getCustomerNr() == cNumber) {
        iter.remove();
      }
    }
  }

  /**
   *Description: add a employee to Employee stock
   * @param m
   */
  public void add(Employee e) {
   employeeStock.add(e);
  }

  /**
   *Description: deletes a employee, who has the given number
   * @param mNummer
   */
  public void edeleting(int eNumber) {
    Iterator < Employee > iter =employeeStock.iterator();
    while (iter.hasNext()) {
      Employee e = iter.next();
      if (e.getEmployeeNr() == eNumber) {
        iter.remove();
      }
    }
  }

  /**
   *Description: searches in the customer list for a NR
   * @param nr
   * @return
   */
  public List < Customer > searchCustomerNr(int nr) {

    List <Customer> searchResult = new ArrayList < Customer > ();
    Iterator < Customer > iter = customerStock.iterator();

    while (iter.hasNext()) {
      Customer c = iter.next();
      if (c.getCustomerNr() == nr) {
    	  searchResult.add(c);
      }
    }
    return searchResult;
  }

  /**
   *Description: searches in the employee list for a NR
   * @param nr
   * @return
   */
  public List < Employee > searchEmployeeNr(int nr) {

    List < Employee > searchResult = new ArrayList < Employee > ();
    Iterator < Employee > iter = employeeStock.iterator();

    while (iter.hasNext()) {
      Employee e = iter.next();
      if (e.getEmployeeNr() == nr) {
    	  searchResult.add(e);
      }
    }
    return searchResult;
  }

 
 /**
   *Description: searches in the customer list for a Name
   * @param name
   * @return searchResult
   */
  
  public List < Customer > searchCustomerName(String name) {

	   List < Customer > searchResult = new ArrayList < Customer > ();
	    Iterator < Customer > iter = customerStock.iterator();

	    while (iter.hasNext()) {
	     Customer c = iter.next();

	      if (c.getFirstname().equals(name)) {
	    	  searchResult.add(c);
	      }

	     }
		return searchResult;
	}

  /**
   *Description: searches in the employee list for a Name
   * @param name
   * @return searchResult
   */
  
  public List < Employee > searchEmployeeName(String name)  {

    List < Employee > searchResult = new ArrayList < Employee > ();
    Iterator < Employee > iter = employeeStock.iterator();

    while (iter.hasNext()) {
     Employee e = iter.next();

      if (e.getFirstname().equals(name)) {
    	  searchResult.add(e);
      }

     

  
  }
	return searchResult;
}
  
  
  
  /**
   *Description: outputs employees as a list
   * @return
   */
  public List < Employee > getEmployeeStock() {
    return new ArrayList < Employee > (employeeStock);
  }
  
  
  
  /**
   *Description: outputs customers as a list
   * @return
   */
  public List < Customer > getCustomerStock() {
    return new ArrayList < Customer > (customerStock);
  }


}