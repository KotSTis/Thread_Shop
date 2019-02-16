package shop;

import java.util.HashMap;

public class Order {
	private String TimeStamp;
	private String CustomerID;
	private HashMap<String,Integer> Items;
	
	
	public Order(String TimeStamp, String CustomerID){
		
		this.TimeStamp = TimeStamp;
		this.CustomerID = CustomerID;
		this.Items = new HashMap<String,Integer>();
	}
	
	public Order(String TimeStamp, String CustomerID, String itemID){
		
		this.TimeStamp = TimeStamp;
		this.CustomerID = CustomerID;
		this.Items.put(itemID, 1);
		
	}

	public String getTimeStamp() {
		return TimeStamp;
	}
	
	public String getItemDescription(Item item) {
		return item.getDescription();
	}
	
	public void setTimeStamp(String TimeStamp) {
		this.TimeStamp = TimeStamp;
	}
	
	public void setCustomerID(String CustomerID) {
		this.CustomerID = CustomerID;
	}
	
	public String getCustomerID() {
		return this.CustomerID;
	}

	public void addItem (Item item, int quantity) {
		if(this.Items.containsKey(item.getItemID()))
			this.Items.put(item.getItemID(), this.Items.get(item.getItemID()) + quantity);
		else
			this.Items.put(item.getItemID(), quantity);
	}
	public void addItem (String itemID, int quantity) {
		if(this.Items.containsKey(itemID))
			this.Items.put(itemID, this.Items.get(itemID) + quantity);
		else
			this.Items.put(itemID, quantity);
	}
	
	public HashMap<String,Integer> getItems() {
		return Items;
	}
}
