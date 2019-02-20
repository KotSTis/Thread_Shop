package testing;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import shop.AllOrders;

import shop.GUI;
import shop.Item;
import shop.Order;

public class AllOrdersTest {

	
	
	@Test
	public void testMakeOrder() {

		Item item = new Item("Guac 'n Chips", "Homemade guacamole with crispy corn tortilla and plantain chips", 5.29,
				"FOOD1254", "Food");
		HashMap<Item, Integer> incoming = new HashMap<Item, Integer>();

		assertTrue(incoming.containsKey(item) && incoming.get(item) != null);
	}

//	@Test
//	public void testGetNewOrders() throws FileNotFoundException {
//		String newOrders = "";
//		GUI gui = new GUI();
//		newOrders += gui.outcoming();
//		assertEquals(newOrders, "{CUST2730=[Honey Bun x 1 (7.99�), Pinot Grigio x 1 (30.0�)]}");
//	}

	@Test
	public void testCalculateBill() {
		Item item = new Item("Guac 'n Chips", "Homemade guacamole with crispy corn tortilla and plantain chips", 5.29,
				"FOOD1254", "Food");
		int quantity = 4;
		double bill = 21.16;
		assertEquals(bill, quantity * item.getPrice(), 0.0);
	}

	@Test
	public void testCalculateFrequency() {
		Item item = new Item("Guac 'n Chips", "Homemade guacamole with crispy corn tortilla and plantain chips", 5.29,
				"FOOD1254", "Food");
		String itemBought = item.getItemID();
		int quantity = 4;

		String frequency = "item " + itemBought + " was bought " + quantity + " times\n";
		String quantityMessage = "item " + "FOOD1254" + " was bought " + 4 + " times\n";
		assertEquals(frequency, quantityMessage);
	}

	@Test
	public void testFindByCustomerID() {
		Item item = new Item("Grilled Top Sirloin Steak",
				"Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli", 17.49,
				"FOOD2435", "Food");
		Order order = new Order("1523580902", "CUST1544", item);
		String customer = "CUST1544";

		String OrderDetails = "Customer" + order.getCustomerID() + "ordered " + item.getItemID() + "at"
				+ order.getTimeStamp().substring(0, 2) + ":" + order.getTimeStamp().substring(2, 4) + ":"
				+ order.getTimeStamp().substring(4, 6) + " in " + order.getTimeStamp().substring(6, 8) + "/"
				+ order.getTimeStamp().substring(8, 10) + ".\n";
		String result = "Customer" + customer + "ordered " + "FOOD2435" + "at" + "15" + ":" + "23" + ":" + "58" + " in "
				+ "09" + "/" + "02" + ".\n";
		assertEquals(result, OrderDetails);
	}

	@Test
	public void testGetAllCustomerOrders() {
		Item item = new Item("Grilled Top Sirloin Steak",
				"Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli", 17.49,
				"FOOD2435", "Food");
		Order order = new Order("1523580902", "CUST1544", item);
		String customer = "CUST1544";

		String OrderDetails = "Customer" + order.getCustomerID() + "ordered " + item.getItemID() + "at"
				+ order.getTimeStamp().substring(0, 2) + ":" + order.getTimeStamp().substring(2, 4) + ":"
				+ order.getTimeStamp().substring(4, 6) + " in " + order.getTimeStamp().substring(6, 8) + "/"
				+ order.getTimeStamp().substring(8, 10) + ".\n";
		String result = "Customer" + customer + "ordered " + "FOOD2435" + "at" + "15" + ":" + "23" + ":" + "58" + " in "
				+ "09" + "/" + "02" + ".\n";
		assertEquals(result, OrderDetails);
	}

	@Test
	public void testQuantity() {
		Item item = new Item("Guac 'n Chips", "Homemade guacamole with crispy corn tortilla and plantain chips", 5.29,
				"FOOD1254", "Food");
		Order order = new Order("1523580902", "CUST1544", item);
		int quantity = 1;
		int timesOrdered = 0;
		if (order.getItem() == item) {
			timesOrdered++;
		}

		assertEquals(quantity, timesOrdered);
	}

	@Test
	public void testGetDescription() throws FileNotFoundException {
		Item item = new Item("Guac 'n Chips", "Homemade guacamole with crispy corn tortilla and plantain chips", 5.29,
				"FOOD1254", "Food");
		String description = item.getDescription();
		assertEquals(description, "Homemade guacamole with crispy corn tortilla and plantain chips");
	}

	@Test
	public void testFinalReport() throws IOException {
		Item item = new Item("Black Bean BBQ Burger",
				"Glazed with guava BBQ sauce and served with melted pepper jack cheese", 11.29, "FOOD5476", "Food");

		AllOrders allOrders = new AllOrders();
		String details = item.getMenu() + " (" + item.getDescription() + ").\nItem " + item.getItemID() + " is ordered "
				+ allOrders.quantity(item) + " times.\n";
		String itemDetails = "Black Bean BBQ Burgercosts11.29�." + " ("
				+ "Glazed with guava BBQ sauce and served with melted pepper jack cheese" + ").\nItem " + "FOOD5476"
				+ " is ordered " + 3 + " times.\n";
		assertEquals(details, itemDetails);
	}
}