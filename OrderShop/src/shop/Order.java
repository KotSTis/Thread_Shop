package shop;

import java.util.HashMap;

public class Order {
	private String TimeStamp;
	private String CustomerID;
	private HashMap<String, Integer> Items;
	private double price;

	public Order(String TimeStamp, String CustomerID) {

		this.TimeStamp = TimeStamp;
		this.CustomerID = CustomerID;
		this.Items = new HashMap<String, Integer>();
	}

	public Order(String TimeStamp, String CustomerID, Item item) {
		this.TimeStamp = TimeStamp;
		this.CustomerID = CustomerID;
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
