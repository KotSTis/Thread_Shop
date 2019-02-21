package testing;

import static org.junit.Assert.*;
import shop.*;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;

public class OrderTest {

	private Order o1;
	private Order o2;
	private Item i = new Item("Blue Mountain","Jamaican Blue Mountain Coffee",12.50,"BEVE2367","beverage");
	
	private String TimeStamp = "1235490802";
	private String CustomerID = "CUST1009";
	
	private HashMap<String,Integer> Items;
	
	
	@Before
	public void setUp() throws Exception {
		o1 = new Order(TimeStamp,CustomerID);
		o2 = new Order(TimeStamp,CustomerID,i);
	}

	@Test
	public void testGetTimeStamp() {
		assertEquals("1235490802",o1.getTimeStamp());
	}

	@Test
	public void testGetItemDescription() {
		i = new Item("Blue Mountain","Jamaican Blue Mountain Coffee",12.50,"BEVE2367","beverage");
		assertEquals("Jamaican Blue Mountain Coffee",o1.getItemDescription(i));
	}

	@Test
	public void testGetCustomerID() {
		assertEquals("CUST1009",o1.getCustomerID());
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
