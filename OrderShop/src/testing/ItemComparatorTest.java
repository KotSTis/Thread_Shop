/*package testing;

import org.junit.Test;


import shop.Item;
import shop.ItemComparator;

public class ItemComparatorTest {

	private ItemComparator comparatorItem() {
		return new ItemComparator();
	}

	@Test
	public void testCompare() throws Exception {
		ItemComparator comparator;
		Item item0 = new Item("Aruba Blonde beer","A rich honey ale",5.99,"BEVE2506", "Beverage");
		Item item1 = new Item("Guac 'n Chips","Homemade guacamole with crispy corn tortilla and plantain chips",5.29,"FOOD1254","Food");
		comparator = comparatorItem();
		int result = comparator.compare(item0, item1);

	}
}*/

package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import shop.*;

public class ItemComparatorTest {

	private Item i1;
	private Item i2;
	
	private ItemComparator comparatorItem() {
		return new ItemComparator();
	}
	
	@Before
	public void setUp() throws Exception {
		i1 = new Item("sushi", "A mixed sushi set", 6.99, "FOOD1005", "food");
		i2 = new Item("sushiB", "A mixed sushi set", 7.99, "FOOD1006", "food");
	}

	@Test
	public void testCompare() {
		
		ItemComparator comparator;
		comparator = comparatorItem();
		
		assertEquals(-1,comparator.compare(i2, i1));
		assertEquals(1,comparator.compare(i1, i2));
		assertEquals(0,comparator.compare(i1, i1));
	}
		
}

