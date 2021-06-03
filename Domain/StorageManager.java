package Domain;

import java.io.IOException;
import java.util.*;

import Persistance.*;
import valueObjects.Item;

public class StorageManager {




private List<Item> itemStock = new ArrayList<Item>();
private PersistenceManager pm = new FilePersistenceManager();

public void readData(String data) throws IOException{
pm.openForReading(data);
Item i;
do {
i = pm.loadItem();
if(i != null) {
addItem(i);

}
}while(i != null);
}


//Schreibt den aktullen bestand vom Lager in die Lager Datei
public void writeData(String data) throws IOException{
pm.openForWriting(data);

for(Item i : itemStock) {
pm.saveItem(i);
}
pm.close();
}







//füge einen Artikel dem Bestand hinzu
public void  addItem(Item i) {
itemStock.add(i);	
	
	
}
//löscht eine Artikel aus dem aktuellen Bestand
public void delete(int number) {
Iterator<Item> iter = itemStock.iterator();
while(iter.hasNext()) {
Item i = iter.next();
if(i.getNumber()==number) {
iter.remove();
}

}
}
//durchsucht den Artikelbestand nach einem Namen und gibt eine Liste mit den Suchergebnissen zurück
public List<Item> searchItemByName(String name){
List<Item> searchResult = new ArrayList<Item>();
Iterator<Item> iter = itemStock.iterator();

while(iter.hasNext()) {
Item i = iter.next();
if(i.getName().equals(name)) {
searchResult.add(i);
}

}
return searchResult;
}

//durchsucht die Liste nach der Artikelnummer
public List<Item> searchItemByNumber(int number){

List<Item> searchResult = new ArrayList<Item>();
Iterator<Item>  iter = itemStock.iterator();

while(iter.hasNext()) {
Item i = iter.next();
if(i.getNumber() == number ) {
	
searchResult.add(i);
	
}
}
return searchResult;
}
//gibt eine Liste mit dem Artikelbestand zurück
public List<Item> getItemStock(){
return new ArrayList<Item>(itemStock);
	
}

}