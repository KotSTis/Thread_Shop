package model;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

import ourExceptions.InvalidOrderCustomerIDException;
import ourExceptions.InvalidOrderCustomerNameException;
import ourExceptions.InvalidOrderTimeStampException;
import shop.Log;
import shop.Order;

public class QueueCustomer extends Observable {
	
	Log logger;
	private Queue <Order> orders = new LinkedList <Order> ();
	private static final Object lock = new Object();
	
	public QueueCustomer(Log lg) 
			throws FileNotFoundException, InvalidOrderTimeStampException, InvalidOrderCustomerIDException, InvalidOrderCustomerNameException{
		logger = lg;
		
	}
	
	public void addQueue(Order ord) {
		synchronized(lock) {
			orders.add(ord);
			logger.log(" added " + ord.getCustomerName() + "'s order to the queue");
		}	
	}
	
	public boolean check_empty() {
		if(orders.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public Order get_top() {
		synchronized(lock) {
			Order ord = orders.poll();
			if(ord != null) {
				return ord;
			}
			setChanged();
			notifyObservers(this);
	        clearChanged();
			return null;
		}
	}
	
	public Queue<Order> get_queue() {
		synchronized(lock) {
			return orders;
		}
	}
	
}