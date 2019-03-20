package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import shop.Log;
import shop.Order;

public class Staff extends Observable implements Runnable {
	
	private static int threadSleepTime = 1500;
	private Log logger;
	QueueCustomer qC;
	int thread;

	public Staff (int thread, Log lg, QueueCustomer qCust){
		this.thread = thread;
		this.logger = lg;
		this.qC = qCust;
		
	}
	
	public int getNumber(){
		return thread;
	}
	
	public void process(Order ord) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM");
		Date date = new Date();
		String timestamp = formatter.format(date);
		this.logger.log("Server No."+ this.thread + " processed Order " + ord.getCustomerID() + ord.getItems().size() + ord.getPrice() +
				"at " + timestamp);
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while(true) {
			if(!qC.check_empty()) {
				Order ord = qC.get_top();
				process(ord);
				setChanged();
				notifyObservers(this);
		        clearChanged();
                try {
                	Thread.sleep(threadSleepTime*ord.getItems().size());
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
			}
			
		}
		
	}

}
