package shop;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Position;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7483455288689655101L;
	private Menu menu = new Menu();
	private JButton addFoodButton, addBeverageButton, addDessertButton, removeButton, proceedButton, resetButton, finishButton;
	
	private JSpinner spinnerFood, spinnerBeverage, spinnerDessert;
	private AllOrders allOrders = new AllOrders();
	private JTable table1, table2, table3;
	private ArrayList <String> newOrderList = new ArrayList <String>();
	private HashMap<Item, Integer> newOrders;
	private JTextArea textBillArea;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JScrollPane scrollOrders;
	private JList<String> list = new JList<>();
	private HashMap <String, ArrayList<String>> orders = new HashMap <String, ArrayList<String>>();

	public GUI() throws FileNotFoundException {

		
		JFrame();

	}

	private void JFrame() {
		// Create and set up a frame window
		// JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Best Shoperino");
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBackground(SystemColor.text);
		frame.setLocation(150, 120);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Define the panel to hold the buttons
		JPanel panel1 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel2 = new JPanel();

		// Set up the title for different panels
		panel1.setBorder(BorderFactory.createTitledBorder("FOODS"));
		panel2.setBorder(BorderFactory.createTitledBorder("BEVERAGES"));
		panel3.setBorder(BorderFactory.createTitledBorder("DESSERTS"));


		proceedButton = new JButton("CONFIRM ORDER");
		proceedButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		proceedButton.setBackground(Color.GREEN);
		proceedButton.setForeground(Color.BLACK);
		proceedButton.addActionListener(this);

		resetButton = new JButton("RESET");
		resetButton.setForeground(Color.BLACK);
		resetButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		resetButton.setBackground(Color.RED);
		resetButton.addActionListener(this);
		resetButton.setEnabled(false);

		finishButton = new JButton("EXIT");
		finishButton.setForeground(Color.BLACK);
		finishButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		finishButton.setEnabled(true);
		finishButton.setBackground(Color.BLUE);
		finishButton.addActionListener(this);

		spinnerFood = new JSpinner(new SpinnerNumberModel(0, 0, 40, 1));

		spinnerBeverage = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));

		spinnerDessert = new JSpinner(new SpinnerNumberModel(0, 0, 60, 1));

		newOrders = new HashMap<>();

		addFoodButton = new JButton("ADD");
		addFoodButton.setEnabled(false);
		addFoodButton.addActionListener(this);

		addBeverageButton = new JButton("ADD");
		addBeverageButton.setEnabled(false);
		addBeverageButton.addActionListener(this);

		addDessertButton = new JButton("ADD");
		addDessertButton.setEnabled(false);
		addDessertButton.addActionListener(this);

		removeButton = new JButton("REMOVE");
		removeButton.setEnabled(false);
		removeButton.addActionListener(this);

		JLabel lblNewLabel = new JLabel("If you want to remove an item, click \"REMOVE\" button.");


		textBillArea = new JTextArea();
		textBillArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		textBillArea.setBackground(SystemColor.activeCaption);
		textBillArea.setEditable(false);

		JLabel lblOrders = new JLabel("Orders", SwingConstants.CENTER);
		lblOrders.setFont(new Font("Tahoma", Font.BOLD, 16));

		scrollOrders = new JScrollPane();
		scrollOrders.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollOrders.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);


		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 724, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel2, GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel3, GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(spinnerFood, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(addFoodButton, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(spinnerBeverage, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(addBeverageButton, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(spinnerDessert, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(addDessertButton, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(proceedButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblOrders, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(scrollOrders, Alignment.TRAILING)
									.addComponent(lblNewLabel))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textBillArea, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addGap(313))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(312)
								.addComponent(removeButton)
								.addContainerGap()))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(124)
							.addComponent(finishButton, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(spinnerFood, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.addComponent(addFoodButton))
									.addGap(40)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel2, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(spinnerBeverage, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.addComponent(addBeverageButton))
									.addGap(47)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(63)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(spinnerDessert, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.addComponent(addDessertButton)))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(proceedButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel3, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addComponent(lblOrders, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollOrders, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
								.addComponent(textBillArea, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(removeButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
					.addGap(75)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(resetButton, GroupLayout.DEFAULT_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(finishButton, GroupLayout.DEFAULT_SIZE, 80, GroupLayout.PREFERRED_SIZE))
					.addGap(30))
		);


		list = new JList<String>(model);
		scrollOrders.setViewportView(list);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Object rowData[] = new Object[4];
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		table1 = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {

				spinnerFood.setEnabled(true);
				addFoodButton.setEnabled(true);

				return false;
			}
		};
		panel1.add(table1);
		table1.setEnabled(true);

		DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
		String[] fill1 = menu.displayFood().split(",");
		model1.setColumnCount(4);

		for (int k = 0; k < fill1.length - 1;) {
			for (int i = 0; i < 4; i++) {
				rowData[i] = fill1[k];
				k++;
			}
			model1.addRow(rowData);
		}

		String header1[] = { "Name", "Description", "ItemID", "Price" };

		for (int i = 0; i < table1.getColumnCount(); i++) {
			TableColumn column = table1.getTableHeader().getColumnModel().getColumn(i);

			column.setHeaderValue(header1[i]);
		}
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

		table2 = new JTable() {
			private static final long serialVersionUID = 1L;


			public boolean isCellEditable(int row, int column) {

				spinnerBeverage.setEnabled(true);
				addBeverageButton.setEnabled(true);


				return false;
			}
		};
		panel2.add(table2);
		table2.setEnabled(true);
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
		String[] fill2 = menu.displayBeverage().split(",");
		model2.setColumnCount(4);

		for (int k = 0; k < fill2.length - 1;) {
			for (int i = 0; i < 4; i++) {
				rowData[i] = fill2[k];
				k++;
			}
			model2.addRow(rowData);
		}

		String header2[] = { "Name", "Description", "ItemID", "Price" };

		for (int i = 0; i < table2.getColumnCount(); i++) {
			TableColumn column = table2.getTableHeader().getColumnModel().getColumn(i);

			column.setHeaderValue(header2[i]);
		}

		table3 = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {

				spinnerDessert.setEnabled(true);
				addDessertButton.setEnabled(true);

				return false;
			}
		};
		panel3.add(table3);
		table3.setEnabled(true);

		DefaultTableModel model3 = (DefaultTableModel) table3.getModel();
		String[] fill3 = menu.displayDessert().split(",");
		model3.setColumnCount(4);

		for (int k = 0; k < fill3.length - 1;) {
			for (int i = 0; i < 4; i++) {
				rowData[i] = fill3[k];
				k++;
			}
			model3.addRow(rowData);
		}
		
		
		String header3[] = { "Name", "Description", "ItemID", "Price" };

		for (int i = 0; i < table3.getColumnCount(); i++) {
			TableColumn column = table3.getTableHeader().getColumnModel().getColumn(i);

			column.setHeaderValue(header3[i]);
		}

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		frame.getContentPane().setLayout(groupLayout);

		// Set the window to be visible as the default to be false
		frame.pack();
		frame.setVisible(true);


	}
	
	public int getQuantityFood() {

		return (int) spinnerFood.getValue();
	}

	public int getQuantityBeverage() {

		return (int) spinnerBeverage.getValue();
	}

	public int getQuantityDessert() {
		return (int) spinnerDessert.getValue();
	}

	
	public void addOrderstoList(Item item, int quantity) {

		String order = item.getName() + " x " + quantity + " (" + item.getPrice() + "\u00a3)";

		if (quantity != 0) {
			model.add(0, order);
			removeButton.setEnabled(true);
		}
	}

	public double displayBill(Item item, int quantity) {
		double totalBill = 0;
		double bill = 0;
		String orderBill = "";

		for (HashMap.Entry<Item, Integer> entry : newOrders.entrySet()) {
			item = entry.getKey();
			quantity = entry.getValue();
			bill = item.getPrice() * quantity;
			totalBill += bill;
			orderBill = String.format("Total price is:\n" + "%.2f", totalBill);
			textBillArea.setText(orderBill + "\u00a3");
			resetButton.setEnabled(true);
			//System.out.println(entry.getKey().getName() + " " + entry.getValue());
		}
		if  (model.size() == 0){
			textBillArea.setText(" ");
		}
		return totalBill;
	}


	public void discount (Item item, int quantity) {
		double discount = 0;
		String discountBill = "";
		double currentBill = (displayBill(item, quantity));
		try {
		if (displayBill(item, quantity) > 50.0 && displayBill(item, quantity) < 80.0){ 
			discount = currentBill *0.95;
			discountBill = String.format("Total price with 5%% off is:\n" + "%.2f", discount);
			textBillArea.setText(discountBill + "\u00a3");

		}
		else if (displayBill(item, quantity) > 80.0 && displayBill(item, quantity) < 100.0){ 
			discount = currentBill *0.90;
			discountBill = String.format("Total price with 10%% off is:\n" + "%.2f", discount);
			textBillArea.setText(discountBill + "\u00a3");
		}
		else if (displayBill(item, quantity) > 100.0 ){ 
			discount = currentBill *0.85;
			discountBill = String.format("Total price with 15%% off is:\n" + "%.2f", discount);
			textBillArea.setText(discountBill + "\u00a3");
		}
		}catch (IllegalFormatConversionException illegalFormatConversion) {
			System.err.print("Inorrect format for the discount.\n");
			JOptionPane.showMessageDialog(null, "The discount has not the proper format.\nPlease, try again.");
		}
	}

	public void removeOrdersFromList() {
		Item item = null;
		int quantity = 0;
		try {
		String element = model.getElementAt(list.getSelectedIndex());
		int x = element.indexOf("x");
		String name = element.substring(0, x-1);
		for (HashMap.Entry<Item, Integer> entry : newOrders.entrySet()) {
			if (entry.getKey().getName().equals(name)){
				item = entry.getKey();
				quantity = entry.getValue();
			}
		}
		newOrders.remove(item);
		model.remove(list.getSelectedIndex());
		String text = textBillArea.getText();
		double price = displayBill(item,quantity);
		if (!text.equals(" ")){
			if (price < 50){
				counter1 = 0;
			}
			if (price < 80){
				counter2 = 0;
			}
			if (price < 100){
				counter3 = 0;
			}
		}
		else{
			counter1 = 0;
			counter2 = 0;
			counter3 = 0;
		}
		} catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBounds) {
			System.err.print("The element is not in the list.\n");
			JOptionPane.showMessageDialog(null, "The element of the list is inaccessible, because it's not selected.\nPlease, try again and select the item that you want to remove.");
		} catch (NumberFormatException wrongFormat) {
			System.err.print("Not correct format of the price.\n");
			JOptionPane.showMessageDialog(null, "The price does not have the correct format.\nPlease, try again.");
		}
	}

	public HashMap<String, ArrayList<String>> outcoming(){
	 	 String custID = "CUST" + ThreadLocalRandom.current().nextInt(0, 5000 + 1);
		 String arr="";
		    for (int i = 0; i < list.getModel().getSize(); i++) {
		             arr = list.getModel().getElementAt(i);
		             newOrderList.add(arr);
		    }  

		    this.orders = new HashMap <String, ArrayList <String>>();
		for (HashMap.Entry<String, ArrayList<String>> entry : orders.entrySet()) {
			custID = entry.getKey();
			newOrderList = entry.getValue();	
		}
		orders.put(custID, newOrderList);
		System.out.println(orders);
		return orders;
	}
	
	int counter1 = 0;//counter for displaying 5% discount message
	int counter2 = 0;//counter for displaying 10% discount message
	int counter3 = 0;//counter for displaying 15% discount message

	public void actionPerformed(ActionEvent e) {
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////REMOVE BUTTON////////////////////
		if (e.getSource() == removeButton) {
			int back = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to remove one of the items from your order?", null, JOptionPane.YES_NO_OPTION);
			if (back == JOptionPane.YES_OPTION) {
				removeOrdersFromList();
			}
		} 
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////ADD BUTTONS////////////////////
		else if (e.getSource() == addFoodButton) { //addFoodButton

			Item itemFood = new Item(table1.getValueAt(table1.getSelectedRow(), 0).toString(),
					table1.getValueAt(table1.getSelectedRow(), 1).toString(),
					Double.parseDouble(table1.getValueAt(table1.getSelectedRow(), 3).toString()),
					table1.getValueAt(table1.getSelectedRow(), 2).toString(), "Food");
			if (getQuantityFood() > 0) {
				if (newOrders.containsKey(itemFood)){
					String element = itemFood.getName();
					int index = list.getNextMatch(element,0,Position.Bias.Forward);
					model.setElementAt(itemFood.getName() + " x " + getQuantityFood() + " (" + itemFood.getPrice() + "\u00a3)", index);
					newOrders.put(itemFood, getQuantityFood());
					
					displayBill(itemFood, (Integer) getQuantityFood());
				}
				else{
					newOrders.put(itemFood, getQuantityFood());
					addOrderstoList(itemFood, (Integer) getQuantityFood());
					displayBill(itemFood, (Integer) getQuantityFood());
				}
			}
			//////////////////////////////DISCOUNTS/////////////////////////////////////
			if (displayBill(itemFood, (Integer) getQuantityFood()) > 50.0 
					&& displayBill(itemFood, (Integer) getQuantityFood()) < 80.0 && counter1 == 0){
				JOptionPane.showMessageDialog(null, "You will get  5% off because the total cost is over 50\u00a3.");
				discount(itemFood, (Integer) getQuantityFood());
				counter1 = 1;
			}
			else if (displayBill(itemFood, (Integer) getQuantityFood()) > 80.0 
					&& displayBill(itemFood, (Integer) getQuantityFood()) < 100.0 && counter2 == 0){
				JOptionPane.showMessageDialog(null, "You will get  10% off because the total cost is over 80\u00a3.");
				discount(itemFood, (Integer) getQuantityFood());
				counter2 = 1;
			}
			else if (displayBill(itemFood, (Integer) getQuantityFood()) > 100 && counter3 == 0){
				JOptionPane.showMessageDialog(null, "You will get  15% off because the total cost is over 100\u00a3.");
				discount(itemFood, (Integer) getQuantityFood());
				counter3 = 1;
			}
			else {
				discount(itemFood, (Integer) getQuantityFood());
			}
		} 
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		else if (e.getSource() == addBeverageButton) { //addBeverageButton

			Item itemBeverage = new Item(table2.getValueAt(table2.getSelectedRow(), 0).toString(),
					table2.getValueAt(table2.getSelectedRow(), 1).toString(),
					Double.parseDouble(table2.getValueAt(table2.getSelectedRow(), 3).toString()),
					table2.getValueAt(table2.getSelectedRow(), 2).toString(), "Beverage");
			if (getQuantityBeverage() > 0) {
				if (newOrders.containsKey(itemBeverage)){
					String element = itemBeverage.getName();
					int index = list.getNextMatch(element,0,Position.Bias.Forward);
					model.setElementAt(itemBeverage.getName() + " x " + getQuantityBeverage() + " (" + itemBeverage.getPrice() + "\u00a3)", index);
					newOrders.put(itemBeverage, getQuantityBeverage());
					if (list.isSelectedIndex(index) != false)
					removeButton.setEnabled(true);
					displayBill(itemBeverage, (Integer) getQuantityBeverage());
				}
				else{
					newOrders.put(itemBeverage, getQuantityBeverage());
					addOrderstoList(itemBeverage, (Integer) getQuantityBeverage());
					displayBill(itemBeverage, (Integer) getQuantityBeverage());
				}
			}
			//////////////////////////////DISCOUNTS/////////////////////////////////////
			if (displayBill(itemBeverage, (Integer) getQuantityBeverage()) > 50.0 
					&& displayBill(itemBeverage, (Integer) getQuantityBeverage()) < 80.0 && counter1 == 0){
				JOptionPane.showMessageDialog(null, "You will get  5% off because the total cost is over 50\u00a3.");
				discount(itemBeverage, (Integer) getQuantityBeverage());
				counter1 = 1;
			}
			else if (displayBill(itemBeverage, (Integer) getQuantityBeverage()) > 80.0 
					&& displayBill(itemBeverage, (Integer) getQuantityBeverage()) < 100.0 && counter2 == 0){
				JOptionPane.showMessageDialog(null, "You will get  10% off because the total cost is over 80\u00a3.");
				discount(itemBeverage, (Integer) getQuantityBeverage());
				counter2 = 1;
			}
			else if (displayBill(itemBeverage, (Integer) getQuantityBeverage()) > 100 && counter3 == 0){
				JOptionPane.showMessageDialog(null, "You will get  15% off because the total cost is over 100\u00a3.");
				discount(itemBeverage, (Integer) getQuantityBeverage());
				counter3 = 1;
			}
			else {
				discount(itemBeverage, (Integer) getQuantityFood());
			}
		} 
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		else if (e.getSource() == addDessertButton) { //addDessertButton
			Item itemDessert = new Item(table3.getValueAt(table3.getSelectedRow(), 0).toString(),
					table3.getValueAt(table3.getSelectedRow(), 1).toString(),
					Double.parseDouble(table3.getValueAt(table3.getSelectedRow(), 3).toString()),
					table3.getValueAt(table3.getSelectedRow(), 2).toString(), "Dessert");
			if (getQuantityDessert() > 0) {
				if (newOrders.containsKey(itemDessert)){
					String element = itemDessert.getName();
					int index = list.getNextMatch(element,0,Position.Bias.Forward);
					model.setElementAt(itemDessert.getName() + " x " + getQuantityDessert() + " (" + itemDessert.getPrice() + "\u00a3)", index);
					newOrders.put(itemDessert, getQuantityDessert());
					
					displayBill(itemDessert, (Integer) getQuantityDessert());
				}
				else{
					newOrders.put(itemDessert, getQuantityDessert());
					addOrderstoList(itemDessert, (Integer) getQuantityDessert());
					displayBill(itemDessert, (Integer) getQuantityDessert());
				}
			}
			//////////////////////////////DISCOUNTS/////////////////////////////////////
			if (displayBill(itemDessert, (Integer) getQuantityDessert()) > 50.0 
					&& displayBill(itemDessert, (Integer) getQuantityDessert()) < 80.0 && counter1 == 0){
				JOptionPane.showMessageDialog(null, "You will get  5% off because the total cost is over 50\u00a3.");
				discount(itemDessert, (Integer) getQuantityDessert());
				counter1 = 1;
			}
			else if (displayBill(itemDessert, (Integer) getQuantityDessert()) > 80.0 
					&& displayBill(itemDessert, (Integer) getQuantityDessert()) < 100.0 && counter2 == 0){
				JOptionPane.showMessageDialog(null, "You will get  10% off because the total cost is over 80\u00a3.");
				discount(itemDessert, (Integer) getQuantityDessert());
				counter2 = 1;
			}
			else if (displayBill(itemDessert, (Integer) getQuantityDessert()) > 100 && counter3 == 0){
				JOptionPane.showMessageDialog(null, "You will get  15% off because the total cost is over 100\u00a3.");
				discount(itemDessert, (Integer) getQuantityDessert());
				counter3 = 1;
			}
			else {
				discount(itemDessert, (Integer) getQuantityDessert());
			}
		} 
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////PROCEED BUTTON////////////////////
		else if (e.getSource() == proceedButton) {
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to confirm your order?\nA receipt will be sent to you.", null,
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				
				allOrders.makeOrder(outcoming());
			resetButton.setEnabled(true);
			}
		} 
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////RESET BUTTON////////////////////	
		else if (e.getSource() == resetButton) {
			int exit = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset all the values?", null,
					JOptionPane.YES_NO_OPTION);
			if (exit == JOptionPane.YES_OPTION) {
				clear();
			}

		} 
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////FINISH BUTTON////////////////////		
		else if (e.getSource() == finishButton) {
			int exit = JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit the application?", null,
					JOptionPane.YES_NO_OPTION);
			if (exit == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////CLEAR BUTTON////////////////////
	public void clear() {
		spinnerFood.setValue(0);
		spinnerBeverage.setValue(0);
		spinnerDessert.setValue(0);
		addFoodButton.setEnabled(false);
		addBeverageButton.setEnabled(false);
		addDessertButton.setEnabled(false);
		proceedButton.setEnabled(false);
		removeButton.setEnabled(false);
		list.removeAll();
		model.removeAllElements();
		newOrders.clear();
		textBillArea.setText(" ");
		counter1 = 0;//counter for displaying 5% discount message
		counter2 = 0;//counter for displaying 10% discount message
		counter3 = 0;//counter for displaying 15% discount message
	}

}