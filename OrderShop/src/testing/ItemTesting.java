package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import shop.Item;

public class ItemTesting {
	private Item item;
	
	private String Name = "Grilled Top Sirloin Steak";
	private String Description="Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli";
	private double Price = 17.49;
	private String ItemID = "FOOD2435";
	private String CategoryItem = "Food";
	
	@Before
	public void setup() {
		item = new Item(Name,Description, Price, ItemID,CategoryItem);
	}
	
	@Test
	public void getItemNamePass() {
		assertEquals(Name,item.getName());
	}
	
	@Test
	public void getDescriptionPass() {
		assertEquals(Description,item.getDescription());
	}
	
	@Test
	public void getPricePass() {
		assertEquals(Price,item.getPrice(),0.01);
	}
	
	@Test
	public void getItemIDPass() {
		assertEquals(ItemID,item.getItemID());
	}
	
	@Test
	public void getCategoryItemPass() {
		assertEquals(CategoryItem,item.getCategory());
	}
}
