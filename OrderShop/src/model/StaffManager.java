/* author: Mitrousis Alexandros
 * All copyrights reserved 2019-2020
 */


package model;

import java.util.ArrayList;
import java.util.Observable;

// This is the model of MVC pattern
// It handles all the staff members (servers)

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

	// thread sleep time for each server : extra core func.
	public void changeTimer(int speed){
		for(Staff s : staffEmployees){
			s.setThreadSleepTime(speed);
		}
	}
	
	// adding a server: extra core func.
	public void addServer(Staff s) {
		staffEmployees.add(s);
	}
	
	// removing a server : extra core func.
	public int removeServer() {
		Staff staff = staffEmployees.get(staffEmployees.size()-1);
		staffEmployees.remove(staff);
		staff.turnOff();
		return staff.getNumber();
	}
}
