package shop;

public class Item {

	private String name;
	private String description;
	private double price;
	private String itemID;
	private String categoryItem;

	public Item(String Name, String Description, double Price, String ItemID, String CategoryItem) {
		this.name = Name;
		this.description = Description;
		this.price = Price;
		this.itemID = ItemID;
		this.categoryItem = CategoryItem;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getCategoryItem() {
		return categoryItem;
	}
	
	public double getPrice() {
		return price;
	}

	public String getItemID() {
		return itemID;
	}

	public String getMenu() {
		return name + " costs " + price + "\u00a3.";
	}

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
