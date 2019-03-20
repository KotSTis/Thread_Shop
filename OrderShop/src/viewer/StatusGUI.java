package viewer;

import model.QueueCustomer;
import model.Staff;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;
import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidOrderCustomerID;
import ourExceptions.InvalidOrderCustomerIDException;
import ourExceptions.InvalidOrderCustomerNameException;
import ourExceptions.InvalidOrderTimeStamp;
import ourExceptions.InvalidOrderTimeStampException;
import ourExceptions.InvalidPriceException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class StatusGUI extends JFrame implements ActionListener, Observer {

	private static final long serialVersionUID = -7483455288689655101L;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JList<String> listCustomerQueue = new JList<>();
	private ArrayList <Staff> staff;
	private JScrollPane scrollOrders;
	private JTextField textField;
	private JTextField textField_1;
	private QueueCustomer queue;
	private JButton simulateButton = new JButton();

	public StatusGUI()  {
		this.queue = queue;
		JFrame();
	}
	
	private void JFrame() {

		JFrame frame = new JFrame("Shop Simulation");
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBackground(SystemColor.text);
		frame.setLocation(1100, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		JPanel panel_1 = new JPanel();
		
		// Set up the title for different panels
		panel.setBorder(BorderFactory.createTitledBorder("Waiting Queue"));
		panel_1.setBorder(BorderFactory.createTitledBorder("Servers"));
		
		simulateButton = new JButton("SIMULATE");
		simulateButton.addActionListener(this);
		simulateButton.setEnabled(true);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
				.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(304)
					.addComponent(simulateButton, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(347, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(simulateButton, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addGap(41))
		);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(textField, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
				.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
		);
		panel_1.setLayout(gl_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
					.addGap(0))
		);
		
		listCustomerQueue = new JList<String>(model);
		scrollPane.setViewportView(listCustomerQueue);
		panel.setLayout(gl_panel);
		
		frame.getContentPane().setLayout(groupLayout);
		
		//frame.setContentPane(panel);
	    frame.pack();
		frame.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == simulateButton){
			startSimulation();
		}

	}
	
	public void startSimulation(){
		int servers = 1;
		model.addElement("FUCK YOU!");
		for (int i =0; i < servers; i++){
			try {
			Staff serverInstance = new Staff(servers);
			Thread thread = new Thread(serverInstance);
			thread.start();
			Thread.sleep(1);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			
			
		}
		
	}

	

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Staff){
			
		}else if (arg1 instanceof QueueCustomer){
			
		}
		
	}
}
