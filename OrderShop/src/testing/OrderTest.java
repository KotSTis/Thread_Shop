package testing;

import static org.junit.Assert.*;

import java.util.HashMap;
import org.junit.Test;

import shop.Item;
import shop.Order;

public class OrderTest {

	@Test
	public void testGetTimeStamp() {
		Item item = new Item("Guac 'n Chips", "Homemade guacamole with crispy corn tortilla and plantain chips", 5.29,
				"FOOD1254", "Food");
		Order order = new Order("1134270902", "CUST1009", item);
		String result = order.getTimeStamp();
		assertEquals(result, "1134270902");
	}

	@Test
	public void testGetItemDescription() {
		Item item = new Item("Grilled Top Sirloin Steak",
				"Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli", 17.49,
				"FOOD2435", "Food");
		Order order = new Order("1523580902", "CUST1544", item);
		String result = order.getItemDescription(item);
		assertEquals(result, "Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli");

	}

	@Test
	public void testGetCustomerID() {
		Item item = new Item("Grilled Top Sirloin Steak",
				"Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli", 17.49,
				"FOOD2435", "Food");
		Order order = new Order("1523580902", "CUST1544", item);
		String result = order.getCustomerID();
		assertEquals(result, "CUST1544");
	}

	@Test
	public void testAddItemItemIdAndQuantity() {
		Item item = new Item("Guac 'n Chips", "Homemade guacamole with crispy corn tortilla and plantain chips", 5.29,
				"FOOD1254", "Food");
		Order order = new Order("1134270902", "CUST1009");
		int quantity = 4;

		order.addItem(item, quantity);
	}

	@Test
	public void testAddItemStringItemIdAndQuantity() {
		String itemID = "FOOD1254";
		Order order = new Order("1134270902", "CUST1009");
		int quantity = 4;

		order.addItem(itemID, quantity);
	}

	@Test
	public void testGetItem() {
		Item item = new Item("Chicken quesadilla", "Sliced chicken with onions sweet peppers and spinach", 10.99,
				"FOOD1005", "Food");
		Order order = new Order("1235490802", "CUST1009", item);

		Item result = order.getItem();
		assertEquals(result, item);
	}

	@Test
	public void testGetItems() throws Exception {
		Item item = new Item("Guac 'n Chips", "Homemade guacamole with crispy corn tortilla and plantain chips", 5.29,
				"FOOD1254", "Food");
		Order order = new Order("1134270902", "CUST1009", item);
		HashMap<String, Integer> result = order.getItems();
		assertEquals(result, order.getItems());
	}
}