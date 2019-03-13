package shop;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ourExceptions.InvalidCategoryException;
import ourExceptions.InvalidItemException;
import ourExceptions.InvalidItemIDLengthException;
import ourExceptions.InvalidOrderCustomerID;
import ourExceptions.InvalidOrderTimeStamp;
import ourExceptions.InvalidPriceException;

public class StatusGUI extends JFrame {

	public StatusGUI(){

		JFrame();

	}
	private void JFrame() {

		JFrame frame = new JFrame("Shop Simulation");
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBackground(SystemColor.text);
		frame.setLocation(150, 120);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
