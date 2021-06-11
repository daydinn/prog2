package valueObjects;
import java.util.*;

public class Item {

private String name;
private int number;
private double price;
private int stock;
private int minimumstock;
private int bulk; //massengut
	
	
public Item(String name, int number, double price, int stock,int minimumstock,int bulk) {
this.name = name;
this.number = number;

this.price = price;
this.stock = stock;
this.minimumstock = minimumstock;
this.bulk=bulk;
}
public Item(String name, int number, double price, int stock,int minimumstock) { //without bulk ??
this.name = name;
this.number = number;

this.price = price;
this.stock = stock;
this.minimumstock = minimumstock;

}



public String toString() {
return("Name: "+name + "Nummer: "+number +"Preis: "+price+"Bestand: "+ stock+"Massengut"+bulk);
}
public boolean equals(Object anotherItem) {

if(anotherItem instanceof Item) {
return(this.number == ((Item) anotherItem).number)
		&& (this.name.equals(((Item) anotherItem).name));
}
else {
return false;
}
}


public boolean verfuegbar(Item i) {
if(i.getStock()< 0)	{
return false;	
}else {
return true;	
}
}




public String getName() {
	return name;
}




public void setName(String name) {
	this.name = name;
}




public int getNumber() {
	return number;
}




public void setNumber(int number) {
	this.number = number;
}




public double getPrice() {
	return price;
}




public void setPrice(double price) {
	this.price = price;
}




public int getStock() {
	return stock;
}




public void setStock(int stock) {
	this.stock = stock;
}




public int getMinimumstock() {
	return minimumstock;
}




public void setMinimumstock(int minimumstock) {
	this.minimumstock = minimumstock;
}


public int getBulk() {
return bulk;
}

public void setBulk(int newbulk) {
this.bulk = newbulk;
}







//getter / setter Methoden





}


