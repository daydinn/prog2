package domain;

import java.io.IOException;

import java.util.*;

import Persistence.FilePersistenceManager;
import Persistence.PersistenceManager;
import Valueobjects.Item;

public class StorageManager {

	private List<Item> itemStock = new ArrayList<Item>();
	private PersistenceManager pm = new FilePersistenceManager();

	public void readData(String file) throws IOException {

		pm.openForReading(file);
		Item i;

		do {
			i = pm.loadItems();
			if (i != null) {
				add(i);
			}
		} while (i != null);

	}

	/**
	 * Deschribtion: uses Persistence Manager to write the current stock from the
	 * Storage to the Storage File.
	 * 
	 * @param String datei
	 * @throws IOException
	 */
	public void writeData(String datei) throws IOException {

		pm.openForWriting(datei);

		for (Item i : itemStock) { // for each Item from the Itemstock
			pm.saveItems(i);
		}

		pm.close();

	}

	/**
	 * adds an item to the inventory
	 * 
	 * @param a
	 */

	public void add(Item i) {

		itemStock.add(i);
	}

	/**
	 * deletes an item that has the given number
	 * 
	 * @param nummer
	 */
	public void delete(int number) {
		Iterator<Item> iter = itemStock.iterator();
		while (iter.hasNext()) {
			Item i = iter.next();
			if (i.getNumber() == number) { //
				iter.remove();
			}
		}
	}

	/**
	 * Searches the item inventory for a name
	 * 
	 * @param name
	 * @return a list of search results
	 */
	public List<Item> searchItemName(String name) {

		List<Item> searchResult = new ArrayList<Item>();
		Iterator<Item> iter = itemStock.iterator();

		while (iter.hasNext()) {
			Item i = iter.next();
			if (i.getName().equals(name)) {
				searchResult.add(i);
			}
		}
		return searchResult;
	}

	/**
	 * searches the list for the given item number
	 * 
	 * @param nr
	 * @return
	 */
	public List<Item> searchItemNr(int nr) {

		List<Item> searchResult = new ArrayList<Item>();
		Iterator<Item> iter = itemStock.iterator();

		while (iter.hasNext()) {
			Item i = iter.next();
			if (i.getNumber() == nr) {
				searchResult.add(i);
			}
		}
		return searchResult;
	}

	/**
	 * returns a list of the item inventory
	 * 
	 * @return
	 */
	public List<Item> getItemStock() {
		return new ArrayList<Item>(itemStock);
	}

}