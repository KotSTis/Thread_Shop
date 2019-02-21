package shop;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidOrderCustomerID;
import ourExceptions.InvalidOrderTimeStamp;
import ourExceptions.InvalidPriceException;
import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;

public class App {

	public static void main(String[] args) throws IOException, InvalidItemIDLengthException, InvalidItemException,
			InvalidPriceException, InvalidCategoryException, InvalidOrderTimeStamp, InvalidOrderCustomerID {

		CsvReader reader = new CsvReader();

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

		new GUI();

	}

}