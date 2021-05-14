package Domain;
import java.util.*;
import valueObjects.*;


public class UserManager {

private List<Employee> employeeStock = new ArrayList <Employee>();
private List<Customer> customerStock = new ArrayList<Customer>();









public void add(Employee e) {
	
employeeStock.add(e);
	
}
//füge einen Mitarbeiter hinzu
public void add(Customer c) {
	
customerStock.add(c);	
	
}

//löscht einen Mitarbeiter
public void edelete (int eNumber) {

Iterator<Employee> iter = employeeStock.iterator();
while(iter.hasNext()) {
Employee e = iter.next();
if(e.getEmployeeNr() ==eNumber) {
iter.remove();
}
}
}
//löscht einen Kunden
public void cdelete (int cNumber) {

Iterator<Customer> iter = customerStock.iterator();
while(iter.hasNext()) {
Customer c = iter.next();
if(c.getCustomerNr() ==cNumber) {
iter.remove();
}
}
}
//durchsucht die Mitarbeiterlise nach der NR
public List<Employee> searchEmployeeNr (int number){
	
List<Employee>searchResult = new ArrayList<Employee>();
Iterator<Employee> iter = employeeStock.iterator();

while(iter.hasNext()) {
Employee e = iter.next();
if(e.getEmployeeNr() == number) {
searchResult.add(e);
}
}
return searchResult;
	
	
}

//durchsucht die Kundenliste nach einer NR
public List<Customer> searchCustomerNr (int number){
	
List<Customer>searchResult = new ArrayList<Customer>();
Iterator<Customer> iter = customerStock.iterator();

while(iter.hasNext()) {
Customer c = iter.next();
if(c.getCustomerNr() == number) {
searchResult.add(c);
}
}
return searchResult;
	
	
}

//gibt den Mitarbeiterbestand aus als Liste

public List<Employee> getEmployeeStock(){

return new ArrayList<Employee>	(employeeStock);

}
//gibt den Kundenbestand aus als Liste

public List<Customer> getCustomerStock(){
	

return new ArrayList<Customer>(customerStock);

}

}
