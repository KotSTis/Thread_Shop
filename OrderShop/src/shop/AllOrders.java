package shop;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class AllOrders {

	private ArrayList<Order> orderList;
	private HashMap<String, Item> itemList;
	private HashMap<String, ArrayList<Order>> allOrders = new HashMap<String, ArrayList<Order>> ();
	private HashMap<String, Integer> summary = new HashMap<String, Integer>();
	private HashMap<String, ArrayList<String>>incoming = new HashMap<String, ArrayList<String>>();
	

	public AllOrders() throws FileNotFoundException {

		this.itemList = new HashMap<String, Item>();
		CsvReader reader = new CsvReader();
		TreeSet <Item> menu;
		menu = reader.readMenuInfo("Menu.csv");
		Iterator<Item> iterator;
		iterator = menu.iterator();
	    while (iterator.hasNext()) {
	    	Item item = iterator.next(); 
	        itemList.put(item.getItemID(), item);
	    }
		this.orderList = reader.readOrdersInfo("Orders.csv");
		for(Order newOrd: this.orderList) {
			if (allOrders.containsKey(newOrd.getCustomerID())) {
				allOrders.get(newOrd.getCustomerID()).add(newOrd);
			}else {
				ArrayList<Order> ord = new ArrayList<Order>();
				ord.add(newOrd);
				allOrders.put(newOrd.getCustomerID(), ord);
			}
			for (Entry<String,Integer> entry : newOrd.getItems().entrySet()) {
				String item = entry.getKey();
				Integer quantity = entry.getValue();
				//adding items sold to summary for the end
				if(summary.containsKey(item))
			    	this.summary.put(item, summary.get(item) + quantity);
			    else
			    	this.summary.put(item, quantity);

			}
		}
		for(Order ord: this.orderList) {
			ord.setPrice(calculateBill(ord));
		}
		
	}

	public String makeOrder(HashMap<Item, Integer> incoming) {
	
		ArrayList<Order> ord = null;
		String custID = "CUST" + ThreadLocalRandom.current().nextInt(0, 5000 + 1);
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM");  
		Date date = new Date();  
		String timestamp = formatter.format(date);  
		timestamp = timestamp.replace(":", "");
		timestamp = timestamp.replace("/", "");
		timestamp = timestamp.replace(" ", "");
		System.out.println(timestamp);
		Order newOrder = new Order(timestamp, custID);
		Item item;
		int quantity;
		for (Entry<Item,Integer> entry : incoming.entrySet()) {
			item = entry.getKey();
			quantity = entry.getValue();
			//adding items sold to summary for the end
			if(summary.containsKey(item.getItemID()))
		    	this.summary.put(item.getItemID(), summary.get(item.getItemID()) + quantity);
		    else
		    	this.summary.put(item.getItemID(), quantity);
			newOrder.addItem(item, quantity);
			newOrder.setPrice(calculateBill(newOrder));
			this.orderList.add(newOrder);

		}	
		//group orders by customer id also for printing in the end
		if (allOrders.containsKey(custID)) {
			allOrders.get(custID).add(newOrder);
		}else {
			ord = new ArrayList<Order>();
			ord.add(newOrder);
			allOrders.put(custID, ord);
		
		}
		
		return null;
		
	}

//	public String getNewOrders(HashMap<String, ArrayList<String>>incoming) throws FileNotFoundException  {
//		String nO = "";
//		GUI gui = new GUI();
//		incoming = gui.outcoming();
//		for (HashMap.Entry<String, ArrayList<String>> entry : incoming.entrySet()) {
//
//		}
//		nO = "" + incoming;
//		return nO;
//		
//	}

	public double calculateBill(Order order) {
		double bill = 0;
		for (HashMap.Entry<String, Integer> entry : order.getItems().entrySet()) {
			String ID = entry.getKey();
			Integer quantity = entry.getValue();
			bill += itemList.get(ID).getPrice() * quantity;
		}

		if(80 >= bill && bill > 50) {
			bill *= 0.95;
		}else if( 100 >= bill && bill > 80) {
			bill *= 0.9;
		}else if(bill > 100) {
			bill *= 0.85;
		}
		System.out.println(bill);
		return bill;
	}

	public String calculateFrequency() {

		String frequency = "";
		for (HashMap.Entry<String, Integer> entry : summary.entrySet()) {
			String item = entry.getKey();
			Integer quantity = entry.getValue();
			frequency += "item " + item + " was bought " + quantity + " times\n";
		}

		return frequency;
	}

	public String findByCustomerID(String customer) {
		String OrderDetails = "Customer " + customer + " ordered:\n";
		for (Order order : orderList) {

			if (customer.equals(order.getCustomerID()))
				for (HashMap.Entry<String, Integer> entry : order.getItems().entrySet()) {
					String ID = entry.getKey();
					Integer quantity = entry.getValue();
					OrderDetails += quantity + " x " + ID + " (" + getDescription(ID) + ")\n";

				}
			OrderDetails += " at " + order.getTimeStamp().substring(0, 2) + ":" + order.getTimeStamp().substring(2, 4)
					+ ":" + order.getTimeStamp().substring(4, 6) + " in " + order.getTimeStamp().substring(6, 8) + "/"
					+ order.getTimeStamp().substring(8, 10) + ".\n";
		}

		return OrderDetails + "\n";
	}

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

//	public int quantity(Item item) {
//		int timesOrdered = 0;
//		for (Order order : orderList) {
//
//			if (order.getItem() == item) {
//				timesOrdered++;
//			}
//		}
//		return timesOrdered;
//	}

	public String getDescription(String itemID) {
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

	public void FinalReport(String filename) throws IOException {

		FileWriter fw = new FileWriter(filename);
		fw.write("Items bought in ascending order by itemID:\n\n");
		for (HashMap.Entry<String, Integer> entry : this.summary.entrySet()) {
			Item item = this.itemList.get(entry.getKey());
			fw.write(item.getMenu() + " (" + item.getDescription() + ").\nItem " + item.getItemID() + " is ordered "
					+ entry.getValue() + " times.\n");
		}
		//fw.write("\n" + makeOrder(incoming));
		
		String OrderDetails = "\nCustomer ";
		for (Order order : orderList) {
			OrderDetails += order.getCustomerID() + " ordered:\n";
			for (HashMap.Entry<String, Integer> entry : order.getItems().entrySet()) {
				String ID = entry.getKey();
				Integer quantity = entry.getValue();
				OrderDetails += quantity + " x " + ID + " (" + getDescription(ID) + ")\n";

			}
			if(order.getTimeStamp().equals("1922592002"))
				System.out.println("yo");
			OrderDetails += " at " + order.getTimeStamp().substring(0, 2) + ":" + order.getTimeStamp().substring(2, 4)
					+ ":" + order.getTimeStamp().substring(4, 6) + " in " + order.getTimeStamp().substring(6, 8) + "/"
					+ order.getTimeStamp().substring(8, 10) + ".\n"
					+ "Total Price: " + (float) order.getPrice() + "\n";

		}
		fw.write(OrderDetails);
		
		fw.close();

	}

}
