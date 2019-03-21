/* author: Jiaxi Lyu
 * All copyrights reserved 2019-2020
 */

package shop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import controller.AllOrders;
import controller.GUIController;
import model.QueueCustomer;
import model.Staff;
import model.StaffManager;
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
			InvalidPriceException, InvalidCategoryException, InvalidOrderTimeStampException, InvalidOrderCustomerIDException, InvalidOrderCustomerNameException, InterruptedException {
		Log logger = new Log();
		Menu menu = new Menu();
		menu.displayMenu();
		CsvReader reader = new CsvReader();
		
//		Log log = new Log();
//		ArrayList <Staff> staff = new ArrayList <Staff> ();
//		QueueCustomer queue = new QueueCustomer(log);
//		StaffManager serversModel = new StaffManager();
//		StatusGUI statusView = new StatusGUI(queue, staff);
//		new GUIController(statusView, serversModel);
		
//		AllOrders allOrders = new AllOrders();
//		allOrders.FinalReport("Report.txt");
		try {
			// For running Exceptions tests: run these commands and switch to appropriate
			// filenames for exceptions
			// example: reader.readMenuInfo("MenuExceptionItemIDLength.csv");
			reader.readMenuInfo("Menu.csv");
			reader.readFirstNames("Random-Names.csv");
			reader.readLastNames("Random-Names.csv");

			// Example: reader.readOrdersInfo("OrdersExceptionCustomerID.csv");
			reader.readOrdersInfo("Orders.csv");
		} catch (FileNotFoundException fileNotFound) {
			System.err.print("File not found! Please, import a correct file.");
		}
				
		QueueCustomer model = new QueueCustomer(logger);
		GUI controlGUI = new GUI();
		Staff numero_uno = new Staff(1, logger, model);
     	Staff numero_duo = new Staff(2, logger, model);
		Staff numero_tre = new Staff(3, logger, model);
		Staff numero_quatro = new Staff(4, logger, model);
		Staff numero_cinqo = new Staff(5, logger, model);
		
		Thread uno = new Thread(numero_uno);
		Thread duo = new Thread(numero_duo);
		Thread tre = new Thread(numero_tre);
		Thread quatro = new Thread(numero_quatro);
		Thread cinqo = new Thread(numero_cinqo);
		
	    ArrayList<Staff> staffs = new ArrayList<Staff>();
		staffs.add(numero_uno);
		staffs.add(numero_duo);
		staffs.add(numero_tre);
		staffs.add(numero_quatro);
		staffs.add(numero_cinqo);
		
		StaffManager manager_rugby = new StaffManager(staffs);
		
		StatusGUI viewGUI = new StatusGUI(model, staffs);
		AllOrders controlla = new AllOrders(controlGUI, model, logger);
		GUIController yaman = new GUIController(viewGUI, manager_rugby);
		uno.start();
		duo.start();
		tre.start();
		quatro.start();
		cinqo.start();

		

	}
}