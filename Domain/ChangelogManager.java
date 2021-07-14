package Domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import Exceptions.InvalidNameChangelogException;
import Exceptions.InvalidNumberChangelogException;
import Persistence.FilePersistenceManager;
import Persistence.PersistenceManager;
import Valueobjects.Changelog;
import Valueobjects.Item;

/**
 * Description: ChangelogManager is the class for all changelog function, has a list of all Changelogs and uses a PersistenceManager object for reading and writing 
 * Used by: Gui, Cui
 *
 */
public class ChangelogManager {

  private List < Changelog > changelog = new ArrayList < Changelog > ();
  private PersistenceManager pm = new FilePersistenceManager();

  /**
   * Description: reads data with filepersistenceManager object
   * @param file
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
   * Description: writes data to the changelog file with filepersistencemanager object
   * @param datei
   * @throws IOException
   */
  public void writeData(String file) throws IOException {

    pm.openForWriting(file + "-Textfile");

    for (Changelog c: changelog) {
      pm.saveChangelog(c);
    }

    pm.close();
  }

  /**
   * Description: add a new Changelog and outputs it in the console.
   * @param c
   */
  public void add(Changelog c) {
    changelog.add(c);
    System.out.println(c);
  }

  /**
   *Description: Outputs list where all changelogs are saved
   * @return
   */
  public List < Changelog > getChangelog() {
    return new ArrayList < Changelog > (changelog);
  }

  /**
   * Description: outputs the changelog in the console
   */
  public void outputLog() {
    System.out.println(changelog);
  }

  /**
   * searches the changelog for a name and returns all entries with the name.
   * @param name
   * @return
   * @throws InvalidNameChangelogException
   */
  public List < Changelog > searchChangelogName(String name) throws InvalidNameChangelogException {

    List < Changelog > searchResult = new ArrayList < Changelog > ();
    Iterator < Changelog > iter = changelog.iterator();

    while (iter.hasNext()) {
      Changelog a = iter.next();
      if (a.getTyp()) { //Typ == True --> Employee             
        if (a.getEmployee().getFirstname().equals(name)) {
          searchResult.add(a);

        }
      } else { //Typ == False --> Customer
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
   * Description: searches the changelog for nr and returns all entries with this nr.
   * @param nr
   * @return searchResult
   * @throws InvalidNumberChangelogException
   */
  public List < Changelog > searchChangelogNr(int nr) throws InvalidNumberChangelogException {

    List < Changelog > searchResult = new ArrayList < Changelog > ();
    Iterator < Changelog > iter = changelog.iterator();

    while (iter.hasNext()) {
      Changelog a = iter.next();
      if (a.getTyp()) { //Typ == True --> Employee             
        if (a.getEmployee().getEmployeeNr() == (nr)) {
          searchResult.add(a);

        }
      } else { //Typ == False --> Customer
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

  /**
   * Description: sorts saved changelogs by date
   * Used by: Gui
   * @param list
   * @return list
   * 
   */
  public List < Changelog > sortDateChangelogliste(List < Changelog > list) {
    if (list.isEmpty()) {
      System.out.println("List is empty .");
    } else {

      Collections.sort(list, new Comparator < Changelog > () {
        @Override
        public int compare(Changelog u1, Changelog u2) {
          return u1.getTime().compareTo(u2.getTime());
        }
      });

    }
    return list;
  }

}