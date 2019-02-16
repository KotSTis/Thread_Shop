package shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class CsvReader {
	
	CsvReader(){
		
		
	}
	
	public ArrayList <Order> readOrdersInfo(String filename) throws FileNotFoundException {
		ArrayList <Order> orderList = new ArrayList<Order>();
		File file2 = new File(filename);
		Scanner sc2 = new Scanner(file2);

		String line = sc2.nextLine();
		while (sc2.hasNextLine()) {
			line = sc2.nextLine();
			String[] values = line.split(",");
			String timestamp = values[0];
			String customerID = values[1];
			String itemOrdered = values[2];
			Order OrderedItem = new Order(timestamp, customerID, itemOrdered);
			for (Order ord: orderList)
				if(ord.getCustomerID() == customerID && ord.getTimeStamp() == timestamp)
					ord.addItem(itemOrdered, 1);
			orderList.add(OrderedItem);
		}
		sc2.close();
		return orderList;

	}


	public TreeSet <Item> readMenuInfo(String filename) throws FileNotFoundException{
		TreeSet <Item> itemList = new TreeSet <Item> (new ItemComparator());
		File file1 = new File(filename);
		Scanner sc1 = new Scanner(file1);
		// we don't need a Map since we don't have a key for the menu, we don't need a set since we don't want to get rid of the duplicates
		String line = sc1.nextLine();
		while (sc1.hasNextLine()) {
			line = sc1.nextLine();
			String[] values = line.split(",");
			String name = values[0];
			String description = values[1];
			double price = Double.parseDouble(values[2]);
			String itemID = values[3];
			String category = values[4];


			Item menuItem = new Item(name, description, price, itemID, category);
			itemList.add(menuItem);
		}	
		sc1.close();
		return itemList;	
	}
}
