/* author: Jiaxi Lyu
 * All copyrights reserved 2019-2020
 */

package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeSet;
import org.junit.Test;

import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;
import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidOrderCustomerIDException;
import ourExceptions.InvalidOrderTimeStampException;
import ourExceptions.InvalidPriceException;
import shop.CsvReader;
import shop.Item;
import shop.Order;

public class CsvReaderTest {

	ArrayList<Order> orderFile = new ArrayList<Order>();
	TreeSet<Item> menuFile = new TreeSet<Item>();


	@Test
	public void testReadOrdersInfo() throws FileNotFoundException, InvalidOrderTimeStampException, InvalidOrderCustomerIDException {
		CsvReader reader = new CsvReader();
		String filename = "Orders.csv";

	}

	@Test
	public void testReadMenuInfo() throws FileNotFoundException, InvalidItemIDLengthException, InvalidItemException, InvalidPriceException, InvalidCategoryException {
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