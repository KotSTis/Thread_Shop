package testing;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeSet;
import org.junit.Test;

import shop.CsvReader;
import shop.Item;
import shop.Order;

public class CsvReaderTest {

	ArrayList<Order> orderFile = new ArrayList<Order>();
	TreeSet<Item> menuFile = new TreeSet<Item>();

	@Test
	public void testReadOrdersInfo() throws FileNotFoundException {
		CsvReader reader = new CsvReader();
		String filename = "Orders.csv";

		orderFile = reader.readOrdersInfo(filename);
		assertEquals(orderFile, reader.readOrdersInfo(filename));
	}

	@Test
	public void testReadMenuInfo() throws FileNotFoundException {
		CsvReader reader = new CsvReader();
		String filename = "Menu.csv";
		menuFile = reader.readMenuInfo(filename);
		assertEquals("Menu.csv", filename);
	}

	@Test
	public void testFindItem() throws Exception {
		String itemID = "FOOD1254";
		Item item = new Item("Guac 'n Chips", "Homemade guacamole with crispy corn tortilla and plantain chips", 5.29,
				"FOOD1254", "Food");
		assertEquals(item.getItemID(), itemID);
	}
}