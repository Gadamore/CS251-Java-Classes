package lab10;
 
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel; 
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Lab10 extends JFrame {
	Lab10() {
		this.setTitle("Keypad");
		this.setSize(300, 300);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		final JFrame frame = new Lab10();
		
		// label is used to display the digits (right aligned) on the keypad
		final JLabel label = new JLabel("0");
		label.setFont(new Font("Arial", Font.PLAIN, 40));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// panel is used to hold the buttons on the keypad
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 3, 4, 4));
		
		// buttons array store the digit buttons
		final JButton[] buttons = new JButton[10];
		final JButton dot = new JButton(".");
		final JButton clear = new JButton("C");
		
		final Keypad pad = new Keypad();
		final ActionListener l = new ActionListener(){

			// use event source to find out which button is clicked
			// call method on "pad" to change the state of the keypad
			// then use the "print" method to get the text to be displayed 
			//      and set the text of the label
			@Override
			public void actionPerformed(ActionEvent e) {
				Object src = e.getSource();
				if (src == dot){
					pad.addDot();
				}
				else if (src == clear){
					pad.clear();
				}
				else{
					for (int i = 0; i < buttons.length; i++){
						if (src == buttons[i]){
							pad.addDigit(i);
						}
					}
				}
				label.setText(pad.print());
			}
			
		};

		// add the listener to all buttons
		for (int j = 0; j < buttons.length; j++){
					buttons[j] = new JButton("" + j); 
					buttons[j].addActionListener(l);
				}
		
		// add the buttons to the panel
		panel.add(buttons[7]);
		panel.add(buttons[8]);
		panel.add(buttons[9]);
		panel.add(buttons[4]);
		panel.add(buttons[5]);
		panel.add(buttons[6]);
		panel.add(buttons[1]);
		panel.add(buttons[2]);
		panel.add(buttons[3]);
		panel.add(clear);
		panel.add(buttons[0]);
		panel.add(dot);
		// add the label (north) and the panel (center) to the frame
		frame.add(label, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.CENTER);
		dot.addActionListener(l);
		clear.addActionListener(l);
		
		frame.setVisible(true);
	}
}

// This class tracks the state of the keypad when digits and dot are added 
// It also resets the keypad state through clear method and generates the string to be displayed
class Keypad {
	String integer = "0", fraction = "";
	boolean hasDot = false;
	
	// reset the keypad state so that it displays 0
	void clear() {
		integer = "0";
		fraction = "";
		hasDot = false;
	}
	// return the string to be displayed
	String print() {
		if (!hasDot){
			return integer;
		}
		else {
			return integer + "." + fraction;
		}
	}
	// change the keypad state to indicate dot is added (so it can have fractions)
	void addDot() { 
		if (!hasDot){
			hasDot = true;
		}
	}
	// change the keypad state to include a digit
	// must not allow leading 0 unless it is a fraction
	void addDigit(int i) {
		if (!hasDot){
			if (integer.equals("0")){
				integer = "" + i;
			}
			else {
				integer = integer + i;
			}
		}
		else {
			fraction = fraction + i;
		}
	}
}
