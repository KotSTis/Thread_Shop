package shop;
import java.io.IOException;


public class App {

	public static void main(String[] args) throws IOException {
		CsvReader reader = new CsvReader();
		reader.readMenuInfo("Menu.csv");
		reader.readOrdersInfo("Orders.csv");
		
	    AllOrders orders = new AllOrders();
	    orders.FinalReport("Report.csv");
	   
	    GUI gui = new GUI();

	}

}