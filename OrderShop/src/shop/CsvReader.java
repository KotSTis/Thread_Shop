/* author: Jiaxi Lyu
 * All copyrights reserved 2019-2020
 */

package shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidOrderCustomerID;
import ourExceptions.InvalidOrderTimeStamp;
import ourExceptions.InvalidOrderCustomerIDException;
import ourExceptions.InvalidOrderCustomerNameException;
import ourExceptions.InvalidOrderTimeStampException;
import ourExceptions.InvalidPriceException;
import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;

// Here we have the readers that read the .csv files
public class CsvReader {
	private ArrayList<Order> orderList = new ArrayList<Order>();
	private TreeSet<Item> itemList = new TreeSet<Item>(new ItemComparator());

	public CsvReader() {
		Log log = new Log();
	}

	public TreeSet<Item> readMenuInfo(String filename) throws InvalidItemIDLengthException, FileNotFoundException,
			InvalidItemIDLengthException, InvalidItemException, InvalidPriceException, InvalidCategoryException {
		File file1 = new File(filename);
		@SuppressWarnings("resource")
		Scanner sc1 = new Scanner(file1);

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

				if (!(itemID.startsWith("FOOD")) && (!(itemID.startsWith("BEVE"))) && (!(itemID.startsWith("DESS")))) {
					throw new InvalidItemException();
				}
				String category = values[4];
				if (!(category.equals("Food")) && !(category.equals("Beverage")) && !(category.equals("Dessert"))) {
					throw new InvalidCategoryException();
				}
				Item menuItem = new Item(name, description, price, itemID, category);
				itemList.add(menuItem);

			}
		} catch (NumberFormatException nfe) {
			System.err.print(filename + ": Wrong format of price! Line '" + line + "' skipped.\n\n");
			while (sc1.hasNext())
				sc1.nextLine();
		}
		// CUSTOM EXCEPTIONS
		catch (InvalidItemIDLengthException invalidItemIDLength) {
			System.err.print(filename + ": Invalid item ID's length in line: '" + line + "'!\n\n");
			while (sc1.hasNext())
				sc1.nextLine();
		} catch (InvalidPriceException invalidPrice) {
			System.err
					.print(filename + ": Price should be between 0 and 100!\nError found in line: '" + line + "'.\n\n");
			while (sc1.hasNext())
				sc1.nextLine();
		} catch (InvalidCategoryException invalidCategory) {
			System.err.print(filename + ": Category is invalid!\nError found in line: '" + line + "'.\n\n");
			while (sc1.hasNext())
				sc1.nextLine();
		} catch (InvalidItemException invalidItem) {
			System.err.print(filename + ": ItemID is invalid!\nError found in line: '" + line + "'.\n\n");
			while (sc1.hasNext())
				sc1.nextLine();
		}
		sc1.close();

		return itemList;
	}

	// Reading orders and storing them in an ArrayList since we don't care about
	// duplicates or order
	public ArrayList<Order> readOrdersInfo(String filename)
			throws FileNotFoundException, InvalidOrderTimeStampException, InvalidOrderCustomerIDException, InvalidOrderCustomerNameException {

		File file2 = new File(filename);
		@SuppressWarnings("resource")
		Scanner sc2 = new Scanner(file2);
		String line = sc2.nextLine();
		try {
			while (sc2.hasNextLine()) {
				line = sc2.nextLine();
				String[] values = line.split(",");
				String timestamp = values[0];
				if ((timestamp.length() != 10)
						|| ((!(Integer.parseInt(timestamp.substring(0, 2)) >= 0)
								|| (!(Integer.parseInt(timestamp.substring(0, 2)) < 24))))
						|| ((!(Integer.parseInt(timestamp.substring(2, 4)) >= 0)
								|| (!(Integer.parseInt(timestamp.substring(2, 4)) < 60))))
						|| ((!(Integer.parseInt(timestamp.substring(4, 6)) >= 0)
								|| (!(Integer.parseInt(timestamp.substring(4, 6)) < 60))))
						|| ((!(Integer.parseInt(timestamp.substring(6, 8)) > 0)
								|| (!(Integer.parseInt(timestamp.substring(6, 8)) <= 31))))
						|| ((!(Integer.parseInt(timestamp.substring(8, 10)) > 0)
								|| (!(Integer.parseInt(timestamp.substring(8, 10)) <= 12))))) {
					throw new InvalidOrderTimeStampException();
				}
				String customerID = values[1];

				if (!((customerID.startsWith("CUST"))) || (!(customerID.substring(4, 8).matches("[0-9]+")))) {
					throw new InvalidOrderCustomerIDException();
				}
				Item itemOrdered = findItem(values[2]);
				String customerName = values[3];
				if (customerName.split(" ").length != 2){
					throw new InvalidOrderCustomerNameException();
				}
				Order newOrder = new Order(timestamp, customerID, itemOrdered, customerName);
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
		} catch (NullPointerException nullPointer) {
			System.err.print(filename + ": Nothing to point at!\nError found in line: '" + line + "'.\n");
			while (sc2.hasNext())
				sc2.nextLine();
		}
		// CUSTOM EXCEPTIONS
		catch (InvalidOrderTimeStampException invalidTimestamp) {
			System.err.print(filename + ": Wrong format of timestamp!\n"
					+ "Timestamp must have this format (0-24)(0-59)(0-59)(1-31)(1-12) (hhmmssddMM) !\n"
					+ "Error found in line: '" + line + "'\n");
			while (sc2.hasNext())
				sc2.nextLine();
		} catch (InvalidOrderCustomerIDException invalidCustomer) {
			System.err.print(filename + ": Wrong format of customer's ID!\nError found in line: '" + line + "'\n");
			while (sc2.hasNext())
				sc2.nextLine();
		} catch (InvalidOrderCustomerNameException invalidCustomer) {
			System.err.print(filename + ": Wrong format of customer's name!\nError found in line: '" + line + "'\n");

			while (sc2.hasNext())
				sc2.nextLine();
		}
		sc2.close();
		return orderList;

	}
	
	
	// getting item for Order's constructor
	public Item findItem(String itemID) {

		for (Item item : itemList) {
			if (item.getItemID().equals(itemID)) {
				return item;
			}
		}
		return null;
	}

}
