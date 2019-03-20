/* author: Mitrousis Alexandros
 * All copyrights reserved 2019-2020
 */
package shop;

import java.util.HashMap;

// This is the Order class that handles the features of each order
public class Order {

	private String timeStamp;
	private String customerID;
	private String customerName;
	private HashMap<String, Integer> items;
	private double price;
	private int priority;

	// Constructor for timestamp and customerID (needed for allOrders)
	public Order(String TimeStamp, String CustomerID, String CustomerName) {

		this.timeStamp = TimeStamp;
		this.customerID = CustomerID;
		this.customerName = CustomerName;
		this.items = new HashMap<String, Integer>();
		this.priority = 0;
	}

	// Constructor for timestamp, customerID and item (needed to read orders from Orders.csv)
	public Order(String TimeStamp, String CustomerID, Item item, String CustomerName) {
		this.timeStamp = TimeStamp;
		this.customerID = CustomerID;
		this.items = new HashMap<String, Integer>();
		this.items.put(item.getItemID(), 1);
		this.customerName = CustomerName;
		this.priority = 0;
	}
	
	// Constructor for timestamp, customerID and item (needed to read orders from Orders.csv)
	public Order(String TimeStamp, String CustomerID, Item item, String CustomerName, int prior) {
		this.timeStamp = TimeStamp;
		this.customerID = CustomerID;
		this.items = new HashMap<String, Integer>();
		this.items.put(item.getItemID(), 1);
		this.customerName = CustomerName;
		this.priority = prior;
	}
	
	// Getter for order's timestamp
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public int getPriority(){
		return priority;
	}
	
	// Getter for order's price
	public double getPrice() {
		return price;
	}


	// Setter for order's price

	public void setPrice(double p) {
		this.price = p;
	}


	// Getter to get item's description
	public String getItemDescription(Item item) {
		return item.getDescription();
	}

	// Getter for customer ID
	public String getCustomerID() {
		return this.customerID;
	}
	
	public String getCustomerName() {
		return this.customerName;
	}

	// This method is needed for AllOrders class where we add order's items and quantity for each order
	public void addItem(Item item, int quantity) {
		if (this.items.containsKey(item.getItemID()))
			this.items.put(item.getItemID(), this.items.get(item.getItemID()) + quantity);
		else
			this.items.put(item.getItemID(), quantity);
	}

	// Getter for items stored in a hashmap
	public HashMap<String, Integer> getItems() {
		return items;

	}
}
