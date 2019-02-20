package shop;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class AllOrders {

	private ArrayList<Order> orderList;
	private HashMap<String, Item> itemList;
	private HashMap<String, ArrayList<Order>> allOrders = new HashMap<String, ArrayList<Order>> ();
	private HashMap<String, Integer> summary;
	private TreeSet<Item> existingOrders = new TreeSet<Item>();
	private HashMap<String, ArrayList<String>>incoming = new HashMap<String, ArrayList<String>>();
	

	public AllOrders() throws FileNotFoundException {

		this.itemList = new HashMap<String, Item>();
		CsvReader reader = new CsvReader();
		this.existingOrders = reader.readMenuInfo("Menu.csv");
		this.orderList = reader.readOrdersInfo("Orders.csv");
	}

	public String makeOrder(HashMap<String, ArrayList<String>> incoming) {
	
		ArrayList<Order> ord = null;
		String custID = "CUST" + ThreadLocalRandom.current().nextInt(0, 5000 + 1);
		String timestamp = "1235490802";
		Order newOrder = new Order(timestamp, custID);
		for (Entry<String, ArrayList<String>> entry : incoming.entrySet()) {
			custID = entry.getKey();
			incoming.values();

		}
		String x = "";
		incoming.size();
		if (allOrders.containsKey(custID))
			allOrders.get(custID).add(newOrder);
		else
			ord = new ArrayList<Order>();
		ord.add(newOrder);
		allOrders.put(custID, ord);
		x += incoming.size();
		
		return x;
		
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

	public int quantity(Item item) {
		int timesOrdered = 0;
		for (Order order : orderList) {

			if (order.getItem() == item) {
				timesOrdered++;
			}
		}
		return timesOrdered;
	}

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
		for (Item item : existingOrders) {
			fw.write(item.getMenu() + " (" + item.getDescription() + ").\nItem " + item.getItemID() + " is ordered "
					+ quantity(item) + " times.\n");
		}
		fw.write("\n" + makeOrder(incoming));
		fw.close();

	}

}
