package valueObjects;

public class Employee extends User {


private int employeeNr;
private String username;
private String passwort;
private String firstname;
private String lastname;
private String city;
private String zipcode;
private String street;





public Employee(String username,String passwort,String firstname,String lastname,String city,String zipcode,String street,int employeeNr) {
this.employeeNr = employeeNr;
this.username = username;
this.passwort= passwort;
this.firstname= firstname;
this.lastname = lastname;
this.city = city;
this.zipcode = zipcode;
this.street = street;

	
}



//toString Methode um einen Employee in der Console auszugeben

public String toString() {
return(" Username: "+ username +"\n"+ " Passwort: "+ passwort+ "\n"+ " Firstname: " +firstname+ "  Lastname: " +lastname + "\n" +"\n"+ " City: " +city+ " | Zip-Code: "+zipcode+ " | Street: "+ street+ " | Employeenumber: "+employeeNr);	
}




    public static void main(String[] args){
        Employee test2 = new Employee("mitarbeiter","1234","XXX","YYY","Mordor","420420","Sesame",777);
        System.out.println(test2.toString());
    }
}

