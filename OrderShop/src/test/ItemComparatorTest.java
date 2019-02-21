/* author: Jiaxi Lyu
 * All copyrights reserved 2019-2020
 */

package test;

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

