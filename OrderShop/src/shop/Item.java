package shop;

public class Item {
	
	private String name;
	private String description;
	private double price;
	private String itemID;
	private String categoryItem;

	public enum Category { 
		food,
		beverage,
		dessert
	}
	
	public Item (String Name, String Description, double Price, String ItemID, String CategoryItem) {
		this.name = Name;
		this.description = Description;
		this.price = Price;
		this.itemID = ItemID;
		this.categoryItem = CategoryItem;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String Name) {
		this.name = Name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String Description) {
		this.description = Description;
	}
	
	public double getPrice () {
		return price;
	}
	
	public String getItemID() {
		return itemID;
	}
	
	public void setItemID(String ItemID) {
		this.itemID = ItemID;
	}
	
	public String getCategory() {
		return categoryItem;
	}
	
	public void setCategory(String CategoryItem) {
		this.categoryItem = CategoryItem;
	}
	
	public String displayMenu (){
		String menu = name + "" + description + "" + itemID + "" + price + "£";
		return menu;
	}

}
