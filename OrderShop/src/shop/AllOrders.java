package shop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import shop.Item.Category;
import java.util.concurrent.ThreadLocalRandom;



public class AllOrders{

	private ArrayList <Order> orderList;
	private HashMap <String, Item> itemList;
	private HashMap<String, ArrayList<Order>> allOrders;
	private HashMap<String,Integer> summary;


	AllOrders() throws FileNotFoundException {
		this.itemList = new HashMap<String,Item>();
		CsvReader reader = new CsvReader();
		this.orderList = reader.readOrdersInfo("Orders.csv");
		System.out.println("read orderlist");
		System.out.println(this.orderList.toArray()[1].toString());
		TreeSet <Item> menu;
		menu = reader.readMenuInfo("Menu.csv");
		Iterator<Item> iterator;
		iterator = menu.iterator();
	    while (iterator.hasNext()) {
	    	Item item = iterator.next(); 
	        itemList.put(item.getItemID(), item);
	    }
		
//		this.allOrders = new HashMap<String, ArrayList<Order>>();
//		this.itemList = new ArrayList<>();
	}

	public void makeOrder(HashMap<Item,Integer> incoming) {
		Item item;
		ArrayList<Order> ord = null;
		int quantity;
		String custID = "CUST" + ThreadLocalRandom.current().nextInt(0, 5000 + 1);
		String timestamp = "1235490802";
		Order newOrder = new Order(timestamp, custID);
		for (HashMap.Entry<Item,Integer> entry : incoming.entrySet())
		{
		    item = entry.getKey();
		    quantity = entry.getValue();
		    if(summary.containsKey(item.getItemID()))
		    	summary.put(item.getItemID(), summary.get(item.getItemID()) + quantity);
		    else
		    	summary.put(item.getItemID(), quantity);
		    
		    
		    newOrder.addItem(item, quantity);
		    
		}
		Double price = calculateBill(newOrder);
		if ( allOrders.containsKey(custID))
	    	allOrders.get(custID).add(newOrder);
	    else
	    	ord = new ArrayList <Order>();
			ord.add(newOrder);
			allOrders.put(custID, ord);
		
		
	}
	
	public double calculateBill(Order order) {
		double bill = 0; 
		for (HashMap.Entry<String,Integer> entry : order.getItems().entrySet())
		{
		    String ID = entry.getKey();
		    Integer quantity = entry.getValue();
		    bill += itemList.get(ID).getPrice() * quantity;
		}
		
		return bill;	
	}


	public String calculateFrequency() {

		String frequency = "";		
		for (HashMap.Entry<String,Integer> entry : summary.entrySet())
		{
		    String item = entry.getKey();
		    Integer quantity = entry.getValue();
		    frequency += "item " + item + " was bought " + quantity + " times\n";
		}

		return frequency;
	}

	//	
	//	public String getOrderDetails() {
	//		String getDetails = "";
	//		for (Order order: orderList) {
	//			 getDetails = order.getItemID() + order.getQuantity();
	//		}
	//		return getDetails;
	//	}

	public String findByCustomerID(String customer) {
		String OrderDetails = "Customer " + customer + " ordered:\n";
		for (Order order : orderList) {
		
			if (customer.equals(order.getCustomerID()))
				for (HashMap.Entry<String,Integer> entry : order.getItems().entrySet())
				{
				    String ID = entry.getKey();
				    Integer quantity = entry.getValue();
				    OrderDetails +=  quantity + " x " + ID + " (" + getDescription(ID) + ")\n" ;
				    
				}
				OrderDetails += " at " + order.getTimeStamp().substring(0, 2)  +
				":" + order.getTimeStamp().substring(2, 4)  + ":" + order.getTimeStamp().substring(4, 6)  +
				" in " + order.getTimeStamp().substring(6, 8) + "/" + order.getTimeStamp().substring(8, 10) +".\n" ;	
		}		

		return OrderDetails + "\n";
	}

	public String getAllCustomerOrders(){
		String OrderDetails = "\nCustomer ";
		
		for (Order order : orderList) {
			OrderDetails += order.getCustomerID() + "ordered:\n" ;
			for (HashMap.Entry<String,Integer> entry : order.getItems().entrySet())
			{
			    String ID = entry.getKey();
			    Integer quantity = entry.getValue();
			    OrderDetails +=  quantity + " x " + ID + " (" + getDescription(ID) + ")\n" ;
			    
			}


			OrderDetails += " at " + order.getTimeStamp().substring(0, 2)  +
					":" + order.getTimeStamp().substring(2, 4)  + ":" + order.getTimeStamp().substring(4, 6)  +
					" in " + order.getTimeStamp().substring(6, 8) + "/" + order.getTimeStamp().substring(8, 10) +".\n";


		}
		return OrderDetails;

	}

	/*public String getOrderDescription (String customer){
		String orderDescription = "";
		for (Order order: orderList){
			for (Item item : itemList){
				if (customer.equals(order.getCustomerID())){
					orderDescription = this.getDescription(item.getItemID());
				}
			}
		}
		return orderDescription;
	}

	 */

	public String getDescription(String itemID) {
		String description = "";
		for (Order order : orderList) {
			if(order.getItems().containsKey(itemID)){
				for (HashMap.Entry<String,Item> entry : itemList.entrySet()) {
					if(itemID.equals(entry.getKey())){
						description += entry.getValue().getDescription();
						return description;
					}
				}
			}
		}
		return null;
	}



	public void FinalReport(String filename, String customer) throws IOException {
		FileWriter fw = new FileWriter(filename);

		fw.write(findByCustomerID (customer));
		fw.write(this.calculateFrequency());
		fw.write(getAllCustomerOrders());
		fw.close();
	}
	/*
	public int getQuantity() {
		int sum = 0;
		for (Order order : orderList) {
			for (Item item : itemList) {
				if (item.getItemID().equals(order.getItemID().substring(0, 8)) && (itemID.equals(item.getItemID())
						&& customer.equals(order.getCustomerID()) && timestamp.equals(order.getTimeStamp()))) {

					sum++;
				}
			}
		}
		return sum;
	}*/
}
