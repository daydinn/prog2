package valueObjects;

import java.io.IOException;

import java.util.List;

import Domain.UserManager;

public class CustomerManagement { //klasse f�r die kunden

  private String datei = "";
  private UserManager userManager;

  /**
   * Customermanagement is the connection point  between the person manager and the GUI. He takes care of the customers
   * @throws IOException
   */

  public CustomerManagement(String datei) throws IOException {
    this.datei = datei;
   userManager = new UserManager();
   userManager.readCustomers(datei + "-Textfile");
  }

  /**
   * Description: calls the user manager, then it writes customers into the file,so it is saved.
   * @throws IOException
   */
  public void writeCustomers() throws IOException {
    userManager.writeCustomers(datei + "-Textfile");
  }

  /**
   * Description: gives the number to the user manager so that the number can be deleted.
   * @param nummer
   */
  public void deleteCustomer(int number) {
   userManager.cdeleting(number);
  }

  /**
   * Description: gives a  number with which an customer can be searched for
   * @param nr
   * @return returns the list of customers with the searched number
   */
  public List < Customer > searchByNumber(int nr) {
    return userManager.searchCustomerNr(nr);
  }
  
  
  /**
   * Description: gives a name parameter with which an customer can be searched for
   * @param name
   * @return returns the list of customers with the searched name
   */
  
 public List < Customer > searchByName(String name) {
 return userManager.searchCustomerName(name);
 }
  
  
  
  
  
  

  /**
   * Description: calls the UserManager and it returns a list of all customers
   * @return the list of customer
   */
  public List < Customer > getAllCustomers() {
    return userManager.getCustomerStock();
  }

  /**
   * Description: add a new customer with the given parameters and forwards the new employee to the user manager.
   * 
   * @return c
   */
  public Customer addACustomer(String username, String password, String firstname, String lastname, String adress, int customerNr) {
    Customer c = new Customer(username, password, firstname, lastname, adress, customerNr);
    userManager.add(c);
    return c;
  }

}