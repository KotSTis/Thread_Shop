package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.QueueCustomer;
import model.Staff;
import model.StaffManager;
import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;
import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidOrderCustomerIDException;
import ourExceptions.InvalidOrderCustomerNameException;
import ourExceptions.InvalidOrderTimeStampException;
import ourExceptions.InvalidPriceException;
import shop.GUI;
import shop.Log;
import viewer.StatusGUI;

public class GUIController{
	private GUI gui = new GUI ();
	
	private StatusGUI viewer;
	private StaffManager staffList;
	private Staff staff;
	private Log log;
	private QueueCustomer queue;
	
	
	GUIController() throws FileNotFoundException, InvalidPriceException, InvalidCategoryException,
	InvalidOrderTimeStampException, InvalidOrderCustomerIDException, InvalidOrderCustomerNameException,
	InvalidItemIDLengthException, InvalidItemException {
		this.staff = staff;
		this.log = log;
		this.queue = queue;
			this.viewer = viewer;
			this.staffList = staffList;
			this.viewer.addSpeedListener(new SpeedListener());
			this.viewer.addServer(new AddServerListener());
			this.viewer.removeServer(new RemoveServerListener());
		}
		
		public class AddServerListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				Staff s = new Staff(staff.getNumber(),log,queue);
				int numberOfServers = staffList.getServers().size();
				if (numberOfServers < 5) {
					staffList.addServer(s);
					s.addObserver(viewer);
					Thread serverThread = new Thread(s);
					serverThread.start();
				}
			}
		}
		
		public class RemoveServerListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (staff.getNumber() > 2){
				staffList.removeServer();
				}
			}
		}
		
		// inner class SpeedListener responds when user sets the Speed via the slider
		public class SpeedListener implements ChangeListener {
			@Override
			public void stateChanged(ChangeEvent e) {
				int speed = viewer.simulationSpeedSlider.getValue();
				staff.setThreadSleepTime(speed);
				viewer.threadsLabel.setText("Thread Processing Speed: " + Integer.toString(Staff.getThreadSleepTime()/1000)+ " Seconds"); 
			}	
		}

	}
