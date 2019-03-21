package model;

import java.util.ArrayList;
import java.util.Observable;

public class StaffManager {
	private ArrayList<Staff> staffEmployees;
	
	public StaffManager() {
		staffEmployees = new ArrayList<Staff>();
	}
	
	public StaffManager( ArrayList<Staff> staff) {
		staffEmployees = staff;
	}
	
	public ArrayList <Staff> getServers() {
		return staffEmployees;
	}

	public void changeTimer(int speed){
		for(Staff mastoras : staffEmployees){
			mastoras.setThreadSleepTime(speed);
		}
	}
	
	public void addServer(Staff s) {
		staffEmployees.add(s);
	}
	
	public void removeServer() {
		staffEmployees.remove(staffEmployees.size());
	}
}
