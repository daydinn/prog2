package Domain;
import java.util.*;
import valueObjects.*;


public class UserManager {

private List<Employee> employeeStock = new ArrayList <Employee>();
private List<Customer> customerStock = new ArrayList<Customer>();









public void add(Employee e) {
	
employeeStock.add(e);
	
}

public void add(Customer c) {
	
customerStock.add(c);	
	
}


public void edelete (int eNumber) {

Iterator<Employee> iter = employeeStock.iterator();
while(iter.hasNext()) {
Employee e = iter.next();
if(e.getEmployeeNr() ==eNumber) {
iter.remove();
}
}
}

public void cdelete (int cNumber) {

Iterator<Customer> iter = customerStock.iterator();
while(iter.hasNext()) {
Customer c = iter.next();
if(c.getCustomerNr() ==cNumber) {
iter.remove();
}
}
}








}
