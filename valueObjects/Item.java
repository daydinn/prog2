package valueObjects;

public class Item {

private String bezeichnung;
private int nummer;
private double preis;
private int bestand;
private int mindestBestand;
	
	
public Item(String bezeichnung, int nummer, double preis, int bestand,int mindestBestand) {
this.nummer = nummer;
this.bezeichnung = bezeichnung;
this.preis = preis;
this.bestand = bestand;
this.mindestBestand = mindestBestand;
}




public String toString() {
return("Name: "+bezeichnung + "Nummer: "+nummer +"Preis: "+preis+"Bestand: "+ bestand);
}
public boolean equals(Object anotherItem) {

if(anotherItem instanceof Item) {
return(this.nummer == ((Item) anotherItem).nummer)
		&& (this.bezeichnung.equals(((Item) anotherItem).bezeichnung));
}
else {
return false;
}
}


public boolean verfuegbar(Item i) {
if(i.getBestand()< 0)	{
return false;	
}else {
return true;	
}
}










//getter / setter Methoden

public String getBezeichnung() {
	return bezeichnung;
}


public void setBezeichnung(String bezeichnung) {
	this.bezeichnung = bezeichnung;
}


public int getNummer() {
	return nummer;
}


public void setNummer(int nummer) {
	this.nummer = nummer;
}


public double getPreis() {
	return preis;
}


public void setPreis(double preis) {
	this.preis = preis;
}


public int getBestand() {
	return bestand;
}


public void setBestand(int bestand) {
	this.bestand = bestand;
}


public int getMindestBestand() {
	return mindestBestand;
}


public void setMindestBestand(int mindestBestand) {
	this.mindestBestand = mindestBestand;
}








}


