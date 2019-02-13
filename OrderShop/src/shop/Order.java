package shop;
public class Order {
	private String TimeStamp;
	private String CustomerID;
	private String ItemID;
	private int Quantity;
	private Menu menu;
	
	
	public Order(String TimeStamp, String CustomerID,  String ItemID){
		
		this.TimeStamp = TimeStamp;
		this.CustomerID = CustomerID;
		this.ItemID = ItemID;
	}

	public Menu getmenu() {
		return menu;
	}
	public String getTimeStamp() {
		return TimeStamp;
	}
	
	public String getItemID() {
		return ItemID;
	}
	
	public String getCustomerID() {
		return CustomerID;
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

	public void add () {
		
	}
	
}
