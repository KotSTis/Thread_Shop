package shop;

import java.util.Comparator;


// Comparing items by itemID ... Sorting them first by Food, then Beverage and last category is Dessert
public class ItemComparator implements Comparator<Item> {

	@Override
	public int compare(Item item0, Item item1) {
		return item1.getItemID().compareTo(item0.getItemID());
	}

}