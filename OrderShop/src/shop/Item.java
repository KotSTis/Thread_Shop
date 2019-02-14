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
		this.setCategoryItem(CategoryItem);
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
	
	public String getCategoryItem() {
		return categoryItem;
	}

	public void setCategoryItem(String categoryItem) {
		this.categoryItem = categoryItem;
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
	
//	public String getCategory() {
//		return getCategoryItem();
//	}
//	
//	public void setCategory(String CategoryItem) {
//		this.setCategoryItem(CategoryItem);
//	}
	
	public String displayFoodItems (){
		String menuFood = "";
		if (categoryItem.equals("Food")) {
	    menuFood = name + " " + description + " " + itemID + " " + categoryItem + " " + price + "£";
		}
		return menuFood;
	}
	
	public String displayBeverageItems (){
		String menuBeverage = "";
		if (categoryItem.equals("Beverage")) {
	    menuBeverage = name + " " + description + " " + itemID + " " + categoryItem + " " + price + "£";
		}
		return menuBeverage;
	}
	public String displayDessertItems (){
		String menuDessert = "";
		if (categoryItem.equals("Dessert")) {
	    menuDessert = name + " " + description + " " + itemID + " " + categoryItem + " " + price + "£";
		}
		return menuDessert;
	}
	public String displayItems (){
		
	    String menu = name + "," + description + "," + itemID + "," + categoryItem + "," + price + "£" + ",";
		
		return menu ;
	}
}
