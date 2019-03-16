package model;

import java.io.FileNotFoundException;
import java.util.Queue;

import ourExceptions.InvalidOrderCustomerIDException;
import ourExceptions.InvalidOrderCustomerNameException;
import ourExceptions.InvalidOrderTimeStampException;
import shop.Log;
import shop.Order;

public class QueueCustomer {
	
	Log logger;
	private static Queue <Order> orders;
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
			return null;
		}
	}
	public Queue<Order> get_queue() {
		synchronized(lock) {
			return orders;
		}
	}
	
	
}

// OBSERVER: NEW GUI
// CONTROLLER : OLD GUI
