package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import shop.Order;

public class OrderTesting {
	private Order orderedItem;
	
	private String timestamp = "1523580902";
	private String customerID = "CUST1544";
	private String itemID = "DESS8791";
	
	@Before
	public void setup() {
		orderedItem = new Order(timestamp,customerID, itemID);
	}
	
	@Test
	public void getTimestampPass() {
		assertEquals(timestamp,orderedItem.getTimeStamp());
	}
	
	@Test
	public void getCustomerIDPass() {
		assertEquals(customerID,orderedItem.getCustomerID());
	}
	
	@Test
	public void getItemIDPass() {
		assertEquals(itemID,orderedItem.getItemID());
	}

}
