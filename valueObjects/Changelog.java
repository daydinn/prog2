package valueObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Changelog {


private Employee e;
private Customer c;
private String message;
private String strDate;
private boolean typ; //true :Mitarbeiter | false: Kunde
private String Time;



//Konstruktor um eienen neuen Changelog als Mitarbeiter zu erzeugen


public Changelog(Employee e, String message, boolean typ) {
this.e = e;
this.message = message;
this.typ = typ;
	
Date date = Calendar.getInstance().getTime();  
DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
String Time = dateFormat.format(date);  
this.Time = Time;
}

//Konstruktor um einen neuen Changelog als Kunde zu erzeugen

public Changelog(Customer c, String message, boolean typ) {
this.c = c;
this.message = message;
this.typ = typ;
Date date = Calendar.getInstance().getTime();  
DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
String Time = dateFormat.format(date);  
this.Time = Time;
}


//Konstruktor wenn der Changelog aus der Datei geladen wird


public Changelog(Employee e, String message, boolean typ, String Time) {
this.e = e;
this.message = message;
this.typ = typ;
this.Time = Time;
}

//KOnstrukotr wenn der Changelog aus der Datei geladen wird

public Changelog(Customer c, String message, boolean typ, String Time) {
this.c = c;
this.message = message;
this.typ = typ;
this.Time = Time;
}

//toString für das ausgeben von einen Log in der Console

public String toString() {	
return("Message: " + message);
}

//getter Methoden

public Employee getEmployee () { return this.e; }

public Customer getCustomer() {return this.c;}

public String getMessage() { return message;}

public String getTime() {return this.Time;}

public boolean getTyp() { return this.typ;}

	
}