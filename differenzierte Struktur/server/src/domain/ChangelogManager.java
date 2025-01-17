package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exceptions.InvalidNameChangelogException;
import exceptions.InvalidNumberChangelogException;
import Persistence.FilePersistenceManager;
import Persistence.PersistenceManager;
import valueObjects.Changelog;
import valueObjects.Item;

public class ChangelogManager {

	private List<Changelog> changelog = new ArrayList<Changelog>();
	private PersistenceManager pm = new FilePersistenceManager();

	/**
	 * Description: reads Data with a FilePersistenceManager Object for Changelog-
	 * 
	 * @param datei
	 * @throws IOException
	 */
	public void readData(String file) throws IOException {

		pm.openForReading(file + "-Textfile");
		Changelog c;

		do {
			c = pm.loadChangelogNew();
			if (c != null) {
				add(c);
			}
		} while (c != null);

	}

	/**
	 * Description: The Persistencemanager Object writes the Changelog to the file
	 * 
	 * @param datei
	 * @throws IOException
	 */
	public void writeData(String file) throws IOException {

		pm.openForWriting(file + "-Textfile");

		for (Changelog c : changelog) {
			pm.saveChangelog(c);
		}

		pm.close();
	}

	/**
	 * Description: add a new Changelog
	 * 
	 * @param c
	 */
	public void add(Changelog c) {
		changelog.add(c);
		System.out.println(c);
	}

	/**
	 * Gibt den aktuellen Changelog aus
	 * 
	 * @return
	 */
	public List<Changelog> getChangelog() {
		return new ArrayList<Changelog>(changelog);
	}

	/**
	 * Description outputs the changelog in the console
	 */
	public void outputLog() {
		System.out.println(changelog);
	}

	/**
	 * searches the changelog for a name and returns all entries with the name.
	 * 
	 * @param name
	 * @return
	 * @throws InvalidNameChangelogException
	 */
	public List<Changelog> searchChangelogName(String name) throws InvalidNameChangelogException {

		List<Changelog> searchResult = new ArrayList<Changelog>();
		Iterator<Changelog> iter = changelog.iterator();

		while (iter.hasNext()) {
			Changelog a = iter.next();
			if (a.getTyp()) { // Typ == True --> Employee
				if (a.getEmployee().getFirstname().equals(name)) {
					searchResult.add(a);

				}
			} else { // Typ == False --> Customer
				if (a.getCustomer().getFirstname().equals(name)) {
					searchResult.add(a);
				}
			}
		}

		if (searchResult.isEmpty()) {
			throw new InvalidNameChangelogException();
		}
		return searchResult;
	}

	/**
	 * searches the changelog for a nr and returns all entries with the nr.
	 * 
	 * @param nr
	 * @return
	 * @throws InvalidNumberChangelogException
	 */

	public List<Changelog> searchChangelogNr(int nr) throws InvalidNumberChangelogException {

		List<Changelog> searchResult = new ArrayList<Changelog>();
		Iterator<Changelog> iter = changelog.iterator();

		while (iter.hasNext()) {
			Changelog a = iter.next();
			if (a.getTyp()) { // Typ == True --> Employee
				if (a.getEmployee().getEmployeeNr() == (nr)) {
					searchResult.add(a);

				}
			} else { // Typ == False --> Customer
				if (a.getCustomer().getCustomerNr() == (nr)) {
					searchResult.add(a);
				}
			}
		}

		if (searchResult.isEmpty()) {
			throw new InvalidNumberChangelogException();
		}
		return searchResult;
	}

}