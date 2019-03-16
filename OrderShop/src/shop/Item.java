/* author: Kontogeorgos Georgios
 * All copyrights reserved 2019-2020
 */

package shop;

// In Item Class we set up all features for each item
public class Item {

	private String name;
	private String description;
	private double price;
	private String itemID;
	private String categoryItem;
	
	// Constructor
	public Item(String Name, String Description, double Price, String ItemID, String CategoryItem) {
		this.name = Name;
		this.description = Description;
		this.price = Price;
		this.itemID = ItemID;
		this.categoryItem = CategoryItem;
	}

	// Getter for item's name
	public String getName() {
		return name;
	}

	// Getter for item's description
	public String getDescription() {
		return description;
	}

	// Getter for item's category
	public String getCategoryItem() {
		return categoryItem;
	}
	
	// Getter for item's price
	public double getPrice() {
		return price;
	}
	
	// Getter for itemID
	public String getItemID() {
		return itemID;
	}

	// Getter to display menu
	public String getMenu() {
		return name + " costs " + price + "\u00a3.";
	}

	// hashcode is needed for the hash map to get rid of same keys with different values
	@Override
	public int hashCode() {
		return this.getItemID().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Item))
			return false;
		if (obj == this)
			return true;
		return this.getItemID() == ((Item) obj).getItemID();
	}
}
