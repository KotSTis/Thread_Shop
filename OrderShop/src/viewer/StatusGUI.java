
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
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.AllOrders;
import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;
import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidOrderCustomerID;
import ourExceptions.InvalidOrderCustomerIDException;
import ourExceptions.InvalidOrderCustomerNameException;
import ourExceptions.InvalidOrderTimeStamp;
import ourExceptions.InvalidOrderTimeStampException;
import ourExceptions.InvalidPriceException;
import shop.CsvReader;
import shop.Order;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;

public class StatusGUI extends JFrame implements ActionListener, Observer {

	private static final long serialVersionUID = -7483455288689655101L;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JList<String> listCustomerQueue = new JList<>();
	private ArrayList <Staff> staff;
	private ArrayList <Order> orders = new ArrayList <Order>();
	private JScrollPane scrollOrders;
	private JTextField textField;
	private JTextField textField_1;
	private QueueCustomer queue;
	private Staff server;
	private AllOrders allProcessedOrders = new AllOrders();
	private JButton simulateButton;
	private JLabel simulationSpeedLabel, threadsLabel;
	private JSlider simulationSpeedSlider;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public StatusGUI() throws FileNotFoundException, InvalidPriceException, InvalidCategoryException,
	InvalidOrderTimeStampException, InvalidOrderCustomerIDException, InvalidOrderCustomerNameException,
	InvalidItemIDLengthException, InvalidItemException {

		this.queue = queue;
		JFrame();

	}
	
	private void JFrame() {

		JFrame frame = new JFrame("Shop Simulation");
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBackground(SystemColor.text);
		frame.setLocation(1050, 120);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
		JPanel panel = new JPanel();
		JPanel panel_1 = new JPanel();
		
		// Set up the title for different panels
		panel.setBorder(BorderFactory.createTitledBorder("Waiting Queue"));
		panel_1.setBorder(BorderFactory.createTitledBorder("Servers"));
		
		simulationSpeedSlider = new JSlider(0, 10);
		simulationSpeedSlider.setPaintTicks(true);
		simulationSpeedSlider.setPaintLabels(true);
		simulationSpeedSlider.setMajorTickSpacing(5);
		simulationSpeedSlider.setBackground(SystemColor.inactiveCaptionBorder);
		
		simulationSpeedLabel = new JLabel("Simulation Speed:");
		simulationSpeedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		simulateButton = new JButton("SIMULATE");
		simulateButton.setEnabled(true);
		
		JButton btnRemoveServer = new JButton("REMOVE SERVER");
		
		JButton btnAddServer = new JButton("ADD SERVER");
		
		threadsLabel = new JLabel("Thread's Simulation Time: 5 seconds" );
		threadsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(btnAddServer, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRemoveServer, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(simulationSpeedSlider, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(threadsLabel, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
						.addComponent(simulationSpeedLabel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(328, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(431)
					.addComponent(simulateButton, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addGap(486))
				.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1181, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1061, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(0)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAddServer, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRemoveServer, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(simulationSpeedLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(simulationSpeedSlider, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addComponent(threadsLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(simulateButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
		);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
						.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
						.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
						.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
					.addGap(5))
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
		
		simulationSpeedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				threadsLabel.setText("Thread's Simulation Time: " + Integer.toString(((JSlider) e.getSource()).getValue()) + " seconds");
			}
		});
		
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
			
		}
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		String customerList = queue.get_top().toString();
		if (arg0 == queue){
			QueueCustomer q = (QueueCustomer) arg0;
		}
		else if (arg0 == server){
			
		}
	}
}
