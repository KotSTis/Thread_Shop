package testing;

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
}
