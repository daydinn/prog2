package Domain;

import java.io.IOException;

import java.util.*;

import Exceptions.InvalidNameChangelogException;
import Persistence.FilePersistenceManager;
import Persistence.PersistenceManager;
import Valueobjects.Changelog;
import Valueobjects.Customer;
import Valueobjects.Employee;
import Valueobjects.Item;

/**
 * 
 * Description: is the class for all user functions, has the lists for users and employees and an persistencemanager object to read and write.
 * Used by: EmployeeManagement,CustomerManagement
 *
 */
public class UserManager {

  private List < Employee > employeeStock = new ArrayList < Employee > ();
  private List < Customer > customerStock = new ArrayList < Customer > ();
  private PersistenceManager pm = new FilePersistenceManager();

  /**
   * Description: calls Persistencemanager to read the file in which the customers are saved
   * @param datei
   * @throws IOException
   */
  public void readCustomers(String datei) throws IOException {

    pm.openForReading(datei);
    Customer c;

    do {
      c = pm.loadCustomer();
      if (c != null) {
        add(c);
      }
    } while (c != null);

  }

  /**
   * Description: calls Persistencemanager to write  customers to the file
   * @param datei
   * @throws IOException
   */
  public void writeCustomers(String datei) throws IOException {

    pm.openForWriting(datei);

    for (Customer c: customerStock) {
      pm.saveCustomer(c);
    }

    pm.close();

  }

  /**
   * Description: calls Persistencemanager to read a file in which the employees are saved
   * @param datei
   * @throws IOException
   */
  public void readEmployees(String datei) throws IOException {

    pm.openForReading(datei);
    Employee e;

    do {
      e = pm.loadEmployee();
      if (e != null) {
        add(e);
      }
    } while (e != null);

  }

  /**
   * Description: calls Persistencemanager to write employees to the file
   * @param datei
   * @throws IOException
   */
  public void writeEmployees(String datei) throws IOException {

    pm.openForWriting(datei);

    for (Employee e: employeeStock) {
      pm.saveEmployee(e);
    }

    pm.close();

  }

  /**
   * Description: adds a customer to the Customer Stock
   * @param k
   */
  public void add(Customer c) {
    customerStock.add(c);
  }

  /**
   *Description: deletes a customer
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
   * Description: add an employee to the Employee stock
   * @param m
   */
  public void add(Employee e) {
    employeeStock.add(e);
  }

  /**
   *Description: deletes an employee
   * @param eNumber
   */
  public void edeleting(int eNumber) {
    Iterator < Employee > iter = employeeStock.iterator();
    while (iter.hasNext()) {
      Employee e = iter.next();
      if (e.getEmployeeNr() == eNumber) {
        iter.remove();
      }
    }
  }

  /**
   * Description: searches in the customer list for a NR
   * @param nr
   * @return searchResult;
   */
  public List < Customer > searchCustomerNr(int nr) {

    List < Customer > searchResult = new ArrayList < Customer > ();
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
   * Description: searches in the employee list for a NR
   * @param nr
   * @return searchResult;
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
   * Description: searches in the customer list for a Name
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
   * Description: searches in the employee list for a name and returns the results
   * @param name
   * @return searchResult
   */
  public List < Employee > searchEmployeeName(String name) {

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
   * Description: outputs  all employees as a Arraylist
   * @return employeeStock
   */
  public List < Employee > getEmployeeStock() {
    return new ArrayList < Employee > (employeeStock);
  }

  /**
   * Description: outputs all customers as a Arraylist
   * @return customerStocks
   */
  public List < Customer > getCustomerStock() {
    return new ArrayList < Customer > (customerStock);
  }

}