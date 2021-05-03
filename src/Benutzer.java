/*
* Abstrakte Klasse für die Subklassen Mitarbeiter und Kunde
*/

public abstract class Benutzer 
{
	
	String name;
	String adresse;
	String passwort;
	int nummer;
	
	public Benutzer(String Name, String Adresse, String Passwort, int Nummer) {
		this.name = name;
		this.adresse = adresse;
		this.passwort = passwort;
		this.nummer = nummer;
}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	
	
		





	}
	

