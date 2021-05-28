package Persistance;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import valueObjects.*;

public class FilePersistenceManager implements PersistenceManager {


private BufferedReader reader = null;
private PrintWriter writer = null;
@Override


//erzeugt einen Buffered Reader, der etwas einlesen kann aus einer Datei
public void openForReading(String file) throws IOException {
reader = new BufferedReader(new FileReader(file));
}


//erzeugt einen BufferedWritter der etwas in eine Datei schreiben kann
@Override
public void openForWriting(String file) throws IOException {
writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
}

//Schließt den buffered Reader un writter
@Override
public boolean close() {
if(writer != null) {
writer.close();
}
if(reader != null) {
try {
reader.close();
}catch(IOException e) {
e.printStackTrace();  //eine Methode für die Fehlerdiagnose
return false;
}
}
return true;
}





//funktion zum laden des Items 
@Override
public Item loadArtikel() throws IOException {
//einlesen des Namens
String name= readRow();
if(name == null) {
return null;	
}

//einlesen der Itemnummer	
String numberString= "";
try {
numberString = readRow();
}catch(IOException e) {
e.printStackTrace();
}
int number = Integer.parseInt(numberString); //wandelt einen eingegebenen Wert in eine intZahl um


//Einlesen des Itempreises
String priceString = "";
try {
priceString = readRow();
}catch(IOException e) {
e.printStackTrace();
}
double price = Double.parseDouble(priceString); //

//Einlesen vom Bestand

String stockString = "";
try {
stockString =readRow();
}catch(IOException e) {
e.printStackTrace();
}

int  stock = Integer.parseInt(stockString);

//Einlesen vom mindestBestand

String minimumstockString ="";

try {
minimumstockString = readRow();
}
catch(IOException e) {
e.printStackTrace();
}
int minimumstock = Integer.parseInt(minimumstockString);
//Einlesen vom massengut

String bulkString = "";
try {
bulkString = readRow();
}
catch(IOException e) {
e.printStackTrace();

}
int bulk = Integer.parseInt(bulkString);

return new Item(name,number,price,stock,minimumstock,bulk);

}
@Override



// funktionen zum ladern der Mitarbeiter
public Employee loadEmployee() throws IOException {
//einlesen von Firstname
String firstname = readRow();
if(firstname == null) {
return null;
}
//einlesen des Secondnames
String lastname = readRow();
if(lastname == null) {
return null;
}



//einlesen des passworts
String password = readRow();
if(password == null) {
return null;
}
//einlesen der MitarbeiterNr

String employeeNrString = "";
try {
employeeNrString = readRow();

}catch(IOException e) {
e.printStackTrace();
}
int employeeNr = Integer.parseInt(employeeNrString);

return new Employee(firstname,lastname,password,employeeNr);
}

//funktionen zum laden der Kunden


public Customer loadCustomer() throws IOException {
//einlesen des Firstnames

String firstname = readRow();
if(firstname == null) {
return null;
}
//einlesen des lastnames
String lastname = readRow();
if(lastname == null) {
return null;
}

//einlesen des passworts
String password = readRow();
if(password == null) {
return null;
}

//einlesen der Adresse
String adress = readRow();
if(adress == null) {
return null;
}

//einlesen der KundenNr

String customerNrString = "";
try {
customerNrString = readRow();

}catch(IOException e) {
e.printStackTrace();
}
int customerNr = Integer.parseInt(customerNrString);

return new Customer(firstname,lastname,password,customerNr,adress);
}

//funktionen zum speichern des Items

public boolean saveItem(Item i) throws IOException {
writeRow(i.getName());
writeRow(i.getNumber()+"");
writeRow(i.getPrice()+"");
writeRow(i.getStock()+"");
writeRow(i.getMinimumstock()+"");
writeRow(i.getBulk()+"");
return true;
}

//funktionen zum speichern der Mitarbeiter

public boolean saveEmployee(Employee e)  throws IOException {
writeRow(e.getFirstname());
writeRow(e.getLastname());
writeRow(e.getEmployeeNr()+"");  // "" weil writeRow ne String zurückgibt

return true;
}


//funktion zum speichern der Kunden

public boolean saveCustomer(Customer c) throws IOException {
writeRow(c.getFirstname());
writeRow(c.getLastname());
writeRow(c.getPassword()+"");
writeRow(c.getCustomerNr()+"");
writeRow(c.getAdress());
return true;
}


//funktiom zum speichern vom Log

public boolean saveLog(String log) throws IOException {
//hier fehlz noch der inhalt
writeRow(log);
return true;
}

//funktion zum einlesen des logs

public String readLog() {

String input ="";

try {
input = readRow();

}catch (IOException e) {
e.printStackTrace();
}
return input;

}









//die fehlen noch


@Override
public Changelog loadChangelogNew() throws IOException {
	//einlesen des Usernames
	
	return null;
}
@Override
public boolean saveChangelog(Changelog c) throws IOException {
	// TODO Auto-generated method stub
	return false;
}
		

		 


//ließt eine Zeile in einer Text Datei und gibt diese Zurück
private String readRow() throws IOException{
if(reader != null) {
return reader.readLine();
}
else {
	return "";
}
}
//schreibt eine Zeile in einer TextDatei

private void writeRow(String data) {
if(writer != null) {
writer.println(data);
}
}








}
