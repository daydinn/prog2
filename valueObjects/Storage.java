package Valueobjects;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Domain.StorageManager;

/**
 * 
 * Description:Storage serves as a connection point between StorageManager and GUI,is also class for management of Items
 * uses a storagemanager object for functions 
 */
public class Storage {

  private String datei = "";
  private StorageManager storageManager;

  /**
   * 
   * @param datei
   * @throws IOException
   */
  public Storage(String datei) throws IOException {
    this.datei = datei;
    storageManager = new StorageManager();
    storageManager.readData(datei + "-Textfile");
  }

  /** 
   * Description: Calls the storage manager and it writes the data into the file that ends with -Textfile
   * 
   * @throws IOException
   */
  public void writeItems() throws IOException {
    storageManager.writeData(datei + "-Textfile");
  }

  /**
   * Description: gives the storage manager a number that is used to search for an item, then the item,then the found is deleted.
   * 
   * 
   */
  public void deleteItem(int nummer) {
    storageManager.delete(nummer);
  }

  /**
   * Description: creates a new item and lets the Storage Manager insert it into the item stock, then returns the new created item
   * @param name
   * @param number
   * @param price
   * @param stock
   * @param minimumStock
   * @param bulk
   * @return new item
   */
  public Item addAnItem(String name, int number, double price, int stock, int minimumStock, int bulk) {
    Item i = new Item(name, number, price, stock, minimumStock, bulk);
    storageManager.add(i);
    return i;
  }

  /**
   * Description: calls the storage manager and it returns storageManagers  item stock.
   * @return Itemstock
   */
  public List < Item > getAllItems() {
    return storageManager.getItemStock();
  }

  /**
   * Description: gives the storage manager a number to search for an item, it then returns a list with the result.
   * @param nr
   * @return nrlist
   */
  public List < Item > searchByNumber(int nr) {
    return storageManager.searchItemNr(nr);
  }

  /**
   * gives the storage manager a name, which returns a list with the result
   * @param name
   * @return namelist
   */
  public List < Item > searchByName(String name) {
    return storageManager.searchItemName(name);
  }

  //Gui,cui functions

  /**
   * used by: gui,cui (employeemenu,customer menu)
   * Description: Sorts the item list by name with the help of a comparator
   * 
   * @param list to be sorted
   * @return list
   */
  public List < Item > sortNameItemList(List < Item > list) {
    if (list.isEmpty()) {
      System.out.println("List is empty.");
    } else {

      Collections.sort(list, new Comparator < Item > () {
        @Override
        public int compare(Item u1, Item u2) {
          return u1.getName().compareTo(u2.getName());
        }
      });

    }
    return list;
  }

  /**
   * 
   * Description: Sorts the item list by number with the help of a comparator
   * used by: gui,cui (employeemenu,customer menu)
   * @param list to be sorted
   * @return list
   */
  public List < Item > sortNumberItemList(List < Item > list) {
    if (list.isEmpty()) {
      System.out.println("List is empty .");
    } else {

      Collections.sort(list, new Comparator < Item > () {
        @Override
        public int compare(Item u1, Item u2) {

          int x = Integer.compare(u1.getNumber(), u2.getNumber());

          return x;
        }
      });

    }
    return list;
  }

}