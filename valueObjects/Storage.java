package valueObjects;
import java.io.IOException;
import java.util.*;
import Domain.StorageManager;



public class Storage {
	
	

/**
 * 
* Lager dient als Schnittstelle zwischen Lagermanager und  CUI
* 
* 
* 
*/


private String data = "";
private StorageManager storageManager;




//Konstruktor für das Lager.

public Storage(String data) throws IOException{
this.data = data;
storageManager = new StorageManager();
storageManager.readData(data+"S.txt");
}



//ruft den Storagemanager auf, welcher den Artikelbestand zurückgibt

public List<Item> getAllItems(){
return storageManager.getItemStock();
}


//übergibt dem Lagarmanager einen Namen,dieser gibt eine Liste mit dem Ergebnis zurück
public List<Item> searchbyName(String name){
return storageManager.searchItemByName(name);
	
}
//übergibt dem Lagarmanager eine Nummer ,dieser gibt eine Liste mit dem Ergebnis zurück
public List<Item> searchbyNumber(int number){
return storageManager.searchItemByNumber(number);
	
}


//erstellt einen neuen Item und lässt diesen vom Storagemanager in den Itemstock einfügen

public Item addanItem(String name,int number,double price,int stock,int minimumstock,int bulk) {
Item i = new Item(name,number,price,stock,minimumstock,bulk);
storageManager.addItem(i);
return i;
}

//übergibt dem Storagemanager eine Nummer, dieser sucht nach dem Artikel mit der Nummer und löscht diesen


public void deleteanItem(int number) {
storageManager.delete(number);
}


//der Storagemanager schreibt alle Daten in in die TXT datei

public void writeItem() throws IOException{
storageManager.writeData(data+"S.txt");
}

}
