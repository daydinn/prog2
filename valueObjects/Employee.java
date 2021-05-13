package valueObjects;

public class Employee extends User {

private int employeeNr;










public Employee(String firstname, String lastname,String password,int employeeNr ) {
super(firstname,lastname,password);
this.employeeNr = employeeNr;

}



//toString Methode um einen Employee in der Console auszugeben

public String toString() {
return(" Number: "+ employeeNr +"\n"+ " Passwort: "+ password+ "\n"+ " Firstname: " +firstname+ "  Lastname: " +lastname + "\n" +"\n" );	
}



//Getter und Setter
public int getEmployeeNr() {
	return employeeNr;
}



public void setEmployeeNr(int employeeNr) {
	this.employeeNr = employeeNr;
}



//Main

    public static void main(String[] args){
        Customer admin1 = new Customer("Admin","Adminson","root",420,"Olympos Greece");
        System.out.println(admin1.toString());
    }
}

