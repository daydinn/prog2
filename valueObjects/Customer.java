package valueObjects;

public class Customer extends User {

private int customerNr;
private String adress;

	


public Customer(String firstname, String lastname,String password,int customerNr,String adress ) {
super(firstname,lastname,password);
this.customerNr = customerNr;
this.adress = adress;
}

public String toString() {
return(" Number: "+ customerNr +"\n"+ " Passwort: "+ password+ "\n"+ " Firstname: " +firstname+ "  Lastname: " +lastname + "\n" +"\n"+ " Adress: " +adress );	
}

public static void main(String[] args){
    Customer test1 = new Customer("Alan","Ericson","secret",55555,"Sesamstreet 30000 Newyork");
    System.out.println(test1.toString());
}
}


	

