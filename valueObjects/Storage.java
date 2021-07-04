package Valueobjects;

import java.io.IOException;

import java.util.List;

import Domain.StorageManager;

public class Storage {

  private String datei = "";
  private StorageManager storageManager;

  /**(ItemManagement)
   * Storage class takes care of the storage of the items and serves 
   * as a connection point between StorageManager and GUI.
   *  
   * 
   * */

  
  
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
   * 
   *Description: creates a new item and lets the Storage Manager insert it into the item stock, then returns the newly created item
   * 
   */
  public Item addAnItem(String name, int number, double price, int stock, int minimumStock, int bulk) {
    Item i = new Item(name, number, price, stock, minimumStock, bulk);
    storageManager.add(i);
    return i;
  }

  /**
   * calls the storage manager and it returns the item stock.
   * 
   */
  public List < Item > getAllItems() {
    return storageManager.getItemStock();
  }

  /**
   * gives the storage manager a number to search for an item, it then returns a list with the result.
   * @param nr
   * 
   */
  public List < Item > searchByNumber(int nr) {
    return storageManager.searchItemNr(nr);
  }

  /**
   * gives the storage manager a name, which returns a list with the result
   * @param name
   * @return
   */
  public List < Item > searchByName(String name) {
    return storageManager.searchItemName(name);
  }

}
	