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
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;

public class StatusGUI extends JFrame implements ActionListener, Observer {

	private static final long serialVersionUID = -7483455288689655101L;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JList<String> listCustomerQueue = new JList<>();
	private ArrayList <Staff> staff;
	private JScrollPane scrollOrders;
	private JTextField textField;
	private JTextField textField_1;
	private QueueCustomer queue;
	private Staff server;
	private JButton simulateButton;
	private JLabel simulationSpeedLabel, threadsLabel;
	private JSlider simulationSpeedSlider;
	
	public StatusGUI()  {
		this.queue = queue;
		JFrame();
	}
	
	private void JFrame() {

		JFrame frame = new JFrame("Shop Simulation");
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBackground(SystemColor.text);
		frame.setLocation(1050, 120);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(372, Short.MAX_VALUE)
					.addComponent(simulateButton, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addGap(342))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAddServer, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addComponent(btnRemoveServer)
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(simulationSpeedLabel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(52))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(simulationSpeedSlider, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(threadsLabel, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addGap(43))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRemoveServer, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAddServer, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(simulationSpeedLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(threadsLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(simulationSpeedSlider, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addGap(13)
									.addComponent(simulateButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
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
