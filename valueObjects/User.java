package valueObjects;

public abstract class User {

String firstname;
String lastname;

String password;





public User(String firstname, String lastname, String password) {
this.firstname = firstname;
this.lastname = lastname;
this.password = password;

	
}




public void registerCustomer() {
	
	
	
}




public String getFirstname() {
	return firstname;
}




public void setFirstname(String firstname) {
	this.firstname = firstname;
}




public String getLastname() {
	return lastname;
}




public void setLastname(String lastname) {
	this.lastname = lastname;
}




public String getPassword() {
	return password;
}




public void setPassword(String password) {
	this.password = password;
}



}
