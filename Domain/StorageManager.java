package Domain;

import java.util.*;

import valueObjects.Item;

public class StorageManager {




private List<Item> itemStock = new ArrayList<Item>();








//füge einen Artikel dem Bestand hinzu
public void  addItem(Item i) {
itemStock.add(i);	
	
	
}
public void delete(int number) {
Iterator<Item> iter = itemStock.iterator();
while(iter.hasNext()) {
Item i = iter.next();
if(i.getNumber()==number) {
iter.remove();
}

}
}

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

public List<Item> getItemStock(){
return new ArrayList<Item>(itemStock);
	
}

}