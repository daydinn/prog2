package valueObjects;
import Domain.UserManager;
import java.util.*;

public class UserManagement {     //Benutzerverwaltung &Buero
	
	
// Beschreibung : Ist die Schnittstelle zwischen der GUI/CUI und dem UserManager
	
private UserManager userManager;

	
	
//Beschreibung: Gibt die Personen zurück, mit der angegeben Mitarbeiter/Kunden Nummer

public List<Employee> getAllEmployee(){
	
return userManager.getEmployeeStock();

}

public List<Customer> getAllCustomer(){
	
return userManager.getCustomerStock();

}



//Beschreibung: fügt einen neuen Mitarbeiter ein, gibt den Mitarbeiter an den PersonenManager weiter
	
public Employee addEmployee(String firstname, String lastname,String password,int employeeNr) {

Employee e = new Employee(firstname,lastname,password,employeeNr);
userManager.add(e);
return e;
}


//Beschreibung: fügt einen neuen Kunden ein, gibt den Kunden an den PersonenManager weiter

public Customer addCustomer(String firstname, String lastname,String password,int customerNr,String adress) {

Customer c = new Customer(firstname,lastname,password,customerNr,adress);
userManager.add(c);
return c;
}







//Beschreibung:Übergibt dem personenenmanager die Nummer, der löscht den Mitarbeiter mit der Nummer
public void deleteEmployee(int employeeNr) {
userManager.edelete(employeeNr);
}

//Beschreibung:Übergibt dem personenenmanager die Nummer, der löscht den Mitarbeiter mit der Nummer

public void deleteCustomer(int customerNr) {
userManager.cdelete(customerNr);
}
	
	
}







