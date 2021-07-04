package Valueobjects;

public class Customer {

  private int CustomerNr;
  private String username;
  private String password;
  private String firstname;
  private String lastname;
  private String adress;

  public Customer(String username, String password, String firstname, String lastname, String adress, int CustomerNr) {
   
    this.CustomerNr = CustomerNr;
    this.username = username;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
    this.adress = adress;

  
  
  //Getter and Setter
  
  
  }
  public int getCustomerNr() {
    return CustomerNr;
  }

  public void setCustomerNr(int CustomerNr) {
    this.CustomerNr = CustomerNr;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAdress() {
    return adress;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public void setAdress(String adress) {
    this.adress = adress;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Beschreibung: This method specifies how a customer should be output as a string
   */
  public String toString() {
    return ("Username: " + username + " | Password: " + password + " | Firstname: " + firstname + " | Lastname: " + lastname + " | Adress: " + adress + "  | CustomerNr: " + CustomerNr);
  }

}