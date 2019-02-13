package shop;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener {

	private AllOrders orderList;
	// GUI components: competitorNumber, name, level, country, overall, score for each competitor
	JTextField customerIDField, nameField, levelField, countryField, overallField, searchField, updateField;
	JTextField[] score;
	// Other components: short details, full details and Result
	JTextArea shortDetailsField, fullDetailsField;
	JLabel Result;
	// Buttons: Search, Update, Sort by Ascending order: Sort_ASCNumber, Sort_ASCName, Sort_ASCLevel, Sort_ASCOverall, Close
	JButton Search, Update, Sort_ASCNumber, Sort_ASCName, Sort_ASCLevel, Sort_ASCOverall, Close;


	
	public GUI(AllOrders list) {
		this.orderList = list;
		setTitle("GK-AM-KS-JS Shop");
		NorthPanel();
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
		northPanel.setLayout(new GridLayout(13, 2));

		// Adding a panel and a text field for searching a competitor number
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(4, 2));
		searchPanel.add(new JLabel("Enter customer ID:", SwingConstants.CENTER)); // Adding the panel to center
		searchField = new JTextField(5);
		searchPanel.add(searchField);

		// Adding a Search Button
		Search = new JButton("Search");
		Search.setForeground(Color.BLUE);
		searchPanel.add(Search);
		// Specify action when button is pressed
		Search.addActionListener(this);
		// A result's message is displayed on top left
		Result = new JLabel("");
		Result.setForeground(Color.RED);
		Result.setLayout(new GridLayout(4, 1));
		searchPanel.add(Result);
		this.add(searchPanel, BorderLayout.NORTH);
	

	}



	private void SouthPanel() {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.DARK_GRAY);
		southPanel.setLayout(new GridLayout(6, 1));

		// Adding a label and a text field for short details
		JLabel shortDetailsLabel = new JLabel("Menu:", SwingConstants.CENTER);
		southPanel.add(shortDetailsLabel);
		shortDetailsLabel.setForeground(Color.WHITE);
		shortDetailsLabel.setBackground(Color.LIGHT_GRAY);
		shortDetailsField = new JTextArea(1, 1);
		// Disables editing of the short detail's field
		shortDetailsField.setEditable(false);
		shortDetailsField.setBackground(Color.LIGHT_GRAY);
		southPanel.add(shortDetailsField);
		
		// Adding a label and a text field for full details
		JLabel fullDetailsLabel = new JLabel("Orders:", SwingConstants.CENTER);
		southPanel.add(fullDetailsLabel);
		fullDetailsLabel.setForeground(Color.WHITE);
		fullDetailsField = new JTextArea(5, 1);
		// Disables editing of the short detail's field
		fullDetailsField.setEditable(false);
		fullDetailsField.setBackground(Color.LIGHT_GRAY);
		southPanel.add(fullDetailsField);
		
		// Adding Update Button
		Update = new JButton("Update");
		Update.setForeground(Color.BLUE);
		Update.addActionListener(this);
		// Disables Update Button by default
		Update.setEnabled(false);
		Update.setSize(2,2);
		southPanel.add(Update);
		
		// Creating a CLOSE button to end the program
		Close = new JButton("EXIT");
		Close.setForeground(Color.RED);
		// Specify action when button is pressed
		Close.addActionListener(this);
		Close.setEnabled(true);
		southPanel.add(Close);
		
		
		this.add(southPanel, BorderLayout.SOUTH);
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Search) {
			search();
		} else if (e.getSource() == Update) {
			//update();
		}
	}


	public void search() {
		
			// Search competitor by competitor number
			int customerNumber = Integer.parseInt(searchField.getText().trim());

			clear();
		
	}

	private void update() {


	}
	

	private void clear() {
		customerIDField.setText("");
		nameField.setText("");
		levelField.setText("");
		countryField.setText("");
		for (int i = 0; i < 5; i++) {
			score[i].setText("");
		}
		overallField.setText("");
		Update.setEnabled(false);
	}
	
}