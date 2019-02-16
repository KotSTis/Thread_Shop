
package shop;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.Iterator;

public class Menu{

	private TreeSet<Item> menu;
	Item i;
	
	Menu () throws FileNotFoundException{
		CsvReader reader = new CsvReader();
		this.menu = reader.readMenuInfo("Menu.csv");
	}
	
	public void add(Item menuItem) {
		menu.add(menuItem);
	}
	
	public void remove(String menuItem) {
		menu.remove(menuItem);
	}
	
	public Item getItem(String itemID) {
		return null;
	}
	
	public void displayMenu (){
		Iterator<Item> iterator;
		iterator = menu.iterator();
	    while (iterator.hasNext()) {
	         System.out.println(iterator.next().getItemID() + " ");
	    }
	}
	

	public String displayFood (){
		String menuDetails = "";
		Iterator<Item> iterator;
		iterator = menu.iterator();
		while (iterator.hasNext()) {
			Item next = iterator.next();
			if ("Food".equals(next.getCategoryItem())){
				menuDetails += next.getName() + "," + next.getDescription() + "," + next.getItemID() + "," + next.getPrice() + "£" + "," +  "\n";
			}
		}
		return menuDetails;
	}

	public String displayDessert (){
		String menuDetails = "";
		Iterator<Item> iterator;
		iterator = menu.iterator();
		while (iterator.hasNext()) {
			Item next = iterator.next();
			if ("Dessert".equals(next.getCategoryItem())){
				menuDetails += next.getName() + "," + next.getDescription() + "," + next.getItemID() + "," + next.getPrice() + "£" + "," +  "\n";
			}
		}
		return menuDetails;
	}
	
	public String displayBeverage (){
		String menuDetails = "";
		Iterator<Item> iterator;
		iterator = menu.iterator();
		while (iterator.hasNext()) {
			Item next = iterator.next();
			if ("Beverage".equals(next.getCategoryItem())){
				menuDetails += next.getName() + "," + next.getDescription() + "," + next.getItemID() + "," + next.getPrice() + "£" + "," +  "\n";
			}
		}
		return menuDetails;
	}
	
	
	public void something (){
		
		Iterator<Item> iterator;
		iterator = menu.iterator();
		while (iterator.hasNext()) {
			Item next = iterator.next();	
		}

	}
}

