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
public void openForReading(String datei) throws IOException {
reader = new BufferedReader(new FileReader(datei));
}


//erzeugt einen BufferedWritter der etwas in eine Datei schreiben kann
@Override
public void openForWriting(String datei) throws IOException {
writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));
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

//fehlt massengut vielleicht


return new Item(name,number,price,stock,minimumstock);

}
@Override




public boolean saveArtikel(Item i) throws IOException {
	// TODO Auto-generated method stub
	return false;
}
@Override
public Employee loadEmployee() throws IOException {
	// TODO Auto-generated method stub
	return null;
}
@Override
public boolean saveEmployee(Employee e) throws IOException {
	// TODO Auto-generated method stub
	return false;
}
@Override
public Customer loadCustomer() throws IOException {
	// TODO Auto-generated method stub
	return null;
}
@Override
public boolean saveCustomer(Customer c) throws IOException {
	// TODO Auto-generated method stub
	return false;
}
@Override
public String readLog() throws IOException {
	// TODO Auto-generated method stub
	return null;
}
@Override
public boolean saveLog(String log) throws IOException {
	// TODO Auto-generated method stub
	return false;
}
@Override
public Changelog loadChangelogNew() throws IOException {
	// TODO Auto-generated method stub
	return null;
}
@Override
public boolean saveChangelog(Changelog c) throws IOException {
	// TODO Auto-generated method stub
	return false;
}
		
//erzeugt einen Buffered Reder der etwas einlesen kann aus einer Datei
		 



private String readRow() throws IOException{
if(reader != null) {
return reader.readLine();
}
else {
	return "";
}
}








}
