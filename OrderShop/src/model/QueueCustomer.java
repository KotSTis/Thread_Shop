package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;

import ourExceptions.InvalidOrderCustomerIDException;
import ourExceptions.InvalidOrderCustomerNameException;
import ourExceptions.InvalidOrderTimeStampException;
import shop.CsvReader;
import shop.Order;

public class QueueCustomer extends Observable{
	
	
	public ArrayList <Order> orders;
	private QueueCustomer() throws FileNotFoundException, InvalidOrderTimeStampException, InvalidOrderCustomerIDException, InvalidOrderCustomerNameException{
		
		CsvReader reader = new CsvReader();
		this.orders = reader.readOrdersInfo("Orders.csv");
	}
	
	public ArrayList <Order> custQueue(){
		return orders;
	}
	
	
}

// OBSERVER: NEW GUI
// CONTROLLER : OLD GUI

// 