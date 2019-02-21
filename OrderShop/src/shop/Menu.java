/* author: Stergiou Konstantinos
 * All copyrights reserved 2019-2020
 */

package shop;

import java.io.FileNotFoundException;
import java.util.TreeSet;

import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidPriceException;
import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;

import java.util.Iterator;


// This is the menu that gets rid of the duplicates if there are any
public class Menu {

	private TreeSet<Item> menu = new TreeSet<Item>();

	public Menu() throws FileNotFoundException, InvalidItemIDLengthException, InvalidItemException,
			InvalidPriceException, InvalidCategoryException {
		CsvReader reader = new CsvReader();
		this.menu = reader.readMenuInfo("Menu.csv");
	}

	public String displayMenu() {
		String m = "";
		Iterator<Item> iterator;
		iterator = menu.iterator();
		while (iterator.hasNext()) {
			Item next = iterator.next();
			m += next.getName() + "," + next.getDescription() + "," + next.getItemID() + ","
					+ next.getPrice() + ".\n";
		}
		return m;
	}
	
	// display item's details for Foods
	public String displayFood() {
		String menuDetails = "";
		Iterator<Item> iterator;
		iterator = menu.iterator();
		while (iterator.hasNext()) {
			Item next = iterator.next();
			if ("Food".equals(next.getCategoryItem())) {

				menuDetails += next.getName() + "," + next.getDescription() + "," + next.getItemID() + ","
						+ next.getPrice() + ",";

			}
		}
		return menuDetails;
	}

	// display item's details for Desserts
	public String displayDessert() {
		String menuDetails = "";
		Iterator<Item> iterator;
		iterator = menu.iterator();
		while (iterator.hasNext()) {
			Item next = iterator.next();
			if ("Dessert".equals(next.getCategoryItem())) {
				menuDetails += next.getName() + "," + next.getDescription() + "," + next.getItemID() + ","
						+ next.getPrice() + ",";

			}
		}
		return menuDetails;
	}
	
	// display item's details for Beverages
	public String displayBeverage() {
		String menuDetails = "";
		Iterator<Item> iterator;
		iterator = menu.iterator();
		while (iterator.hasNext()) {
			Item next = iterator.next();
			if ("Beverage".equals(next.getCategoryItem())) {
				menuDetails += next.getName() + "," + next.getDescription() + "," + next.getItemID() + ","
						+ next.getPrice() + ",";

			}
		}
		return menuDetails;
	}

}
