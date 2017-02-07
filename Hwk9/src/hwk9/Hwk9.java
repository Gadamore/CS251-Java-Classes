package hwk9;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JPanel;
 

@SuppressWarnings("serial")
public class Hwk9  extends JFrame {
	Hwk9() {
		this.setTitle("Tic Tac Toe");
		this.setBounds(0, 0, 600, 600); 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) throws IOException { 
		// create a frame with border layout
		JFrame frame = new Hwk9();
		frame.setLayout(new BorderLayout());
		
		// create a panel with box layer aligned with y axis
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		// create a new game button that is centered on x axis
		JButton newGame = new JButton("New Game");
		newGame.setAlignmentX(CENTER_ALIGNMENT);
		
		
		// add the new game button to the game panel
		panel.add(newGame);

		// create a box panel with 3 x 3 grid layout
		JPanel box = new JPanel();
		box.setLayout(new GridLayout(3, 3));
		
		// create an array of 9 buttons and add them to the box panel
		JButton[] buttons = new JButton[9];
		for (int i = 0; i < buttons.length; i++){
			buttons[i] = new JButton("");
			box.add(buttons[i]);
		}
		
		// instantiate a tic-tac-toe game object 
		final TicTacToe game = new TicTacToe(buttons);
		
		// add an action listener to the new-game buttom so that it clears the game on click.
		ActionListener clearGame = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.clear();
			}
		};
		newGame.addActionListener(clearGame);
		
		// add game panel (north) and box panel (center) to the frame and set it visible
		frame.add(panel, BorderLayout.NORTH);
		frame.add(box, BorderLayout.CENTER);
		
		frame.setVisible(true); // make frame visible

		// TODO: Uncomment the line below for testing
		//Testing.test(frame, buttons, newGame, game);
		
	}
}

class TicTacToe {
	// this array holds flags for buttons
	//   0 means not clicked
	//   1 means X
	//   2 means O
	int[] data = new int[9];

	// array of buttons representing the grid cells of the game
	JButton[] buttons;

	// this means next move will be X if true and O if false
	boolean isX = true; 
	
	// use this fields to hold image icons loaded from files
	ImageIcon icon_x, icon_o; 
	
	// save buttons array
	// load icons
	// clear the grid (call the clear method)
	// add listener (call the addListener method)
	TicTacToe(JButton[] buttons) throws IOException {
		this.buttons = buttons;
		icon_x = new ImageIcon(ImageIO.read(getClass().getResource("X.png")));
		icon_o = new ImageIcon(ImageIO.read(getClass().getResource("O.png")));
		clear();
		addListener();
	}
	 
	// add action the same button listner to all buttons
	private void addListener() {
		for (int i = 0; i < buttons.length; i++){
			buttons[i].addActionListener(buttonListener);
		}
	}

	// create a button listener object directly from ActionListener interface
	// by overridding its actionPerformed method
	//
	// the actionPerformed method will find out 
	//              which button is clicked and 
	//              whether the click is successful and switch the player if so
	ActionListener buttonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource();
			for (int i = 0; i < buttons.length; i++){
				if (src == buttons[i]){
					if (play(i, isX) == true){
						play(i, isX);
						isX = !isX;
						break;
					}
				}
			}
		}
	};
	
	// set the background color to each button to white 
	// remove icons by calling setIcon(null)
	// clear the data array to 0
	// reset isX to true
	void clear() {
		isX = true;
		for (int i = 0; i < data.length; i++){
			data[i] = 0;
		}
		for (int i = 0; i < buttons.length; i++){
			buttons[i].setIcon(null);
			buttons[i].setBackground(Color.white);
		}
		
	}
	  
	// put X or O at the i'th button
	// set the data array to 1 for X and 2 for O
	//
	// returns true if i'th button is not already clicked
	private boolean play(int i, boolean isX) {
		if (data[i] == 0){
			if (isX){
				buttons[i].setIcon(icon_x);
				data[i] = 1;
			}
			else {
				buttons[i].setIcon(icon_o);
				data[i] = 2;
			}
				return true;
		}
		return false;
	}  
}

