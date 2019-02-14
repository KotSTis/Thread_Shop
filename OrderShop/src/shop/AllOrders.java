package shop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import shop.Item.Category;

public class AllOrders{

	private ArrayList <Order> orderList;
	private ArrayList <Item> itemList;
	private HashMap<String, ArrayList<Order>> allOrders;
	private TreeSet <Item> menu;

	AllOrders() throws FileNotFoundException {
		
		CsvReader reader = new CsvReader();
		this.orderList = reader.readOrdersInfo("Orders.csv");
		System.out.println("read orderlist");
		System.out.println(this.orderList.toArray()[1].toString());
//		this.allOrders = new HashMap<String, ArrayList<Order>>();
//		this.itemList = new ArrayList<>();
	}

	public double calculateBill() {
		double bill = 0; 
		for (Item item : itemList) {
			bill += item.getPrice() ;			
		}
		return bill;	
	}


	public String calculateFrequency() {

		String frequency = "";		
		for (Order order: orderList) {
			for (Item item: itemList){
				if (item.getItemID().equals(order.getItemID())){
					frequency += item.getName() + " has been bought "  + " times.\n";
				}
			}
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
				OrderDetails +=  " x" + order.getItemID() +  " (" + getDescription(order.getItemID()) + ") at " + order.getTimeStamp().substring(0, 2)  +
				":" + order.getTimeStamp().substring(2, 4)  + ":" + order.getTimeStamp().substring(4, 6)  +
				" in " + order.getTimeStamp().substring(6, 8) + "/" + order.getTimeStamp().substring(8, 10) +".\n" ;	
		}		

		return OrderDetails + "\n";
	}

	public Item findByItemID(String itemID) {

		for (Item item : itemList) {

			if (itemID.equals(item.getItemID()))
				return item;
		}

		return null;
	}

	public String getAllCustomerOrders(){
		String OrderDetails = "\nCustomer ";

		for (Order order : orderList) {


			OrderDetails += order.getCustomerID() + "ordered:\n"  + " x" + order.getItemID() +  " (" + ") at " + order.getTimeStamp().substring(0, 2)  +
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
			if(itemID.equals(order.getItemID())){
				for (Item item : itemList) {
					if(itemID.equals(item.getItemID())){
						description += item.getDescription();
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
