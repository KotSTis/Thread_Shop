package shop;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GUI extends JFrame implements ActionListener {

	private Menu menu;

	private JButton addFoodButton, addBeverageButton, addDessertButton, removeButton, proceedButton, resetButton,
			finishButton;
	private JSpinner spinnerFood, spinnerBeverage, spinnerDessert;

	private JTable table1, table2, table3;
	private HashMap<Item, Integer> newOrders;
	private JTextArea textBillArea;
	private AllOrders allOrders;
	private String order[] = new String[3];
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JScrollPane scrollOrders;
	private JList<String> list = new JList<>();

	public GUI(Menu menuList, HashMap<Item, Integer> newOrdersList) throws FileNotFoundException {
		this.menu = menuList;
		this.newOrders = newOrdersList;
		JFrame();

	}

	private void JFrame() {
		// Create and set up a frame window
		// JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Best Shoperino");
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBackground(SystemColor.text);
		frame.setLocation(100, 20);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Define the panel to hold the buttons
		JPanel panel1 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel2 = new JPanel();

		// Set up the title for different panels
		panel1.setBorder(BorderFactory.createTitledBorder("FOOD"));
		panel3.setBorder(BorderFactory.createTitledBorder("DESSERTS"));
		panel2.setBorder(BorderFactory.createTitledBorder("BEVERAGES"));

		proceedButton = new JButton("PROCEED >");
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

		finishButton = new JButton("FINISH");
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
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false).addGroup(groupLayout
						.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(panel2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 724,
										Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)).addGroup(
								groupLayout.createSequentialGroup()
										.addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 271,
												GroupLayout.PREFERRED_SIZE)
										.addGap(30)))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(finishButton, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
								.addGap(27).addComponent(proceedButton, GroupLayout.PREFERRED_SIZE, 222,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(spinnerFood, GroupLayout.PREFERRED_SIZE, 64,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(spinnerBeverage, GroupLayout.PREFERRED_SIZE, 64,
																GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(addFoodButton, GroupLayout.PREFERRED_SIZE, 68,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(addBeverageButton, GroupLayout.PREFERRED_SIZE, 68,
																GroupLayout.PREFERRED_SIZE)))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(spinnerDessert, GroupLayout.PREFERRED_SIZE, 64,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(addDessertButton, GroupLayout.PREFERRED_SIZE, 68,
														GroupLayout.PREFERRED_SIZE)))
								.addGap(52)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(scrollOrders, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(textBillArea,
														GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup().addGap(269).addComponent(removeButton,
								GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(299).addComponent(lblOrders)))
				.addGap(1353)));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(140).addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
										.createSequentialGroup().addGap(49).addGroup(groupLayout
												.createParallelGroup(Alignment.TRAILING)
												.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 127,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																.addComponent(spinnerFood, GroupLayout.PREFERRED_SIZE,
																		40, GroupLayout.PREFERRED_SIZE)
																.addComponent(addFoodButton))
														.addGap(60)))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(
																panel2, GroupLayout.PREFERRED_SIZE, 137,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup().addGap(41)
														.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																.addComponent(
																		spinnerBeverage, GroupLayout.PREFERRED_SIZE, 40,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(addBeverageButton))))
										.addGap(42)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
												groupLayout.createSequentialGroup().addGap(36).addGroup(groupLayout
														.createParallelGroup(Alignment.BASELINE)
														.addComponent(spinnerDessert, GroupLayout.PREFERRED_SIZE, 40,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(addDessertButton)))
												.addComponent(
														panel3, GroupLayout.PREFERRED_SIZE, 134,
														GroupLayout.PREFERRED_SIZE)))
								.addGroup(
										groupLayout.createSequentialGroup().addGap(36)
												.addComponent(lblOrders, GroupLayout.PREFERRED_SIZE, 25,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(scrollOrders, GroupLayout.DEFAULT_SIZE, 332,
																Short.MAX_VALUE)
														.addComponent(textBillArea, GroupLayout.PREFERRED_SIZE, 168,
																GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 43,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(removeButton, GroupLayout.DEFAULT_SIZE, 31,
														Short.MAX_VALUE)
												.addGap(5)))
						.addGap(78)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(finishButton, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addComponent(proceedButton, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
								.addComponent(resetButton, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
						.addGap(1302)));

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

	public void addFoodToList() {

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

//	public String displayOrder(Item item) {
//		double totalBill = 0;
//		double bill = 0;
//		String orders ="";
//		String orderBill = "";
//		String orderList = "";
//		double discount = 0;
//		for (HashMap.Entry<Item, Integer> entry : newOrders.entrySet()) {
//
//			item = entry.getKey();
//			int quantity = entry.getValue();
//			order[0] = item.getName();
//			order[1] = String.valueOf(quantity);
//			order[2] = String.valueOf(item.getPrice());
//
//			orders += order[0] + " x " + order[1] + " (" + order[2] + "£)";
//			orderList = orders;
//			bill = Integer.parseInt(order[1]) * Double.parseDouble(order[2]);
//			
//			totalBill += bill;
//			orderBill = String.format("Total price is:\n" + "%.2f", totalBill);
//
//			textBillArea.setText(orderBill + "£");
//			textOrderArea.append(String.valueOf(orders));
//			
//				
//		}
//		return orderList + "\n";
//
//	}

	public void addOrderstoList(Item item, int quantity) {

		String order = item.getName() + " x " + quantity + " (" + item.getPrice() + "£)";

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
			textBillArea.setText(orderBill + "£");

		}

		return totalBill;
	}

	public void discount(Item item, int quantity) {
		double discount = 0;
		String discountBill = "";
		double currentBill = (displayBill(item, quantity));
		if (displayBill(item, quantity) > 50.0)
			JOptionPane.showMessageDialog(null, "You will get  5% off because the total cost is over 50£.");
		discount = currentBill * 0.95;
		discountBill = String.format("Total price is:\n" + "%.2f", discount);
		textBillArea.setText(discountBill + "£");
	}

	public void removeOrdersFromList() {

		model.remove(list.getSelectedIndex());

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == removeButton) {
			int back = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove one of your orders?", null,
					JOptionPane.YES_NO_OPTION);
			if (back == JOptionPane.YES_OPTION) {
				removeOrdersFromList();
			}
		} else if (e.getSource() == addFoodButton) {
			resetButton.setEnabled(true);

			Item itemFood = new Item(table1.getValueAt(table1.getSelectedRow(), 0).toString(),
					table1.getValueAt(table1.getSelectedRow(), 1).toString(),
					Double.parseDouble(table1.getValueAt(table1.getSelectedRow(), 3).toString()),
					table1.getValueAt(table1.getSelectedRow(), 2).toString(), "Food");

			if (getQuantityFood() > 0) {
				newOrders.put(itemFood, getQuantityFood());
			}
			addOrderstoList(itemFood, (Integer) getQuantityFood());
			displayBill(itemFood, (Integer) getQuantityFood());
			discount(itemFood, (Integer) getQuantityFood());

		} else if (e.getSource() == addBeverageButton) {
			resetButton.setEnabled(true);

			Item itemBeverage = new Item(table2.getValueAt(table2.getSelectedRow(), 0).toString(),
					table2.getValueAt(table2.getSelectedRow(), 1).toString(),
					Double.parseDouble(table2.getValueAt(table2.getSelectedRow(), 3).toString()),
					table2.getValueAt(table2.getSelectedRow(), 2).toString(), "Beverage");

			if (getQuantityBeverage() > 0) {
				newOrders.put(itemBeverage, getQuantityBeverage());
			}

			addOrderstoList(itemBeverage, (Integer) getQuantityBeverage());
			displayBill(itemBeverage, (Integer) getQuantityBeverage());
			discount(itemBeverage, (Integer) getQuantityBeverage());

		} else if (e.getSource() == addDessertButton) {
			resetButton.setEnabled(true);

			Item itemDessert = new Item(table3.getValueAt(table3.getSelectedRow(), 0).toString(),
					table3.getValueAt(table3.getSelectedRow(), 1).toString(),
					Double.parseDouble(table3.getValueAt(table3.getSelectedRow(), 3).toString()),
					table3.getValueAt(table3.getSelectedRow(), 2).toString(), "Dessert");

			if (getQuantityDessert() > 0) {
				newOrders.put(itemDessert, getQuantityDessert());
			}

			addOrderstoList(itemDessert, (Integer) getQuantityDessert());
			displayBill(itemDessert, (Integer) getQuantityDessert());
			discount(itemDessert, (Integer) getQuantityDessert());

		} else if (e.getSource() == proceedButton) {
			JOptionPane.showMessageDialog(null,
					"Are you sure you sure you want to continue?\nThe total cost will be displayed.");
			resetButton.setEnabled(true);

		} else if (e.getSource() == resetButton) {
			JOptionPane.showMessageDialog(null, "Are you sure you sure you want to reset all values?");
			clear();

		} else if (e.getSource() == finishButton) {
			int exit = JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit the application?", null,
					JOptionPane.YES_NO_OPTION);
			if (exit == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	public void clear() {
		spinnerFood.setValue(0);
		spinnerBeverage.setValue(0);
		spinnerDessert.setValue(0);
		textBillArea.selectAll();
		textBillArea.replaceSelection("");
		addFoodButton.setEnabled(false);
		addBeverageButton.setEnabled(false);
		addDessertButton.setEnabled(false);
		proceedButton.setEnabled(false);
		removeButton.setEnabled(false);
		list.removeAll();
		model.removeAllElements();
	}
}