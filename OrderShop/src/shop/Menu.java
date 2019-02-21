package shop;

import java.io.FileNotFoundException;
import java.util.TreeSet;

import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidPriceException;
import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;

import java.util.Iterator;

public class Menu {

	private TreeSet<Item> menu = new TreeSet<Item>();

	public Menu() throws FileNotFoundException, InvalidItemIDLengthException, InvalidItemException,
			InvalidPriceException, InvalidCategoryException {
		CsvReader reader = new CsvReader();
		this.menu = reader.readMenuInfo("Menu.csv");
	}

	public void displayMenu() {
		Iterator<Item> iterator;
		iterator = menu.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getItemID() + " ");
		}
	}

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
