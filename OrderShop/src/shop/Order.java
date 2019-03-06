package shop;

import java.util.HashMap;

// This is the Order class that handles the features of each order
public class Order {
	private String TimeStamp;
	private String CustomerID;
	private String CustomerName;
	private HashMap<String, Integer> Items;
	private double price;

	public Order(String TimeStamp, String CustomerID, String CustomerName) {

		this.TimeStamp = TimeStamp;
		this.CustomerID = CustomerID;
		this.CustomerName = CustomerName;
		this.Items = new HashMap<String, Integer>();
	}

	public Order(String TimeStamp, String CustomerID, String CustomerName, Item item) {
		this.TimeStamp = TimeStamp;
		this.CustomerID = CustomerID;
		this.CustomerName = CustomerName;
		this.Items = new HashMap<String, Integer>();
		this.Items.put(item.getItemID(), 1);

	}
	
	public String getTimeStamp() {
		return TimeStamp;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double p) {
		this.price = p;
	}

	public String getItemDescription(Item item) {
		return item.getDescription();
	}

	public String getCustomerID() {
		return this.CustomerID;
	}
	
	public String getCustomerName() {
		return this.CustomerName;
	}

	public void addItem(Item item, int quantity) {
		if (this.Items.containsKey(item.getItemID()))
			this.Items.put(item.getItemID(), this.Items.get(item.getItemID()) + quantity);
		else
			this.Items.put(item.getItemID(), quantity);
	}

	public HashMap<String, Integer> getItems() {
		return Items;
	}
}
