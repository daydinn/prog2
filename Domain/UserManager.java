package Domain;
import java.io.IOException;
import java.util.*;

import Persistance.*;
import valueObjects.*;



public class UserManager {

private List<Employee> employeeStock = new ArrayList <Employee>();
private List<Customer> customerStock = new ArrayList<Customer>();
private PersistenceManager pm = new FilePersistenceManager();



//list die Datei aus in der die Mitarbeiter stehen


public void readMitarbeiter(String data) throws IOException{
pm.openForReading(data);
Employee e;

do {
e = pm.loadEmployee();
if(e != null) {
addEmployee(e);
}
}while(e != null);

	
}
//list die Datei aus in der die Kunden stehen

public void readCustomer(String data) throws IOException{
pm.openForReading(data);
Customer c;

do {
c = pm.loadCustomer();
if(c != null) {
addCustomer(c);
}
}while(c != null);
}



// schreibt die Mitarbeiter in die Datei
public void writeEmployee(String data) throws IOException{
pm.openForWriting(data);

for( Employee e : employeeStock) {
pm.saveEmployee(e);
}
pm.close();	
}

//schreibt die Kunden in die Datei
public void writeCustomer(String data) throws IOException{
pm.openForWriting(data);

for( Customer c : customerStock) {
pm.saveCustomer(c);
}
pm.close();	
}




//füge einen Mitarbeiter hinzu
public void addEmployee(Employee e) {
	
employeeStock.add(e);
	
}
//füge einen Kunden hinzu
public void addCustomer(Customer c) {
	
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
