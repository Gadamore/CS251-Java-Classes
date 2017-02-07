package hwk10;

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
import javax.swing.UIManager;
 

@SuppressWarnings("serial")
public class Hwk10  extends JFrame {
	Hwk10() {
		this.setTitle("Tic Tac Toe");
		this.setBounds(0, 0, 600, 600); 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) throws IOException { 
		try{  
			  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch(Exception e) {
			  System.out.println(e);
			}
		
		JFrame frame = new Hwk10();
		frame.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JButton newGame = new JButton("New Game");
		newGame.setAlignmentX(CENTER_ALIGNMENT);
		
		panel.add(newGame);
		
		JPanel box = new JPanel();
		box.setLayout(new GridLayout(3, 3));
		
		JButton[] buttons = new JButton[9];
		for (int i = 0; i < buttons.length; i++){
			buttons[i] = new JButton("");
			box.add(buttons[i]);
		}
		
		final TicTacToe game = new TicTacToe(buttons);
		
		ActionListener clearGame = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.clear();
			}
		};
		newGame.addActionListener(clearGame);
		
		frame.add(panel, BorderLayout.NORTH);
		frame.add(box, BorderLayout.CENTER);
		
		frame.setVisible(true);
		Testing10.test(frame, buttons, newGame, game);
	}
}

class TicTacToe {
	int[] data = new int[9];
	JButton[] buttons;
	boolean isX = true; 

	// this variable keeps track of how many steps the game has played (it cannot be >= 9)
	int steps = 0;

	// use the flag to indicate whether the game has ended due to that X wins or O wins but not due to a draw
	boolean gameEnded = true;
	
	ImageIcon icon_x, icon_o; 
	
	// save buttons, load icon images, and clear the game (by calling "clear" method)
	TicTacToe(JButton[] buttons) throws IOException {
		this.buttons = buttons; 
		icon_x = new ImageIcon(ImageIO.read(getClass().getResource("X.png")));
		icon_o = new ImageIcon(ImageIO.read(getClass().getResource("O.png"))); 
		clear(); 
	} 
	
	// add the same listener to all buttons
	void addListener() {
		for (int i = 0; i < buttons.length; i++){
			buttons[i].addActionListener(buttonListener);
		}
	}
	// create an action listener
	ActionListener buttonListener = new ActionListener() {

		// 1. Use the event source to find out which button is clicked
		// 2. Find out if anyone wins and if so, has it X or O player won
		// 3. In the console, 
		//      if X wins, print "X wins", 
		//      if O wins, print "O wins", 
		//      if it is a draw (max number of steps has reached), print "Draw" 
		//      if nobody wins, do nothing and continue the game
		// 4. Don't forget to increment the "steps" variable to find out whether max number of steps has reached.
		// 5. Switch player in this method as well if it is a legal move.
		@Override
		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource();
			for (int i = 0; i < buttons.length; i++){
				if (src == buttons[i]){
					if (play(i, isX) == true){
						isX = !isX;
						steps++;
						break;
					}
				}
			}
			if (win() == 1){
				removeListener();
				System.out.println("X wins");
			}
			else if (win() == 2){
				removeListener();
				System.out.println("O wins");
			}
			else if (win() == 0 && steps == 9){
				if (steps == 9){
					removeListener();
				}
				System.out.println("draw");
			}
		}
	};
	
	// reset "steps" to 0
	// for each button, set the background color to "white" and remove icons from buttons "setIcon(null)"
	// reset "data" array to 0
	// if game has ended (due to someone winnning), add listener back to buttons and reset the flag to false
	void clear() {
		steps = 0;
		//isX = true;
		if (gameEnded == true){
			gameEnded = false;
			addListener();
		}
		for (int i = 0; i < data.length; i++){
			data[i] = 0;
		}
		for (int i = 0; i < buttons.length; i++){
			buttons[i].setIcon(null);
			buttons[i].setBackground(Color.white);
		}
	}
	
	// remove listener from all buttons and set "gameEnded" flag to true
	void removeListener() {
		for (int i = 0; i < buttons.length; i++){
			buttons[i].removeActionListener(buttonListener);;
		}
		gameEnded = true;
	}
	
	// put X or O at i
	// if a button click is legal (i.e. the button was no clicked before), set icons to the button and update "data" array accordingly
	// return true if and only if the button click is legal
	boolean play(int i, boolean isX) {
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
	
	// if X wins, return 1, 
	// if O wins, return 2, 
	// if draws or no result, return 0
	//
	// if someone wins, the remove listeners so that subsequent clicks won't change the remaining unclicked buttons
	int win() {
		for(int row = 0; row < 3; row++)
		{
			if (checkRow(row) > 0) 
				return checkRow(row);
		}
		for(int column = 0; column < 3; column++) {
			if (checkColumn(column) > 0)
				return checkColumn(column);
		}
		
		return checkDiagonal();
	}

	// check whether the ith row matches
	//       if so, set the background for that row of buttons to blue color
	//       and also return 1 if X wins, 2 if O wins, 0 if nobody wins
	int checkRow(int i) {
		int array[] = new int[3];
		
		int o = i * 3; // this is for 
		if (1 == data[0 + o] && 1 ==  data[1 + o] && 1 == data[2 + o]){
			for (int k = 0; k < array.length; k++){
				array[k] = k + o;
				}
			setBackground(array);
			return 1;
		}
		else if (2 == data[0 + o] && 2 ==  data[1 + o] && 2 == data[2 + o]){
			for (int k = 0; k < array.length; k++){				
				array[k] = k + o;
			}
			setBackground(array);
			return 2;
		}
		else {
			return 0;
			}
	}

	// check whether the ith column matches
	//       if so, set the background for that column of buttons to blue color
	//       and also return 1 if X wins, 2 if O wins, 0 if nobody wins
	int checkColumn(int i) {
		int array[] = new int[3];
		
		if (1 == data[i] && 1 == data[i + 3] && 1 == data[i + 6]){
			for (int k = 0; k < array.length; k++){
				int o = (k * 3);
				array[k] = i + o;
			}
			setBackground(array);
			return 1;
		}
		else if (2 == data[i] && 2 == data[i + 3] && 2 == data[i + 6]){
			for (int k = 0; k < array.length; k++){
				int o = (k * 3);
				array[k] = k + o;
			}
			setBackground(array);
			return 2;
		}
		else {
			return 0;
		}
	}

	// check whether a diagonal matches
	//       if so, set the background for that diagonal of buttons to blue color
	//       and also return 1 if X wins, 2 if O wins, 0 if nobody wins
	// note that there are two diagonals to check
	int checkDiagonal() {
		int array[] = new int[3];
		if (1 == data[0] && 1 == data[4] && 1 == data[8]){
			for (int j = 0; j < 3; j++){
				buttons[j * 4].setBackground(Color.BLUE);
				array[j] = j * 4;
			}
			setBackground(array);
			return 1;
		}
		else if (2 == data[0] && 2 == data[4] && 2 == data[8]){
			for (int j = 0; j < 3; j++){
				buttons[j * 4].setBackground(Color.BLUE);
				array[j] = j * 4;
			}
			setBackground(array);
			return 1;
		}
		else if (1 == data[6] && 1 == data[4] && 1 == data[2]){
			for (int k = 0; k < array.length; k++){
				array[k] = (k + 1) * 2;
			}
			setBackground(array);
			return 2;
		}
		else if (2 == data[6] && 2 == data[4] && 2 == data[2]){
			for (int k = 0; k < array.length; k++){
				array[k] = (k + 1) * 2;
			}
			setBackground(array);
			return 2;
		}
		else{
			return 0;
		}
	}

	// set buttons at the specified indices to blue
	void setBackground(int[] indices) {
		for (int i = 0; i < indices.length; i ++){
			buttons[indices[i]].setBackground(Color.BLUE);
		}
		
	}
}
