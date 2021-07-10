package valueObjects;

import java.io.IOException;

import java.util.List;

import Domain.UserManager;
/**
 * Description: Is the connection point between the GUI and the Usermanager, uses the file in the employee's stand.
 * 
 * @param datei
 * @throws IOException
 */

public class EmployeeManagement {  

  private String datei = "";
  private UserManager userManager;

  public EmployeeManagement(String datei) throws IOException {
    this.datei = datei;
    userManager = new UserManager();
    userManager.readEmployees(datei + "-Textfile");
  }

  /**
   * Description: calls the user manager, then it writes employees into the file,so it is saved.
   * @throws IOException
   */
  public void writeEmployees() throws IOException {
	  userManager.writeEmployees(datei + "-Textfile");
  }

  
  /**
   * Description: gives the number parameter to the user manager so that the number can be deleted
   * @param employeeNr
   */
  public void deleteEmployee(int employeeNr) {
	  userManager.edeleting(employeeNr);
  }

  /**
   * Description: gives a  number parameter to the user manager with which an employee can be searched for
   * @param nr
   * @return returns the list of employees with the searched number
   */
  public List < Employee > searchByNumber(int nr) {
    return userManager.searchEmployeeNr(nr);
  }
 
  
  /**
   * Description: gives a name parameter with which an employee can be searched for
   * @param name
   * @return returns the list of employees with the searched name
   */
  
 public List < Employee > searchByName(String name) {
 return userManager.searchEmployeeName(name);
 }
  
  
  
  
  

  /**
   * Description: calls the UserManager and it returns a list of all employees
   * @return the list of employees
   */
  public List < Employee > getAllEmployees() {
    return userManager.getEmployeeStock();
  }

  /**
   * Description: add a new employee with the given parameters and forwards the new employee to the user manager
   * @return e
   */
  public Employee addAnEmployee(String username, String password, String firstname, String lastname, String email, int employeeNr) {
   Employee e = new Employee(username, password, firstname, lastname, email, employeeNr);
    userManager.add(e);
    return e;
  }

}
