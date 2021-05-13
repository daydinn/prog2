package valueObjects;
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



private StorageManager storageManager;



//ruft den Storagemanager auf, welcher den Artikelbestand zurückgibt

public List<Item> showAllItems(){
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

public Item addanItem(String name,int number,double price,int stock,int minimumstock) {
Item i = new Item(name,number,price,stock,minimumstock);
storageManager.addItem(i);
return i;
}

//übergibt dem Storagemanager eine Nummer, dieser sucht nach dem Artikel mit der Nummer und löscht diesen


public void deleteanItem(int number) {
storageManager.delete(number);
}

}
