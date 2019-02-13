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
	
	
	public void displayMenu (){
		Iterator<Item> iterator;
		iterator = menu.iterator();
	    while (iterator.hasNext()) {
	         System.out.println(iterator.next().getItemID() + " ");
	    }
	}
	
	

}
