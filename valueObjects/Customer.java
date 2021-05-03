package valueObjects;

public class Customer extends User {

private int customerNr;
private String username;
private String passwort;
private String firstname;
private String lastname;
private String city;
private String zipcode;
private String street;	
	


public Customer(String username,String passwort,String firstname,String lastname,String city,String zipcode,String street,int customerNr) {
this.customerNr = customerNr;
this.username = username;
this.passwort= passwort;
this.firstname= firstname;
this.lastname = lastname;
this.city = city;
this.zipcode = zipcode;
this.street = street;

	
}

public String toString() {
return(" Username: "+ username +"\n"+ " Passwort: "+ passwort+ "\n"+ " Firstname: " +firstname+ "  Lastname: " +lastname + "\n" +"\n"+ " City: " +city+ " | Zip-Code: "+zipcode+ " | Street: "+ street+ " | Customernumber: "+customerNr);	
}

public static void main(String[] args){
    Customer test1 = new Customer("funboy","1234","Someone","Noone","Nowhere","420420","Sesame",777);
    System.out.println(test1.toString());
}
}


	

