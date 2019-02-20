
package testing;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

import shop.*;

public class ItemTest extends TestCase {

	private Item i1;
	private Item i2;
	private Item i3;
	
	@Before
	public void setUp() throws Exception {
		i1 = new Item("sushi", "A mixed sushi set", 6.99, "FOOD4534", "food");
		i2 = new Item("Blue Mountain","Jamaican Blue Mountain Coffee",12.50,"BEVE2367","beverage");
		i3 = new Item("Blue Mountain","Jamaican Blue Mountain Coffee",12.50,"BEVE2367","beverage");

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
	}
	
	@Test
	public void testHashCode() {
		Assert.assertTrue(i3.equals(i2) && (i2.equals(i3)));
		Assert.assertTrue(i3.hashCode() == i2.hashCode());
	}

	@Test
	public void testGetName() {
		assertEquals("sushi",i1.getName());
		assertEquals("Blue Mountain",i2.getName());
	}

	@Test
	public void testGetDescription() {
		assertEquals("A mixed sushi set",i1.getDescription());
		assertEquals("Jamaican Blue Mountain Coffee",i2.getDescription());
	}

	@Test
	public void testGetCategoryItem() {
		assertEquals("food",i1.getCategoryItem());
		assertEquals("beverage",i2.getCategoryItem());
	}

	@Test
	public void testGetPrice() {
		assertEquals(6.99,i1.getPrice());
		assertEquals(12.50,i2.getPrice());
	}

	@Test
	public void testGetItemID() {
		assertEquals("FOOD4534",i1.getItemID());
		assertEquals("BEVE2367",i2.getItemID());
	}
	
	@Test
	public void testGetMenu() {
		Item item = new Item("Grilled Top Sirloin Steak","Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli",17.49,"FOOD2435","Food");
		String menu = "Grilled Top Sirloin Steak" + " costs " + 17.49 + "\u00a3.";

		assertEquals(menu,item.getMenu());
	}
	
	@Test
	public void testEqualsObject() {
		Object obj1 = new Object();
		
		assertFalse(obj1.equals(null));
		assertFalse(obj1.equals(i1));
		assertTrue(i1.equals(i1));
		assertTrue(i1.getItemID().equals((i1).getItemID()));
	}

}
