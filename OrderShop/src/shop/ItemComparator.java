package shop;


import java.util.Comparator;

public class ItemComparator  implements Comparator<Item>{

	@Override
	public int compare(Item item0, Item item1) {
		return item1.getItemID().compareTo(item0.getItemID());
	}

}