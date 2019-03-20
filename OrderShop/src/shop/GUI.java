/* authors: Kontogeorgos Georgios & Mitrousis Alexandros
 * All copyrights reserved 2019-2020
 */

package shop;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
//import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.Position;

import controller.AllOrders;

import javax.swing.LayoutStyle.ComponentPlacement;

import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidOrderTimeStampException;
import ourExceptions.InvalidPriceException;
import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;
import ourExceptions.InvalidOrderCustomerIDException;
import ourExceptions.InvalidOrderCustomerNameException;

// The GUI interface that displays 3 tables
// Each table contains an item's category. Each category has its own button
// By an item from the table and adding it to a Jlist, price is displayed on a Textarea
// New price is displayed when the user reaches a certain amount
public class GUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7483455288689655101L;
	private Menu menu = new Menu();
	private JButton addFoodButton, addBeverageButton, addDessertButton, removeButton, proceedButton, resetButton,
			finishButton;
	private JSpinner spinnerFood, spinnerBeverage, spinnerDessert;
	private AllOrders allOrders = new AllOrders();
	private JTable table1, table2, table3;
	private HashMap<Item, Integer> newOrders;
	private JTextArea textBillArea, textQueueArea;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JScrollPane scrollOrders;
	private JList<String> list = new JList<>();
	int counter = 1;
	// private static final int SLEEP_MINUTES = 1;
	// private long endTime;
	// private Timer timer;

	public GUI() throws FileNotFoundException, InvalidPriceException, InvalidCategoryException,
			InvalidOrderTimeStampException, InvalidOrderCustomerIDException, InvalidOrderCustomerNameException,
			InvalidItemIDLengthException, InvalidItemException {

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
		resetButton.setBackground(Color.LIGHT_GRAY);
		resetButton.addActionListener(this);
		resetButton.setEnabled(false);

		finishButton = new JButton("EXIT");
		finishButton.setForeground(Color.BLACK);
		finishButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		finishButton.setEnabled(true);
		finishButton.setBackground(SystemColor.inactiveCaption);
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

		JTextArea txtrOffDiscount = new JTextArea();
		txtrOffDiscount.setForeground(Color.RED);
		txtrOffDiscount.setText(
				"5% off discount if price is between 50 and 80\n10% off discount if price is between 80 and 100\n15% off discount if price is over 100");

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel1, GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
						.addComponent(panel2, GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
						.addComponent(panel3, GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(0)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(spinnerDessert, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(addDessertButton, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(4)
											.addComponent(spinnerBeverage, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(addBeverageButton, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(spinnerFood, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(addFoodButton, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGap(92)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblOrders, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(1)
											.addComponent(scrollOrders, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(proceedButton, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(75)
									.addComponent(finishButton, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textBillArea, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtrOffDiscount, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(338)
							.addComponent(removeButton, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(366))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel1, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(47)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
										.addComponent(spinnerFood, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.addComponent(addFoodButton))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel2, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(addBeverageButton)
										.addComponent(spinnerBeverage, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
									.addGap(42)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel3, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(55)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(spinnerDessert, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
										.addComponent(addDessertButton))
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addComponent(lblOrders, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textBillArea, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(txtrOffDiscount, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addGap(0, 6, Short.MAX_VALUE))
								.addComponent(scrollOrders, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(removeButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(proceedButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addGap(6)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(resetButton, GroupLayout.DEFAULT_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(finishButton, GroupLayout.DEFAULT_SIZE, 80, GroupLayout.PREFERRED_SIZE))
					.addGap(30))
		);

		// initialize the list with strings
		list = new JList<String>(model);
		scrollOrders.setViewportView(list);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		Object rowData[] = new Object[4];
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		table1 = new JTable() {
			private static final long serialVersionUID = 1L;

			// disable editing the table
			public boolean isCellEditable(int row, int column) {

				spinnerFood.setEnabled(true);
				addFoodButton.setEnabled(true);

				return false;
			}
		};
		panel1.add(table1);
		table1.setEnabled(true);

		DefaultTableModel model1 = (DefaultTableModel) table1.getModel();

		// splitting needed to get each value and store them to each cell of the
		// table
		String[] fill1 = menu.displayFood().split(",");
		model1.setColumnCount(4);

		for (int k = 0; k < fill1.length - 1;) {
			for (int i = 0; i < 4; i++) {
				rowData[i] = fill1[k];
				k++;
			}
			model1.addRow(rowData);
		}

		panel1.add(new JScrollPane(table1));
		String header1[] = { "Name", "Description", "ItemID", "Price" };

		TableColumnModel columnModel1 = table1.getColumnModel();
		columnModel1.getColumn(0).setPreferredWidth(200);
		columnModel1.getColumn(1).setPreferredWidth(600);
		columnModel1.getColumn(2).setPreferredWidth(150);
		columnModel1.getColumn(3).setPreferredWidth(80);

		for (int i = 0; i < table1.getColumnCount(); i++) {
			TableColumn column = table1.getTableHeader().getColumnModel().getColumn(i);

			column.setHeaderValue(header1[i]);
		}

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

		table2 = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// disable editing the table
				spinnerBeverage.setEnabled(true);
				addBeverageButton.setEnabled(true);

				return false;
			}
		};
		panel2.add(table2);
		table2.setEnabled(true);
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		DefaultTableModel model2 = (DefaultTableModel) table2.getModel();

		// splitting needed to get each value and store them to each cell of the
		// table
		String[] fill2 = menu.displayBeverage().split(",");
		model2.setColumnCount(4);

		for (int k = 0; k < fill2.length - 1;) {
			for (int i = 0; i < 4; i++) {
				rowData[i] = fill2[k];
				k++;
			}
			model2.addRow(rowData);
		}

		panel2.add(new JScrollPane(table2));
		String header2[] = { "Name", "Description", "ItemID", "Price" };

		TableColumnModel columnModel2 = table2.getColumnModel();
		columnModel2.getColumn(0).setPreferredWidth(200);
		columnModel2.getColumn(1).setPreferredWidth(600);
		columnModel2.getColumn(2).setPreferredWidth(150);
		columnModel2.getColumn(3).setPreferredWidth(80);

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

		// splitting needed to get each value and store them to each cell of the
		// table
		String[] fill3 = menu.displayDessert().split(",");
		model3.setColumnCount(4);

		for (int k = 0; k < fill3.length - 1;) {
			for (int i = 0; i < 4; i++) {
				rowData[i] = fill3[k];
				k++;
			}
			model3.addRow(rowData);
		}

		panel3.add(new JScrollPane(table3));
		String header3[] = { "Name", "Description", "ItemID", "Price" };

		TableColumnModel columnModel3 = table3.getColumnModel();
		columnModel3.getColumn(0).setPreferredWidth(200);
		columnModel3.getColumn(1).setPreferredWidth(600);
		columnModel3.getColumn(2).setPreferredWidth(150);
		columnModel3.getColumn(3).setPreferredWidth(80);

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

	// Get the quantity of foods inserted in the box
	public int getQuantityFood() {

		return (int) spinnerFood.getValue();
	}

	// Get the quantity of beverages inserted in the box
	public int getQuantityBeverage() {

		return (int) spinnerBeverage.getValue();
	}

	// Get the quantity of desserts inserted in the box
	public int getQuantityDessert() {
		return (int) spinnerDessert.getValue();
	}

	// when the user selects an item from the table, these item's details are
	// inserted to the list
	public void addOrderstoList(Item item, int quantity) {

		String order = item.getName() + " x " + quantity + " (" + item.getPrice() + "\u00a3)";

		if (quantity != 0) {
			model.add(0, order);
			removeButton.setEnabled(true);
		}
	}

	// display the bill in the textarea
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
			// .println(entry.getKey().getName() + " " + entry.getValue());
		}
		if (model.size() == 0) {
			textBillArea.setText(" ");
		}
		return totalBill;
	}

	// discount 5% if the price is between 50 and 80,
	// 10% if the price is between 80 and 100,
	// and 15% if it's above 100
	public void discount(Item item, int quantity) {
		double discount = 0;
		String discountBill = "";
		double currentBill = (displayBill(item, quantity));
		try {
			if (displayBill(item, quantity) > 50.0 && displayBill(item, quantity) <= 80.0) {
				discount = currentBill * 0.95;
				discountBill = String.format("Total price with 5%% off is:\n" + "%.2f", discount);
				textBillArea.setText(discountBill + "\u00a3");

			} else if (displayBill(item, quantity) > 80.0 && displayBill(item, quantity) <= 100.0) {
				discount = currentBill * 0.90;
				discountBill = String.format("Total price with 10%% off is:\n" + "%.2f", discount);
				textBillArea.setText(discountBill + "\u00a3");
			} else if (displayBill(item, quantity) > 100.0) {
				discount = currentBill * 0.85;
				discountBill = String.format("Total price with 15%% off is:\n" + "%.2f", discount);
				textBillArea.setText(discountBill + "\u00a3");
			}
		} catch (IllegalFormatConversionException illegalFormatConversion) {
			System.err.print("Inorrect format for the discount.\n");
			JOptionPane.showMessageDialog(null, "The discount has not the proper format.\nPlease, try again.");
		}
	}

	// feature to remove an item from the list
	// when it's selected, item is entirely removed... total price and discount
	// will change too
	public void removeOrdersFromList() {
		Item item = null;
		int quantity = 0;
		try {
			String element = model.getElementAt(list.getSelectedIndex());
			int x = element.indexOf("x");
			String name = element.substring(0, x - 1);
			for (HashMap.Entry<Item, Integer> entry : newOrders.entrySet()) {
				if (entry.getKey().getName().equals(name)) {
					item = entry.getKey();
					quantity = entry.getValue();
				}
			}
			newOrders.remove(item);
			model.remove(list.getSelectedIndex());
			String text = textBillArea.getText();
			double price = displayBill(item, quantity);
			if (!text.equals(" ")) {
				if (price < 50) {
					counter1 = 0;
				}
				if (price < 80) {
					counter2 = 0;
				}
				if (price < 100) {
					counter3 = 0;
				}
			} else {
				counter1 = 0;
				counter2 = 0;
				counter3 = 0;
			}
		} catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBounds) {
			System.err.print("The element is not in the list.\n");
			JOptionPane.showMessageDialog(null,
					"The element of the list is inaccessible, because it's not selected.\nPlease, try again and select the item that you want to remove.");
		} catch (NumberFormatException wrongFormat) {
			System.err.print("Not correct format of the price.\n");
			JOptionPane.showMessageDialog(null, "The price does not have the correct format.\nPlease, try again.");
		}
	}

	int counter1 = 0;// counter for displaying 5% discount message
	int counter2 = 0;// counter for displaying 10% discount message
	int counter3 = 0;// counter for displaying 15% discount message


	public void actionPerformed(ActionEvent e) {
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////// REMOVE BUTTON////////////////////
		if (e.getSource() == removeButton) {

			int remove = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to remove one of the items from your order?", null,
					JOptionPane.YES_NO_OPTION);
			if (remove == JOptionPane.YES_OPTION) {
				removeOrdersFromList();
			}

			spinnerFood.setValue(0);
			spinnerBeverage.setValue(0);
			spinnerDessert.setValue(0);
			addFoodButton.setEnabled(false);
			addBeverageButton.setEnabled(false);
			addDessertButton.setEnabled(false);
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////// ADD BUTTONS////////////////////
		else if (e.getSource() == addFoodButton) { // addFoodButton

			Item itemFood = new Item(table1.getValueAt(table1.getSelectedRow(), 0).toString(),
					table1.getValueAt(table1.getSelectedRow(), 1).toString(),
					Double.parseDouble(table1.getValueAt(table1.getSelectedRow(), 3).toString()),
					table1.getValueAt(table1.getSelectedRow(), 2).toString(), "Food");
			// if box is not zero, then we put the food's items in the list
			if (getQuantityFood() > 0) {
				if (newOrders.containsKey(itemFood)) {
					String element = itemFood.getName();
					int index = list.getNextMatch(element, 0, Position.Bias.Forward);
					model.setElementAt(
							itemFood.getName() + " x " + getQuantityFood() + " (" + itemFood.getPrice() + "\u00a3)",
							index);
					newOrders.put(itemFood, getQuantityFood());
					displayBill(itemFood, (Integer) getQuantityFood());
				} else {
					newOrders.put(itemFood, getQuantityFood());
					addOrderstoList(itemFood, (Integer) getQuantityFood());
					displayBill(itemFood, (Integer) getQuantityFood());
				}
			}
			////////////////////////////// DISCOUNTS/////////////////////////////////////
			if (displayBill(itemFood, (Integer) getQuantityFood()) > 50.0
					&& displayBill(itemFood, (Integer) getQuantityFood()) < 80.0 && counter1 == 0) {
				JOptionPane.showMessageDialog(null, "You will get  5% off because the total cost is over 50\u00a3.");
				discount(itemFood, (Integer) getQuantityFood());
				counter1 = 1;
			} else if (displayBill(itemFood, (Integer) getQuantityFood()) > 80.0
					&& displayBill(itemFood, (Integer) getQuantityFood()) < 100.0 && counter2 == 0) {
				JOptionPane.showMessageDialog(null, "You will get  10% off because the total cost is over 80\u00a3.");
				discount(itemFood, (Integer) getQuantityFood());
				counter2 = 1;
			} else if (displayBill(itemFood, (Integer) getQuantityFood()) > 100 && counter3 == 0) {
				JOptionPane.showMessageDialog(null, "You will get  15% off because the total cost is over 100\u00a3.");
				discount(itemFood, (Integer) getQuantityFood());
				counter3 = 1;
			} else {
				discount(itemFood, (Integer) getQuantityFood());
			}
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		else if (e.getSource() == addBeverageButton) { // addBeverageButton

			Item itemBeverage = new Item(table2.getValueAt(table2.getSelectedRow(), 0).toString(),
					table2.getValueAt(table2.getSelectedRow(), 1).toString(),
					Double.parseDouble(table2.getValueAt(table2.getSelectedRow(), 3).toString()),
					table2.getValueAt(table2.getSelectedRow(), 2).toString(), "Beverage");
			// if box is not zero, then we put the beverage's items in the list
			if (getQuantityBeverage() > 0) {
				if (newOrders.containsKey(itemBeverage)) {
					String element = itemBeverage.getName();
					int index = list.getNextMatch(element, 0, Position.Bias.Forward);
					model.setElementAt(itemBeverage.getName() + " x " + getQuantityBeverage() + " ("
							+ itemBeverage.getPrice() + "\u00a3)", index);
					newOrders.put(itemBeverage, getQuantityBeverage());
					displayBill(itemBeverage, (Integer) getQuantityBeverage());
				} else {
					newOrders.put(itemBeverage, getQuantityBeverage());
					addOrderstoList(itemBeverage, (Integer) getQuantityBeverage());
					displayBill(itemBeverage, (Integer) getQuantityBeverage());
				}
			}
			////////////////////////////// DISCOUNTS/////////////////////////////////////
			if (displayBill(itemBeverage, (Integer) getQuantityBeverage()) > 50.0
					&& displayBill(itemBeverage, (Integer) getQuantityBeverage()) < 80.0 && counter1 == 0) {
				JOptionPane.showMessageDialog(null, "You will get  5% off because the total cost is over 50\u00a3.");
				discount(itemBeverage, (Integer) getQuantityBeverage());
				counter1 = 1;
			} else if (displayBill(itemBeverage, (Integer) getQuantityBeverage()) > 80.0
					&& displayBill(itemBeverage, (Integer) getQuantityBeverage()) < 100.0 && counter2 == 0) {
				JOptionPane.showMessageDialog(null, "You will get  10% off because the total cost is over 80\u00a3.");
				discount(itemBeverage, (Integer) getQuantityBeverage());
				counter2 = 1;
			} else if (displayBill(itemBeverage, (Integer) getQuantityBeverage()) > 100 && counter3 == 0) {
				JOptionPane.showMessageDialog(null, "You will get  15% off because the total cost is over 100\u00a3.");
				discount(itemBeverage, (Integer) getQuantityBeverage());
				counter3 = 1;
			} else {
				discount(itemBeverage, (Integer) getQuantityBeverage());
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		else if (e.getSource() == addDessertButton) { // addDessertButton
			Item itemDessert = new Item(table3.getValueAt(table3.getSelectedRow(), 0).toString(),
					table3.getValueAt(table3.getSelectedRow(), 1).toString(),
					Double.parseDouble(table3.getValueAt(table3.getSelectedRow(), 3).toString()),
					table3.getValueAt(table3.getSelectedRow(), 2).toString(), "Dessert");
			// if box is not zero, then we put the dessert's items in the list
			if (getQuantityDessert() > 0) {
				if (newOrders.containsKey(itemDessert)) {
					String element = itemDessert.getName();
					int index = list.getNextMatch(element, 0, Position.Bias.Forward);
					model.setElementAt(itemDessert.getName() + " x " + getQuantityDessert() + " ("
							+ itemDessert.getPrice() + "\u00a3)", index);
					newOrders.put(itemDessert, getQuantityDessert());
					displayBill(itemDessert, (Integer) getQuantityDessert());
				} else {
					newOrders.put(itemDessert, getQuantityDessert());
					addOrderstoList(itemDessert, (Integer) getQuantityDessert());
					displayBill(itemDessert, (Integer) getQuantityDessert());
				}
			}
			////////////////////////////// DISCOUNTS/////////////////////////////////////
			if (displayBill(itemDessert, (Integer) getQuantityDessert()) > 50.0
					&& displayBill(itemDessert, (Integer) getQuantityDessert()) < 80.0 && counter1 == 0) {
				JOptionPane.showMessageDialog(null, "You will get  5% off because the total cost is over 50\u00a3.");
				discount(itemDessert, (Integer) getQuantityDessert());
				counter1 = 1;
			} else if (displayBill(itemDessert, (Integer) getQuantityDessert()) > 80.0
					&& displayBill(itemDessert, (Integer) getQuantityDessert()) < 100.0 && counter2 == 0) {
				JOptionPane.showMessageDialog(null, "You will get  10% off because the total cost is over 80\u00a3.");
				discount(itemDessert, (Integer) getQuantityDessert());
				counter2 = 1;
			} else if (displayBill(itemDessert, (Integer) getQuantityDessert()) > 100 && counter3 == 0) {
				JOptionPane.showMessageDialog(null, "You will get  15% off because the total cost is over 100\u00a3.");
				discount(itemDessert, (Integer) getQuantityDessert());
				counter3 = 1;
			} else {
				discount(itemDessert, (Integer) getQuantityDessert());
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////// PROCEED BUTTON////////////////////
		else if (e.getSource() == proceedButton) {
			if (model.isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"There is nothing to confirm, since there are no orders.\nPlease make an order.");

			} else {
				// JOptionPane.showMessageDialog(null, "Please wait 1 minute.
				// Processing previous orders...");
				// endTime =
				// System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(SLEEP_MINUTES);
				// //define the end time
				// timer = new Timer(500, removeButton->{ //Loop every 1000ms
				// a.k.a 1second
				// long millisBeforeEnd = endTime - System.currentTimeMillis();
				// if (millisBeforeEnd < 0) {
				// timer.stop();
				int confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to confirm your order?\nClick EXIT to get your receipt.", null,
						JOptionPane.YES_NO_OPTION);
				int onlineOption = JOptionPane.showConfirmDialog(null,
						"Is this an online order?", null,
						JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					// allOrders.putOrder(newOrders);
					allOrders.makeOrder(newOrders);
					allOrders.getAllCustomerOrders();
					
					if (onlineOption == JOptionPane.YES_OPTION) {
						clear();
					}
					clear();
				} // Restore the text to your label, or write whatever you want.
					// return;
					// }
					// long secsBeforeEnd =
					// TimeUnit.MILLISECONDS.toSeconds(millisBeforeEnd);
					// //Convert
					// the millis to seconds.
					// textBillArea.setText(secsBeforeEnd+"");
					// /*
					// int hours = (int)
					// TimeUnit.MILLISECONDS.toHours(millisBeforeEnd);
					// int mins = (int)
					// (TimeUnit.MILLISECONDS.toMinutes(millisBeforeEnd) - hours
					// *
					// 60);
					// int secs = (int)
					// (TimeUnit.MILLISECONDS.toSeconds(millisBeforeEnd) - mins
					// * 60
					// - hours * 60 * 60);
					// lab1.setText(String.format("%02d:%02d:%02d", hours, mins,
					// secs));
					// */
					// });
					// timer.setInitialDelay(0); //Don't loose the first second.
					// timer.start(); //Start the timer (in stop button, just
					// add
					// timer.stop())

			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////// RESET BUTTON////////////////////
		else if (e.getSource() == resetButton) {
			int exit = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset all the values?", null,
					JOptionPane.YES_NO_OPTION);
			if (exit == JOptionPane.YES_OPTION) {
				clear();
			}

		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////// FINISH BUTTON////////////////////
		else if (e.getSource() == finishButton) {
			int exit = JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit the application?", null,
					JOptionPane.YES_NO_OPTION);
			if (exit == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "A receipt will be sent to you now.");
				try {
					allOrders.FinalReport("Report.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////// CLEAR BUTTON////////////////////
	public void clear() {
		spinnerFood.setValue(0);
		spinnerBeverage.setValue(0);
		spinnerDessert.setValue(0);
		addFoodButton.setEnabled(false);
		addBeverageButton.setEnabled(false);
		addDessertButton.setEnabled(false);
		removeButton.setEnabled(false);
		list.removeAll();
		model.removeAllElements();
		newOrders.clear();
		textBillArea.setText(" ");

		counter1 = 0;// counter for displaying 5% discount message
		counter2 = 0;// counter for displaying 10% discount message
		counter3 = 0;// counter for displaying 15% discount message
	}
}