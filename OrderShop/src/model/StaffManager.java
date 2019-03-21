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
	
	public int removeServer() {
		Staff staff = staffEmployees.get(staffEmployees.size()-1);
		staffEmployees.remove(staff);
		staff.turnOff();
		return staff.getNumber();
	}
}
