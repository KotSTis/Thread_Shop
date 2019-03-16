package model;

import java.util.ArrayList;
import java.util.Observable;

import shop.Order;

public class Staff extends Observable implements Runnable {
	
	private int threadSleepTime = 1500;
	
	QueueCustomer qC;
	ArrayList <Order> order ;
	int thread;
	
	public Staff (int thread){
		this.thread = thread;
		order = new ArrayList <Order>();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
