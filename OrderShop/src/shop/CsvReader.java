package shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidOrderCustomerID;
import ourExceptions.InvalidOrderTimeStamp;
import ourExceptions.InvalidPriceException;
import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;

// Here we have the readers that read the .csv files
public class CsvReader {
	private ArrayList<Order> orderList = new ArrayList<Order>();
	private TreeSet<Item> itemList = new TreeSet<Item>(new ItemComparator());

	public CsvReader() {
	}

	public TreeSet<Item> readMenuInfo(String filename) throws InvalidItemIDLengthException, FileNotFoundException,
			InvalidItemIDLengthException, InvalidItemException, InvalidPriceException, InvalidCategoryException {
		File file1 = new File(filename);
		@SuppressWarnings("resource")
		Scanner sc1 = new Scanner(file1);
		// we don't need a Map since we don't have a key for the menu, we don't need a
		// set since we don't want to get rid of the duplicates

		String line = sc1.nextLine();
		try {
			while (sc1.hasNextLine()) {
				line = sc1.nextLine();
				String[] values = line.split(",");
				String name = values[0];
				String description = values[1];
				double price = Double.parseDouble(values[2]);
				if (price < 0 || price > 100) {
					throw new InvalidPriceException();
				}
				String itemID = values[3];
				if (itemID.length() != 8) {
					throw new InvalidItemIDLengthException();
				}
				String category = values[4];
				if (!(category.equals("Food")) && !(category.equals("Beverage")) && !(category.equals("Dessert"))) {
					throw new InvalidCategoryException();
				}
				Item menuItem = new Item(name, description, price, itemID, category);
				itemList.add(menuItem);

			}
		} catch (NumberFormatException nfe) {
			System.err.print("Wrong format of price! Line '" + line + "' skipped.\n");
			while (sc1.hasNext())
				sc1.nextLine();
		} catch (InvalidItemIDLengthException invalidItemIDLength) {
			System.err.print("Invalid item ID's length in line: '" + line + "' !\n");
			while (sc1.hasNext())
				sc1.nextLine();
		} catch (InvalidPriceException invalidPrice) {
			System.err.print("Price should be between 0 and 100! Error found in line: '" + line + "' .\n");
			while (sc1.hasNext())
				sc1.nextLine();
		} catch (InvalidCategoryException invalidCategory) {
			System.err.print("Category is invalid!\n");
			while (sc1.hasNext())
				sc1.nextLine();
		}
		sc1.close();

		return itemList;
	}

	public ArrayList<Order> readOrdersInfo(String filename)
			throws FileNotFoundException, InvalidOrderTimeStamp, InvalidOrderCustomerID {

		File file2 = new File(filename);
		@SuppressWarnings("resource")
		Scanner sc2 = new Scanner(file2);
		String line = sc2.nextLine();
		try {
			while (sc2.hasNextLine()) {
				line = sc2.nextLine();
				String[] values = line.split(",");
				String timestamp = values[0];
				if (timestamp.length() != 10) {
					throw new InvalidOrderTimeStamp();
				}
				String customerID = values[1];
				if (!(customerID.startsWith("CUST"))) {
					throw new InvalidOrderCustomerID();
				}
				Item itemOrdered = findItem(values[2]);
				Order newOrder = new Order(timestamp, customerID, itemOrdered);
				boolean foundOrd = false;
				for (Order ord : orderList) {
					if (ord.getCustomerID().equals(customerID) && ord.getTimeStamp().equals(timestamp)) {
						ord.addItem(itemOrdered, 1);
						foundOrd = true;
					}
				}
				if (!foundOrd)
					orderList.add(newOrder);

			}
		} catch (InvalidOrderTimeStamp invalidTimestamp) {
			System.err.print("Wrong format of timestamp!");
			while (sc2.hasNext())
				sc2.nextLine();
		} catch (InvalidOrderCustomerID invalidCustomer) {
			System.err.print("Wrong format of customer!");
			while (sc2.hasNext())
				sc2.nextLine();
		}
		sc2.close();
		return orderList;

	}

	public Item findItem(String itemID) {

		for (Item item : itemList) {
			if (item.getItemID().equals(itemID)) {
				return item;
			}
		}
		return null;
	}

}
