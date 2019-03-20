/* author: Jiaxi Lyu
 * All copyrights reserved 2019-2020
 */

package shop;

import java.io.FileNotFoundException;
import java.io.IOException;

import controller.AllOrders;
import model.QueueCustomer;
import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidOrderCustomerIDException;
import ourExceptions.InvalidOrderCustomerNameException;
import ourExceptions.InvalidOrderTimeStampException;
import ourExceptions.InvalidPriceException;
import viewer.StatusGUI;
import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;

// Our main app
public class App {

	public static void main(String[] args) throws IOException, InvalidItemIDLengthException, InvalidItemException,
			InvalidPriceException, InvalidCategoryException, InvalidOrderTimeStampException, InvalidOrderCustomerIDException, InvalidOrderCustomerNameException {

		
		new GUI();

		QueueCustomer model = new QueueCustomer(logger);
		StatusGUI viewGUI = new StatusGUI(model);
		GUI controlGUI = new GUI();
		AllOrders controlla = new AllOrders(controlGUI, model, logger);


	}
}