package valueObjects;

public class tempItem {


	
// ein TempItem beinhaltet einen Artikel und eine Anzahl(Amount).Die Anzahl gibt an wie of sich dieser im Warenkorb befindet.	
	
	
	
private int amount;
private Item i;



public tempItem(Item i, int amount) {
this.amount = amount;
this.i = i;
}




public boolean equals(Object o) {
	
System.out.println("call");
if(o instanceof tempItem) {
System.out.println("equals");
return(this.i.getNumber() == ((tempItem)o).getItem().getNumber());
}
else {
return false;
}
	
}
public String toString() {
double gPrice;
gPrice =(this.i.getPrice()*this.amount);
return(this.i.toString()+ " | Anzahl " + amount + "| Stückpreis: "+ this.i.getPrice() + "$ | Preis: " + gPrice + "$" );
}

public Item getItem() { return this.i;}
public int getAmount() {return this.amount;}

//setter Methoden

public void setAmount(int amount) {this.amount = amount;}


}
	


