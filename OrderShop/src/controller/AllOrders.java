/* author: Stergiou Konstantinos
 * All copyrights reserved 2019-2020
 */

package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import model.QueueCustomer;
import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidOrderCustomerIDException;
import ourExceptions.InvalidOrderCustomerNameException;
import ourExceptions.InvalidOrderTimeStampException;
import ourExceptions.InvalidPriceException;
import shop.CsvReader;
import shop.GUI;
import shop.Item;
import shop.Log;
import shop.Menu;
import shop.Order;
import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;

// This the AllOrders that handles all orders
public class AllOrders {

	private GUI view;
	private QueueCustomer model;
	private Log logger;

	Menu m = new Menu();
	//ArrayList to hold All of the Orders placed
	private ArrayList<Order> orderList;
	TreeSet<Item> menu;
	private String[] firstNames = new String[20];
	private String[] lastNames = new String[20];
	//Holds a copy of the menu in Hashmap form for easy lookup and access of items using their ID
	private HashMap<String, Item> itemList;
	//String is the Customer ID and the ArrayList<Orders> holds all orders made by that customer
	private HashMap<String, ArrayList<Order>> allOrders = new HashMap<String, ArrayList<Order>>();
	//holds how many times each item was sold
	private HashMap<String, Integer> summary = new HashMap<String, Integer>();

	public AllOrders(GUI v, QueueCustomer q, Log lg) 
			throws FileNotFoundException, InvalidPriceException, InvalidCategoryException,
			InvalidOrderTimeStampException, InvalidOrderCustomerIDException, InvalidOrderCustomerNameException, InvalidItemIDLengthException, InvalidItemException {

		this.view = v;
		this.model = q;
		this.logger = lg;
		view.addOrderListender(new addListener());
		view.AddOnlineListener(new addOnlineListener());
		this.itemList = new HashMap<String, Item>();
		CsvReader reader = new CsvReader();
		menu = reader.readMenuInfo("Menu.csv");
		this.firstNames = reader.readFirstNames("Random-Names.csv");
		this.lastNames = reader.readLastNames("Random-Names.csv");

		//iterate through the treeset menu
		Iterator<Item> iterator;
		iterator = menu.iterator();
		while (iterator.hasNext()) {
			Item item = iterator.next();
			itemList.put(item.getItemID(), item);
		}
		//processing all the orders read from the csv Orders file
		this.orderList = reader.readOrdersInfo("Orders.csv");
		for (Order newOrd : this.orderList) {
			//check if there's an order with the same customerID in the orderList
			if (allOrders.containsKey(newOrd.getCustomerID())) {
				//if the customer exists, add this order to their arrayList
				allOrders.get(newOrd.getCustomerID()).add(newOrd);
			} else {
				//otherwise create new entry in the HashMap
				ArrayList<Order> ord = new ArrayList<Order>();
				ord.add(newOrd);
				allOrders.put(newOrd.getCustomerID(), ord);
			}
			//add all the items and their quantities that exist in each order to the summary Hashmap
			for (Entry<String, Integer> entry : newOrd.getItems().entrySet()) {
				String item = entry.getKey();
				Integer quantity = entry.getValue();
				// adding items sold to summary for the end
				if (summary.containsKey(item))
					this.summary.put(item, summary.get(item) + quantity);
				else
					this.summary.put(item, quantity);

			}
		}
		//calculate the total price of the order
		for (Order ord : this.orderList) {
			ord.setPrice(calculateBill(ord));
			model.addQueue(ord);
			try {TimeUnit.SECONDS.sleep(2);} 
			catch (InterruptedException e) {e.printStackTrace();}
		}
		Frame frame= GUI.getFrames()[1];
		frame.setVisible(true);
		((JFrame) frame).getContentPane().setVisible(true);
	}
	
	// This method process new orders
	public Order makeOrder(HashMap<Item, Integer> incoming) {
		
		ArrayList<Order> ord = null;
		//create customer id and timestamp for the order
		String custID = "CUST" + ThreadLocalRandom.current().nextInt(0, 5000 + 1);
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM");
		Date date = new Date();
		String timestamp = formatter.format(date);
		timestamp = timestamp.replace(":", "");
		timestamp = timestamp.replace("/", "");
		timestamp = timestamp.replace(" ", "");
		String customerName = firstNames[ThreadLocalRandom.current().nextInt(0, 19 + 1)] + " " 
				+ lastNames[ThreadLocalRandom.current().nextInt(0, 19 + 1)];
		Order newOrder = new Order(timestamp, custID, customerName);
		Item item;
		int quantity;

		for (Entry<Item, Integer> entry : incoming.entrySet()) {
			//get item and quantity
			item = entry.getKey();
			quantity = entry.getValue();
			// adding items sold to summary for the end
			if (summary.containsKey(item.getItemID()))
				this.summary.put(item.getItemID(), summary.get(item.getItemID()) + quantity);
			else
				this.summary.put(item.getItemID(), quantity);
			newOrder.addItem(item, quantity);
			newOrder.setPrice(calculateBill(newOrder));

		}
		this.orderList.add(newOrder);
		// group orders by customer id also for printing in the end
		if (allOrders.containsKey(custID)) {
			allOrders.get(custID).add(newOrder);
		} else {
			ord = new ArrayList<Order>();
			ord.add(newOrder);
			allOrders.put(custID, ord);

		}
		return newOrder;
	}

	public class addListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			HashMap<Item, Integer> inc_ord = view.getOrd();
			
			Order ord = makeOrder(inc_ord);
			ord.makeOnline();
			model.addQueue(ord);
			view.clear();
		}
	}

	public class addOnlineListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			HashMap<Item, Integer> inc_ord = view.getOrd();
			
			Order ord = makeOrder(inc_ord);
			ord.makeOnline();
			model.addQueue(ord);
			view.clear();
		}
	}

	// Calculate the price for each order
	public double calculateBill(Order order) {
		double bill = 0;
		//iterate through all the items in order and add their prices
		for (HashMap.Entry<String, Integer> entry : order.getItems().entrySet()) {
			String ID = entry.getKey();
			Integer quantity = entry.getValue();
			bill += itemList.get(ID).getPrice() * quantity;
		}
		//calculating the discounts(if they apply and how much)
		if (80 >= bill && bill > 50) {
			bill *= 0.95;
		} else if (100 >= bill && bill > 80) {
			bill *= 0.9;
		} else if (bill > 100) {
			bill *= 0.85;
		}
		return bill;
	}

	// Calculate the quantity of each ordered item
	public String calculateFrequency() {
		//pretty print each item and how many times it was sold
		String frequency = "";
		for (HashMap.Entry<String, Integer> entry : summary.entrySet()) {
			String item = entry.getKey();
			Integer quantity = entry.getValue();
			frequency += "item " + item + " was bought " + quantity + " times\n";
		}

		return frequency;
	}

	// Display all customer's orders
	public String getAllCustomerOrders() {
		String OrderDetails = "\nCustomer ";
		for (Order order : orderList) {
			OrderDetails += order.getCustomerID() + "ordered:\n";
			for (HashMap.Entry<String, Integer> entry : order.getItems().entrySet()) {
				String ID = entry.getKey();
				Integer quantity = entry.getValue();
				OrderDetails += quantity + " x " + ID + " (" + getDescription(ID) + ")\n";

			}

			OrderDetails += " at " + order.getTimeStamp().substring(0, 2) + ":" + order.getTimeStamp().substring(2, 4)
					+ ":" + order.getTimeStamp().substring(4, 6) + " in " + order.getTimeStamp().substring(6, 8) + "/"
					+ order.getTimeStamp().substring(8, 10) + ".\n";

		}

		return OrderDetails;

	}

	// Display description for each item
	public String getDescription(String itemID) {
		//get item description from the ItemList
		//not really sure where this is used
		String description = "";
		for (Order order : orderList) {
			if (order.getItems().containsKey(itemID)) {
				for (HashMap.Entry<String, Item> entry : itemList.entrySet()) {
					if (itemID.equals(entry.getKey())) {
						description += entry.getValue().getDescription();
						return description;
					}
				}
			}
		}
		return null;
	}

	// Final report to display menu (before closing GUI), 
	// current orders, new orders, and total income to a Report.txt
	public void FinalReport(String filename) throws IOException {
		//iterate through the summary Hashmap and print it out in the report file

		FileWriter fw = new FileWriter(filename);
		fw.write("Menu:\n\n");
		fw.write(m.displayMenu() + "\n\n");
		fw.write("Items sold:\n\n");
		for (HashMap.Entry<String, Integer> entry : this.summary.entrySet()) {
			Item item = this.itemList.get(entry.getKey());
			fw.write(item.getMenu() + " (" + item.getDescription() + ").\nItem " + item.getItemID() + " is ordered "
					+ entry.getValue() + " times.\n");
		}
		//Iterate through all of the orders placed and pretty print them out in the report file
		fw.write("\n\nAll of the Orders processed:\n");
		String OrderDetails = "\nCustomer ";
		double totalIncome = 0;
		for (Order order : orderList) {
			totalIncome += order.getPrice();
			OrderDetails += order.getCustomerID() + " ordered:\n";
			for (HashMap.Entry<String, Integer> entry : order.getItems().entrySet()) {
				String ID = entry.getKey();
				Integer quantity = entry.getValue();
				OrderDetails += quantity + " x " + ID + " (" + getDescription(ID) + ")\n";

			}

			OrderDetails += " at " + order.getTimeStamp().substring(0, 2) + ":" + order.getTimeStamp().substring(2, 4)
					+ ":" + order.getTimeStamp().substring(4, 6) + " in " + order.getTimeStamp().substring(6, 8) + "/"
					+ order.getTimeStamp().substring(8, 10) + ".\n" + "Total Price: " + (float) order.getPrice() + "\u00a3\n\n";

		}
		fw.write(OrderDetails);
		String income = String.format("%.2f", totalIncome);
		fw.write("\nTotal Income: " + income + "\u00a3");

		fw.close();
	}
}
