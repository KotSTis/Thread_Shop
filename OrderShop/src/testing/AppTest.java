package testing;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import shop.AllOrders;
import shop.App;
import shop.CsvReader;


public class AppTest {

	@Test
	public void testMain() throws IOException {
		String[] args = new String[] { "" };
		App.main(args);
		
		CsvReader reader = new CsvReader();
		String filename1 ="Orders.csv";
		reader.readOrdersInfo(filename1);		
		assertEquals (filename1,"Orders.csv");

		String filename2 ="Menu.csv";
		reader.readMenuInfo(filename2);		
		assertEquals (filename2,"Menu.csv");
		
		AllOrders allOrders = new AllOrders();
		String filename3 = "Report.csv";
		allOrders.FinalReport(filename3);	
		assertEquals(filename3, "Report.csv");
	}

}