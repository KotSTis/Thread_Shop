package testing;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;
import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidPriceException;
import shop.CsvReader;
import shop.Item;

public class MenuTest {

	private TreeSet<Item> menu = new TreeSet<Item>();
	CsvReader reader;
	String menuFileName;
	
	@Before
	public void setUp() throws FileNotFoundException, InvalidItemIDLengthException, InvalidItemException, InvalidPriceException, InvalidCategoryException{
		reader = new CsvReader();
		menuFileName = "Menu.csv";
		this.menu = reader.readMenuInfo(menuFileName);
	}
	
	@Test
	public void testdisplayMenu() {
		Item item = new Item("Grilled Top Sirloin Steak",
				"Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli", 17.49,
				"FOOD2435", "Food");
		@SuppressWarnings("unused")
		Iterator<Item> iterator;
		iterator = menu.iterator();
		assertEquals(item.getItemID() + " ", "FOOD2435" + " ");

	}

	@Test
	public void testDisplayFood() {
		Item item = new Item("Grilled Top Sirloin Steak",
				"Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli", 17.49,
				"FOOD2435", "Food");
		@SuppressWarnings("unused")
		Iterator<Item> iterator;
		iterator = menu.iterator();
		assertEquals(
				item.getName() + "," + item.getDescription() + "," + item.getItemID() + "," + item.getPrice() + ",",
				"Grilled Top Sirloin Steak" + "," + "Juicy 8-oz. center-cut sirloin served with mashed potatoes and fresh steamed broccoli" + ","
						+ "FOOD2435" + "," + 17.49 + ",");

	}

	@Test
	public void testDisplayBeverage() {
		Item item = new Item("Aruba Blonde beer","A rich honey ale",5.99,"BEVE2506","Beverage");
		@SuppressWarnings("unused")
		Iterator<Item> iterator;
		iterator = menu.iterator();
		assertEquals(
				item.getName() + "," + item.getDescription() + "," + item.getItemID() + "," + item.getPrice() + ",",
				"Aruba Blonde beer" + "," + "A rich honey ale" + "," + "BEVE2506" + "," + 5.99 + ",");

	}


	@Test
	public void testDisplayDessert() {
		Item item = new Item("Snickerdoodle","Snickerdoodle cookies and vanilla bean ice cream",6.49,"DESS7645","Dessert");
		@SuppressWarnings("unused")
		Iterator<Item> iterator;
		iterator = menu.iterator();
		assertEquals(
				item.getName() + "," + item.getDescription() + "," + item.getItemID() + "," + item.getPrice() + ",",
				"Snickerdoodle" + "," + "Snickerdoodle cookies and vanilla bean ice cream" + "," + "DESS7645" + "," + 6.49 + ",");

	}
}