/* author: Jiaxi Lyu
 * All copyrights reserved 2019-2020
 */

package shop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import controller.AllOrders;
import model.QueueCustomer;
import model.Staff;
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
		Log logger = new Log();
		Menu menu = new Menu();
		menu.displayMenu();
		CsvReader reader = new CsvReader();
//		AllOrders allOrders = new AllOrders();
//		allOrders.FinalReport("Report.txt");
		try {
			// For running Exceptions tests: run these commands and switch to appropriate
			// filenames for exceptions
			// example: reader.readMenuInfo("MenuExceptionItemIDLength.csv");
			reader.readMenuInfo("Menu.csv");

			// Example: reader.readOrdersInfo("OrdersExceptionCustomerID.csv");
			reader.readOrdersInfo("Orders.csv");
		} catch (FileNotFoundException fileNotFound) {
			System.err.print("File not found! Please, import a correct file.");
		}
				
		QueueCustomer model = new QueueCustomer(logger);
		GUI controlGUI = new GUI();
		Staff numero_uno = new Staff(1, logger, model);

		
		ArrayList<Staff> staffs = new ArrayList<Staff>();
		staffs.add(numero_uno);
		StatusGUI viewGUI = new StatusGUI(model, staffs);
		Thread uno = new Thread(numero_uno);
		uno.start();
		AllOrders controlla = new AllOrders(controlGUI, model, logger);

		

	}
}