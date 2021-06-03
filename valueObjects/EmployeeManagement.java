package valueObjects;

import java.io.IOException;
import java.util.List;
import Domain.UserManager;

public class EmployeeManagement {
	
	
//EmployeeManagement ist die Schnittstelle zwischen PersonenManager und der CUI/GUI. Er kümmer sich um die Mitarbeiter
//übergibt die datei, in der Mitarbeiter stehen


private String data = "";
private UserManager usermanager;

public EmployeeManagement(String data) throws IOException{
this.data = data;
usermanager = new UserManager();
usermanager.readMitarbeiter(data+"L.txt");
}

//gibt eine Liste mit allen Mitarbeitern zurück

public List<Employee> getAllEmployee(){
return usermanager.getEmployeeStock();
}



//Übergibt die Nummer, nach der ein Mitarbeiter gesucht werden soll

public List<Employee> searchByNumber(int nr){
return usermanager.searchEmployeeNr(nr);
}



//fügt einen neuen Mitarbeiter ein, gibt den Mitarbeiter an den Usermanager weiter


public Employee addEmployee(String firstname,String lastname,String password,int employeeNr) {
Employee e = new Employee(firstname,lastname,password,employeeNr);
usermanager.addEmployee(e);
return e;
}

//löscht einen Mitarbeiter nach der MitarbeiterNumber

public void deleteEmployee(int employeeNr) {
usermanager.edelete(employeeNr);
}


//lässt den userManager in die Mitarbeiterliste speichern

public void writeEmployee() throws IOException{
usermanager.writeEmployee(data+"L.txt");
}

}
