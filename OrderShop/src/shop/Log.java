package shop;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import controller.AllOrders;
import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;
import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidOrderCustomerIDException;
import ourExceptions.InvalidOrderCustomerNameException;
import ourExceptions.InvalidOrderTimeStampException;
import ourExceptions.InvalidPriceException;
/**
 * Use singleton pattern
 * Store log strings
 * Write the log string to a file
 * @author jeese
 *
 */

public class Log {
	private static Log logInstance;
	private static String logContent;
	public Log() {
		
	}
	
	//double-checked locking
	public static Log getLogInstance() {
		if(logInstance == null) {
			synchronized(Log.class) {
				if(logInstance == null) {
					logInstance = new Log();
				}
			}
		}
		return logInstance;
	}
	
	public void log(String logl) {
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		logl += "  ----- " + df.format(new Date()) + "\n";
		
		logContent += logl;
	}
	
	public static void writeFile() {
		
		try {
			
			File fileName = new File("Log.txt");
			
			if(!fileName.exists()) {
				fileName.createNewFile(); // check file exists
			}
			
			FileWriter filewriter = new FileWriter(fileName.getName(),true);
			filewriter.write(logContent);
			filewriter.close();
			
		}catch (FileNotFoundException e) {
			
			e.printStackTrace();
		
		}catch (IOException e) {
			
			e.printStackTrace();
		}
		try {
			AllOrders final_one = new AllOrders();
		} catch (InvalidPriceException | InvalidCategoryException | InvalidOrderTimeStampException
				| InvalidOrderCustomerIDException | InvalidOrderCustomerNameException | InvalidItemIDLengthException
				| InvalidItemException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	
}
