package shop;
import java.io.IOException;
import java.util.HashMap;


public class App  {

	public static void main(String[] args) throws IOException {

		AllOrders orders = new AllOrders();
		Menu menu = new Menu();

//		
//	    System.out.println(orders.calculateBill());
//	    
        HashMap <Item, Integer> newOrders = new HashMap <>();
		new GUI2(menu, newOrders);

//	    

//	    orders.FinalReport("Report.csv", "CUST1009");

	}

}