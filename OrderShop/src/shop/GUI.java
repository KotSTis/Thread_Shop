package shop;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.LayoutStyle.ComponentPlacement;


public class GUI extends JFrame implements ActionListener {

	private Menu menu;

	private JButton foodButton, beverageButton, dessertButton, plusButton, minusButton, proceedButton, cancelButton, finishButton;
	private JLabel orderLabel;


	private JTextArea itemsField;
	private JTextField quantityField;
	private JLabel lb1;
	private JLabel numberLabel = new JLabel("50");
	private JTable table1, table2, table3;
	private static int num = 0;

	public GUI(Menu menuList) throws FileNotFoundException {
		this.menu = menuList;
		JFrame();

	}

	private void JFrame(){
		// Create and set up a frame window
		//JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Best Shoperino");
		frame.setLocation(600, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Define the panels
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		// Set up the title for different panels
		panel1.setBorder(BorderFactory.createTitledBorder("FOOD"));
		panel2.setBorder(BorderFactory.createTitledBorder("DESSERTS"));
		panel3.setBorder(BorderFactory.createTitledBorder("BEVERAGES"));
		
		proceedButton = new JButton("Proceed >");
		proceedButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		proceedButton.setBackground(Color.GREEN);
		proceedButton.setForeground(Color.BLACK);
		proceedButton.addActionListener(this);
		
		cancelButton = new JButton("< Back");
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		cancelButton.setBackground(Color.RED);
		cancelButton.addActionListener(this);
		cancelButton.setEnabled(false);
		
		JTextArea orderDetails = new JTextArea();
		orderDetails.setText("You ordered the following:");
		orderDetails.setEnabled(false);
		
		JList list = new JList();
		
		finishButton = new JButton("Finish");
		finishButton.setForeground(Color.BLACK);
		finishButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		finishButton.setEnabled(true);
		finishButton.setBackground(Color.BLUE);
		finishButton.addActionListener(this);
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
								.addComponent(panel2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
								.addComponent(panel3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(177)
							.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
							.addComponent(finishButton, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(135)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
								.addComponent(orderDetails, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(73)
							.addComponent(proceedButton, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
					.addGap(12))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel2, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel3, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addComponent(finishButton, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addComponent(proceedButton, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
					.addGap(70)
					.addComponent(orderDetails, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(216, Short.MAX_VALUE))
		);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
		Object rowData[] = new Object[4];
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

		JScrollPane scrollPane1 = new JScrollPane();

		table1 = new JTable();
		scrollPane1.setViewportView(table1);
		table1.setEnabled(false);
		String [] fill1= menu.displayFood().split(",");	
		DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
		model1.setColumnCount(4);

		for(int k = 0; k < fill1.length-1;){
			for(int i = 0; i < 4; i++){
				rowData[i]= fill1[k];
				k++;
			}
			model1.addRow(rowData);
		}

		String header1[] = {"Name", "Description", "ItemID", "Price"};

		for(int i=0;i<table1.getColumnCount();i++)
		{
			TableColumn column = table1.getTableHeader().getColumnModel().getColumn(i);

			column.setHeaderValue(header1[i]);
		} 

		panel1.add(scrollPane1);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

		JScrollPane scrollPane2 = new JScrollPane();

		table2 = new JTable();
		scrollPane2.setViewportView(table2);
		table2.setEnabled(false);
		String [] fill2= menu.displayDessert().split(",");

		DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
		model2.setColumnCount(4);


		for(int k = 0; k < fill2.length-1;){
			for(int i = 0; i < 4; i++){
				rowData[i]= fill2[k];
				k++;
			}
			model2.addRow(rowData);
		}

		String header2[] = {"Name", "Description", "ItemID", "Price"};

		for(int i=0;i<table2.getColumnCount();i++)
		{
			TableColumn column = table2.getTableHeader().getColumnModel().getColumn(i);

			column.setHeaderValue(header2[i]);
		} 
		table2.setEnabled(false);

		panel2.add(scrollPane2);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   

		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

		JScrollPane scrollPane3 = new JScrollPane();

		table3 = new JTable();
		scrollPane3.setViewportView(table3);
		table3.setEnabled(false);
		String [] fill3 = menu.displayBeverage().split(",");

		DefaultTableModel model3 = (DefaultTableModel) table3.getModel();
		model3.setColumnCount(4);

		for(int k = 0; k < fill3.length-1;){
			for(int i = 0; i < 4; i++){
				rowData[i]= fill3[k];
				k++;
			}
			model3.addRow(rowData);
		}

		String header3[] = {"Name", "Description", "ItemID", "Price"};

		for(int i=0;i<table3.getColumnCount();i++)
		{
			TableColumn column = table3.getTableHeader().getColumnModel().getColumn(i);

			column.setHeaderValue(header3[i]);
		} 

		panel3.add(scrollPane3);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   

		frame.getContentPane().setLayout(groupLayout);

		// Set the window to be visible as the default to be false
		frame.pack();
		frame.setVisible(true);
		

	}

	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == cancelButton) {
		
		 }
		 else if (e.getSource() == proceedButton) { 
			proceedButton.setEnabled(true);
			JOptionPane.showMessageDialog(null,"Are you sure you want to continue?");
		 }
		 else if (e.getSource() == finishButton) {
			 JOptionPane.showMessageDialog(null,"Are you sure you sure you want to quit?");
			 System.exit(0);
		 }
	}
}