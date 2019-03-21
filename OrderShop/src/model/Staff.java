/* author: Stergiou Konstantinos
 * All copyrights reserved 2019-2020
 */


package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Observable;

import shop.Log;
import shop.Order;

// model for MVC pattern
public class Staff extends Observable implements Runnable {
	
	private static int threadSleepTime = 5000;
	private Order ord;
	private Log logger;
	QueueCustomer qC;
	int thread;
	private boolean running = true;


	public Staff (int thread, Log lg, QueueCustomer qCust){
		this.thread = thread;
		this.logger = lg;
		this.qC = qCust;
		
	}
	
	// closing the server
	public void turnOff() {
		this.running =false;
	}
	
	// return the number of threads (number of serving staff)
	public int getNumber(){
		return thread;
	}
	
	// display the message for the order in the queue: offline/ online
	public String getGUIDisplay(){
		String ret = "Processing " + ord.getCustomerName() + "'s order.\n";
		for (HashMap.Entry<String, Integer> entry : ord.getItems().entrySet()) {
			ret += String.valueOf(entry.getValue()) ;
			ret += " " ;
			ret += entry.getKey() + "\n";
		}
		ret += "\nTotal is " + ord.getPrice() + " $";
		return  ret;
	}
	
	// thread sleep time for the servers
	public void setThreadSleepTime(int threadSleepTime){
		threadSleepTime = threadSleepTime*1000;
	}
	
	// return the sleep time
	public static int getThreadSleepTime(){
		return threadSleepTime;
	}
	
	// display timestamp and the message for the processed order
	public void process(Order ord) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM");
		Date date = new Date();
		String timestamp = formatter.format(date);
		this.logger.log("Server No."+ this.thread + " processed Order " + ord.getCustomerID() + ord.getItems().size() + ord.getPrice() +
				"at " + timestamp);
	}
	
	//
	@Override
	public void run() {
		
		//initiate a sleep time
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while(running) {
			if(!qC.check_empty()) {
				Order ord = qC.get_top();
				if(ord == null){
					return;
				}
				this.ord = ord;
				process(ord);
				
				// methods for Observable
				setChanged();
				notifyObservers(this);
		        clearChanged();
                try {
                	
                	// set the sleep time to the size of ordered items*sleep time
                	Thread.sleep(threadSleepTime*ord.getItems().size());
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
			}
			
		}
		
	}
}
