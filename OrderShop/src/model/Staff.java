package model;

import java.util.ArrayList;
import java.util.Observable;

import shop.Order;

public class Staff extends Observable implements Runnable {
	
	private static int threadSleepTime = 1500;
	
	QueueCustomer qC;
	int thread;

	public Staff (int thread){
		this.thread = thread;
		
	}
	
	public void process(Order ord) {
		
	}
	
	@Override
	public void run() {
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
