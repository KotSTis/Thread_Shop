package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import shop.Item;
import org.junit.Assert;


public class ItemTest {
	
	private Item i1;
	private Item i2;
	
	@Before
	public void setUp() throws Exception {
		i1 = new Item("sushi", "A mixed sushi set", 6.99, "FOOD4534", "food");
		i2 = new Item("Blue Mountain","Jamaican Blue Mountain Coffee",12.50,"BEVE2367","beverage");

	}

	@Test
	public void testGetName() {
		Item item = new Item("Grilled Top Sirloin Steak","Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli",17.49,"FOOD2435","Food");
		String name = "Grilled Top Sirloin Steak";

		assertEquals(name,item.getName());
	}

	@Test
	public void testGetDescription()  {
		Item item = new Item("Grilled Top Sirloin Steak","Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli",17.49,"FOOD2435","Food");
		String description = "Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli";

		assertEquals(description,item.getDescription());
		
	}

	@Test
	public void testGetCategoryItem() {
		Item item = new Item("Grilled Top Sirloin Steak","Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli",17.49,"FOOD2435","Food");
		String category = "Food";
		
		assertEquals(category,item.getCategoryItem());
	}

	@Test
	public void testGetPrice() {
		Item item = new Item("Grilled Top Sirloin Steak","Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli",17.49,"FOOD2435","Food");
		double price = 17.49;

		assertEquals(price,item.getPrice(),0.0);

	}

	@Test
	public void testGetItemID() {
		Item item = new Item("Grilled Top Sirloin Steak","Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli",17.49,"FOOD2435","Food");
		String itemID = "FOOD2435";
		
		assertEquals(itemID,item.getItemID());
	}

	@Test
	public void testGetMenu() {
		Item item = new Item("Grilled Top Sirloin Steak","Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli",17.49,"FOOD2435","Food");
		String menu = "Grilled Top Sirloin Steak" + " costs " + 17.49 + "\u00a3.";

		assertEquals(menu,item.getMenu());
	}

	@Test
	public void testHashCode() {
		Item item1 = new Item("Grilled Top Sirloin Steak","Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli",17.49,"FOOD2435","Food");
		Item item2 = new Item("Grilled Top Sirloin Steak","Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli",17.49,"FOOD2435","Food");
		Assert.assertTrue(item1.equals(item2) && (item2.equals(item1)));
		Assert.assertTrue(item1.hashCode() == item2.hashCode());

	}
	
	@Test
	public void testEquals() {
		
		
		
		Item item1 = new Item("sushi", "A mixed sushi set", 6.99, "FOOD4534", "fOOD");
		Item item2 = new Item("sushi", "A mixed sushi set", 6.99, "FOOD4534", "fOOD");
		Item item3 = new Item("Blue Mountain","Jamaican Blue Mountain Coffee",12.50,"BEVE2367","beverage");
		
		assertEquals(item1,item2);
		assertEquals(item2,item1);
		assertNotEquals(item1,item3);
		assertNotEquals(item3,item1);
		assertNotEquals(item1,null);
		
		assertTrue(item1.equals(item2));
		
		assertFalse(item1.equals(this));
		
		assertFalse(item1.equals(item3));
		
		assertTrue(item1.equals(item1));
	}
}

