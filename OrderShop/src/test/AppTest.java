package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;
import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidOrderCustomerID;
import ourExceptions.InvalidOrderTimeStamp;
import ourExceptions.InvalidPriceException;
import shop.App;
import shop.CsvReader;


public class AppTest {

	@Test
	public void testMain() throws IOException, InvalidItemIDLengthException, InvalidItemException, InvalidPriceException, InvalidCategoryException, InvalidOrderTimeStamp, InvalidOrderCustomerID {
		String[] args = new String[] { "" };
		App.main(args);
		
		CsvReader reader = new CsvReader();
		String filename1 =" Orders.csv";
		reader.readOrdersInfo(filename1);		
		assertEquals (filename1,"Orders.csv");

	}
}