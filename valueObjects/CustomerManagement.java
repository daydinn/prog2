package valueObjects;

import java.io.IOException;
import java.util.*;

import Domain.*;

public class CustomerManagement{
	
//CustomerManagement ist die Schnittstelle zwischen PersonenManager und der CUI/GUI.Er kümmer sich um die kunden
	
	
private String data = "";
private UserManager userManager;

public CustomerManagement(String data) throws IOException{
this.data = data;
userManager = new UserManager();
userManager.readCustomer(data+"S.txt");

}

//ruft den Personenmanager auf und lässt sich den aktuellen Kundenbestand geben
public List<Customer> getAllCustomer(){
return userManager.getCustomerStock();
}


//übergibt den Personenmanager die Kundennummer, diese gibt eine Liste zurück mit der Person und Nummer

public List<Customer> searchByNumber(int nr){
return userManager.searchCustomerNr(nr);
}

//Erstellt einen Kunden und übergibt diesen zum Personenmanager.Dieser fügt den Kunden zum Bestand hinzu
public Customer addCustomer(String firstname,String lastname, String password, String adress,int customerNr ) {
Customer c = new Customer(firstname,lastname,password,customerNr,adress);
userManager.addCustomer(c);
return c;
}

//übergibt den personenmanger die nummer.Dieser löscht den Kunden mit der Nummer

public void deleteCustomer(int number) {
userManager.cdelete(number);

}

//Lässt den personenManager den Kundenbestand speichern

public void writeCustomer() throws IOException{
userManager.writeCustomer(data+"S.txt");
}


}
