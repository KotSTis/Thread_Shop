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

	private static Queue <Order> orders = new LinkedList <Order> ();

	private static Queue <Order> online_ords = new LinkedList <Order> ();

	private static final Object lock = new Object();
	
	public QueueCustomer(Log lg) 
			throws FileNotFoundException, InvalidOrderTimeStampException, InvalidOrderCustomerIDException, InvalidOrderCustomerNameException{
		logger = lg;
		
	}
	
	public void addQueue(Order ord) {
		synchronized(lock) {
			if(ord.getPriority() == 1) {
				online_ords.add(ord);
			}else {
				orders.add(ord);
			}
			
			logger.log(" added " + ord.getCustomerName() + "'s order to the queue");
		}	
		setChanged();
		notifyObservers(this);
        clearChanged();
	}
	
	public boolean check_empty() {
		if(orders.isEmpty() && online_ords.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public Order get_top() {
		Order ord;
		synchronized(lock) {
			if(!online_ords.isEmpty()) {
				ord = online_ords.poll();
			}else{
				ord = orders.poll();
			}
			
		}

		setChanged();
		notifyObservers(this);
        clearChanged();
		return ord;
	}
	
	public Queue<Order> get_queue() {
		synchronized(lock) {
			return orders;
		}
	}
	
	public Queue<Order> get_online() {
		synchronized(lock) {
			return online_ords;
		}
	}
	
}