package shop;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.TreeSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class GUI extends JFrame implements ActionListener {

	private Menu menu;

	// GUI components: competitorNumber, name, level, country, overall, score for each competitor
	JButton foodButton, beverageButton, dessertButton, plusButton, minusButton, procceedButton, closeButton;
	JLabel orderLabel;
	JTextField[] items;
	// Other components: short details, full details and Result
	JTextArea itemsField;
	JTextField quantityField;
	JLabel Result;
	// Buttons: Search, Update, Sort by Ascending order: Sort_ASCNumber, Sort_ASCName, Sort_ASCLevel, Sort_ASCOverall, Close
	JTable table;

	
	public GUI(Menu menuList) throws FileNotFoundException {
		
		this.menu = menuList;
		setTitle("GK-AM-KS-JS Shop");
		NorthPanel();
		CenterPanel();
		SouthPanel();
		// pack and set visible
		setLocation(655, 30);
		pack();
		setVisible(true);
	}
	
	private void NorthPanel() {

		// Creating the north panel
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.GRAY);
		northPanel.setLayout(new GridLayout(15, 12));
		northPanel.setLayout(new FlowLayout());

		// Adding a panel and a text field for searching a competitor number
		foodButton = new JButton("Food");
		foodButton.setForeground(Color.BLACK);
		foodButton.addActionListener(this);
		foodButton.setEnabled(true);
		northPanel.add(foodButton);
		
		beverageButton = new JButton("Beverage");
		beverageButton.setForeground(Color.BLACK);
		beverageButton.addActionListener(this);
		beverageButton.setEnabled(true);
		northPanel.add(beverageButton);
		
		dessertButton = new JButton("Dessert");
		dessertButton.setForeground(Color.BLACK);
		dessertButton.addActionListener(this);
		dessertButton.setEnabled(true);
		northPanel.add(dessertButton);
		
	
		this.add(northPanel, BorderLayout.NORTH);
		
	}
	
	
	
	private void CenterPanel() {
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.DARK_GRAY);
		centerPanel.setLayout(new GridLayout(3, 1));
		
		JLabel itemDetailsLabel = new JLabel("Menu:", SwingConstants.CENTER);
		centerPanel.add(itemDetailsLabel);
		itemDetailsLabel.setForeground(Color.WHITE);
		itemsField = new JTextArea(66, 8);
		// Disables editing of the short detail's field

	
		


		String [] fill = menu.displayItemsMenu().split(",");
		
		
		table = new JTable();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		Object rowData[] = new Object[5];
		
		
		model.addColumn("Name");
		model.setColumnCount(5);
		model.setRowCount(15);
		 
		for (int i = 0; i < 5; i++) {
			rowData[i] = fill[i];
			
		 }
		model.addRow(rowData);
		JScrollPane scroll = new JScrollPane(table);
		
		table.setVisible(true);
		model.moveRow(model.getRowCount() - 1, model.getRowCount() - 1, 0);
		table.setEnabled(false);
		centerPanel.add(table);

		
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(centerPanel, FlowLayout.CENTER);
		
	}
	
	


	private void SouthPanel() {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.GRAY);
		southPanel.setLayout(new GridLayout(6, 1));

		
		
		
		
		// Adding Update Button
		procceedButton = new JButton("CONFIRM");
		procceedButton.setForeground(Color.GREEN);
		procceedButton.addActionListener(this);
		// Disables Update Button by default
		procceedButton.setEnabled(false);
		procceedButton.setSize(2,2);
		southPanel.add(procceedButton);
		
		// Creating a CLOSE button to end the program
		closeButton = new JButton("EXIT");
		closeButton.setForeground(Color.RED);
		// Specify action when button is pressed
		closeButton.addActionListener(this);
		closeButton.setEnabled(true);
		southPanel.add(closeButton);
		southPanel.setLayout(new FlowLayout());
		
		this.add(southPanel, BorderLayout.SOUTH);
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource () == foodButton) {
			getFoodDetails();
			
		}
		else if (e.getSource () == beverageButton) {
			getBeverageDetails();
		}
		else if (e.getSource () == dessertButton) {
			getDessertDetails();
		}
		else if (e.getSource() == procceedButton) {
			
		}
		else if (e.getSource() == closeButton) {
			System.exit(0);
		}
	}

	public void getFoodDetails (){
		itemsField.setText(menu.displayFoodMenu());
		procceedButton.setEnabled(true);
		String [] fill = menu.displayFoodMenu().split(",");
		for (int i = 0; i<5; i++) {
			items[i].setText(fill[i]);
			
		}
	}
	
	public void getBeverageDetails (){
		itemsField.setText(menu.displayBeverageMenu());
		procceedButton.setEnabled(true);
	}
	
	public void getDessertDetails (){
		itemsField.setText(menu.displayDessertMenu());
		procceedButton.setEnabled(true);

		

	}
	
	private void procceed () {
		
	}
	
	private void update() {


	}
	

	private void clear() {
		
		itemsField.setText("");
		procceedButton.setEnabled(false);
	}
	
}