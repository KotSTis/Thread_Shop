/* author: Kontogeorgos Georgios
 * All copyrights reserved 2019-2020
 */


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

// model for MVC pattern
public class QueueCustomer extends Observable {
	
	Log logger;

	// store the offline and online orders in a linked list
	private static Queue <Order> orders = new LinkedList <Order> ();

	private static Queue <Order> online_ords = new LinkedList <Order> ();

	// lock is needed for synchronization
	private static final Object lock = new Object();
	
	public QueueCustomer(Log lg) 
			throws FileNotFoundException, InvalidOrderTimeStampException, InvalidOrderCustomerIDException, InvalidOrderCustomerNameException{
		logger = lg;
		
	}
	
	// adding the orders to the queue and check the priority of the orders.. if it's online it has higher priority
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
	
	// write the final output to the log file when queues are empty
	public boolean check_empty() {
		if(orders.isEmpty() && online_ords.isEmpty()) {
			Log.writeFile();
			return true;
		}
		return false;
	}
	
	// get the first element from the queue
	public Order get_top() {
		Order ord;
		synchronized(lock) {
			if(!online_ords.isEmpty()) {
				ord = online_ords.poll();
			}else if(!orders.isEmpty()){
				ord = orders.poll();
			}else{
				return null;
			}
			
		}

		setChanged();
		notifyObservers(this);
        clearChanged();
		return ord;
	}
	
	// return the queue of the offline orders
	public Queue<Order> get_queue() {
		synchronized(lock) {
			return orders;
		}
	}
	
	// return the queue of the online orders
	public Queue<Order> get_online() {
		synchronized(lock) {
			return online_ords;
		}
	}
	
}