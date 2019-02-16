package shop;
import java.io.IOException;


public class App  {

	public static void main(String[] args) throws IOException {

		AllOrders orders = new AllOrders();
		Menu menu = new Menu();
		menu.displayMenu();
		System.out.println(menu.displayDessert());
//		
//	    System.out.println(orders.calculateBill());
//	    
    //new GUI(orders);
//	    

//	    orders.FinalReport("Report.csv", "CUST1009");

	}

}