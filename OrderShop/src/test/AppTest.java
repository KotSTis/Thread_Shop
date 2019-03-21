/* author: Jiaxi Lyu
 * All copyrights reserved 2019-2020
 */
package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;
import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidOrderCustomerIDException;
import ourExceptions.InvalidOrderCustomerNameException;
import ourExceptions.InvalidOrderTimeStampException;
import ourExceptions.InvalidPriceException;
import shop.App;
import shop.CsvReader;


public class AppTest {

	@Test
	public void testMain() throws IOException, InvalidItemIDLengthException, InvalidItemException, InvalidPriceException, 
	InvalidCategoryException, InvalidOrderTimeStampException, InvalidOrderCustomerIDException, InvalidOrderCustomerNameException {
		String[] args = new String[] { "" };
		


	}
}