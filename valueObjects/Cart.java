package valueObjects;
import java.util.*;

public class Cart {


 
private List<tempItem> cartList = new ArrayList<tempItem>();

private List<Item> iList;

public Cart (List<Item>iList) {
this.iList =iList;
}





//Fügt einen Artikel den ck hinzu. Es wird geprüft ob ein Artikel bereits im Warenkorb ist, ist jedoch noch fehlerhaft

public void addItem(int iNumber, int iAmount) { //Item  dem Cart hinzufügen
Iterator<Item> iter = this.iList.iterator();
tempItem t;
while(iter.hasNext()) {
Item i = iter.next();
if(i.getNumber()==iNumber) {
if(cartList.contains(new tempItem(i,iAmount))) {  //prüfen ob der Item bereits im Cart ist(fehlerhaft)
t = cartList.get(cartList.indexOf(i));  //get gibt ein objekt aus einer Liste zurück, indexof liefert die stelle in einer Liste von einem Objekt
System.out.println("it already exists"); //nur zum testen
}else{
if(i.getStock()>=iAmount) {
cartList.add(new tempItem(i,iAmount));
System.out.println("it doenst exist yet"); //zum testen
}else{
System.out.println("There are not enough items in stock");	
}
}
}
}
}


//löscht einen Item aus dem Cart, je nach dem wie groß die Anzahl ist

public void delItem(int iNumber, int iAmount) { //Item aus dem Warenkorb entfernen
Iterator<tempItem> iter = this.cartList.iterator();
while(iter.hasNext()) {
	
tempItem t = iter.next();
if(t.getItem().getNumber()==iNumber) { //Item ist im Cart vorhanden
if(t.getAmount() == iAmount) { //Artikel soll komplett gelöscht werden
iList.remove(t);


}else if (t.getAmount()>iAmount){  //Die Anzahl im Cart soll veringert werden
t.setAmount(t.getAmount()-iAmount);
}else{
System.out.println("It is not possible to delete more Items than are available.");

}
}else{ //Item war gar nicht im Cart
System.out.println("This Item is not in the shopping cart");
}
}
}






//gibt den Warenkorb in der Console aus


public void showCart() { 

for(tempItem i : cartList) {
System.out.println(i);
}
}





//Gibt die aktuelle Cartlist aus
 
public List<tempItem> getCart(){
return cartList;
}


//durchsucht den Warenkorb nach einem Namen

public List<tempItem> searchByName(String name){
List<tempItem> searchResult = new ArrayList<tempItem>();
Iterator<tempItem> iter = cartList.iterator();

while(iter.hasNext()) {
tempItem i = iter.next();
if(i.getItem().getName().equals(name)) {
searchResult.add(i);
}
}
return searchResult;	
}













//Leer den Cart  */

public void leeren() {
cartList.removeAll(cartList);

}


/* um den Cart zu kaufen */

public void kaufen() { // item aus dem Cart kaufen
for(tempItem t : cartList) { //jeden Item aus dem Warenkorb durchgehen
if(iList.contains(t.getItem())) { //nachschauen ob die Items aus dem Cart auch in Itemlist sind
Iterator<Item> iter = this.iList.iterator(); //in der Itemliste nach dem Iten suchen , der in der for schleife ausgewählt ist
while(iter.hasNext()) {
Item i = iter.next();

if(i.getNumber()==t.getItem().getNumber()) { //abgleich der Listen ob beide das gleiche objekt ausgewählt haben
i.setStock(i.getStock() - t.getAmount()); //den bestand im Lager verringern
System.out.println(i);
}
}
}

}
}
}
