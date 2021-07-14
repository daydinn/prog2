package Valueobjects;

public class Employee {

  private int EmployeeNr;
  private String username;
  private String password;
  private String firstname;
  private String lastname;
  private String email;

  /**
   * 
   * @param username
   * @param password
   * @param firstname
   * @param lastname
   * @param email
   * @param EmployeeNr
   */
  public Employee(String username, String password, String firstname, String lastname, String email, int EmployeeNr) {

    this.EmployeeNr = EmployeeNr;
    this.username = username;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;

    //Getter and Setter

  }
  public int getEmployeeNr() {
    return EmployeeNr;
  }

  public void setEmployeeNr(int EmployeeNr) {
    this.EmployeeNr = EmployeeNr;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
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

  public void setNachname(String lastname) {
    this.lastname = lastname;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * toString methode um einen MItarbeiter in der Console auszugeben
   */
  public String toString() {
    return ("Firstname: " + username + " | Password: " + password + " | Firstname: " + firstname + " | Lastname: " + lastname + " | Email: " + email + " |   EmployeeNr: " + EmployeeNr);
  }
}