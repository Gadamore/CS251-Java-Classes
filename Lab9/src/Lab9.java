

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Lab9 extends JFrame {
	Lab9() {
		setTitle("Temperature Converter"); // set frame title
		this.setBounds(500, 0, 1000, 200); // set frame bounds
		this.setLayout(new FlowLayout()); // set frame layout
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //creates an X to close the window
	}
	
	public static void main(String[] args) {
		JFrame frame = new Lab9();
		
		// create labels, textfields, and buttons
		JLabel convertF = new JLabel("Fahrenheit"); // the String is the name of the lable
		JLabel convertC = new JLabel("Celsius");
		final JTextField fahTF = new JTextField(7); // 7 is the size of the TextField
		final JTextField celTF = new JTextField(7); // final WAS RECOMENDED BY JAVA
		JButton buttF = new JButton("Convert"); // the String is the name of the button
		JButton buttC = new JButton("Convert");
		
		// add them to the frame
		frame.add(convertF); // Fahrenheit set
		frame.add(fahTF);
		frame.add(buttF);
		
		frame.add(convertC); // Celsius set
		frame.add(celTF);
		frame.add(buttC);
		
		// create listener for fahrenheit to celsius conversion 
		ActionListener f2c = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				try {
					Double dCelsius = (5.0/9.0) * ((Double.parseDouble(fahTF.getText())) - 32.0);
					String sCelsius = String.format("%.2f", dCelsius);
					celTF.setText(sCelsius);
				}
				catch (NumberFormatException n){
					celTF.setText("Undefined");
				}
			}
		};
		buttF.addActionListener(f2c); // add listener f2c to the right button

		
		// create listener for celsius to fahrenheit conversion
		ActionListener c2f = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				try {
					double dFharenheit = (9.0/5.0) * (Double.parseDouble(celTF.getText())) + 32;
					String sFharenheit = String.format("%.2f", dFharenheit);
					fahTF.setText(sFharenheit);
				}
				catch (NumberFormatException n){
					fahTF.setText("Undefined");
				}
			}
		};
		buttC.addActionListener(c2f); // add listener c2f to the right button
		
		frame.setVisible(true); // make frame visible

	}
}
