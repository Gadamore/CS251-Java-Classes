
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout; 
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.JRadioButton;
import javax.swing.UIManager;

public class Dice extends JFrame{
	Dice(){
		this.setTitle("Dices");
		this.setBounds(0, 0, 600, 600); 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] ards) throws IOException {
		JFrame frame = new Dice();
		frame.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		
		JLabel four = new JLabel("4");
		JLabel six = new JLabel("6");
		JLabel eight = new JLabel("8");
		JLabel ten = new JLabel("10");
		JLabel twelve = new JLabel("12");
		JLabel twenty = new JLabel("20");
		
		
	}
	
}
