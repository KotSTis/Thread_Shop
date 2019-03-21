package model;

import java.util.ArrayList;
import java.util.Observable;

public class StaffManager {
	private ArrayList<Staff> staffEmployees;
	
	public StaffManager() {
		staffEmployees = new ArrayList<Staff>();
	}
	
	public ArrayList <Staff> getServers() {
		return staffEmployees;
	}

	
	public void addServer(Staff s) {
		staffEmployees.add(s);
	}
	
	public void removeServer() {
		Staff s = staffEmployees.get(staffEmployees.size());
		staffEmployees.remove(s);
	}
}
