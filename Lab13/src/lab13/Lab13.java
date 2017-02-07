package lab13;

import javax.imageio.ImageIO;
import javax.swing.*; 

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Lab13 {
	public static void main (String[] args) {
		JFrame frame = new SmileyFrame();
		frame.add(new SmileyPanel());
		frame.setVisible(true);
	}
}

class SmileyFrame extends JFrame {
	private static final long serialVersionUID = 7613378514394599117L;
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400; 

	public SmileyFrame () {
		setTitle("Drag Smiley");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
	}  
}  


class SmileyPanel extends JPanel { 
	private static final long serialVersionUID = 6650015376897677969L;
	private ImageIcon SMILEY = null;
	private ImageIcon SCARED = null;
	private final int WIDTH;
	private final int HEIGHT;

	private Point imageCorner; // image's top-left corner location
	private Point prevPt;      // mouse location for previous event
	private ImageIcon image;   // toggles between smiley and scared
	private boolean grabbed = false; // whether the icon image is grabbed 

	public SmileyPanel() {
		try {
			SMILEY = new ImageIcon(ImageIO.read(getClass().getResource("smiley.gif")));
			SCARED = new ImageIcon(ImageIO.read(getClass().getResource("scared.gif")));
		}
		catch(IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		WIDTH = SMILEY.getIconWidth();
		HEIGHT = SMILEY.getIconHeight();

		image = SMILEY;
		imageCorner = new Point(0, 0); // image starts at top left
		ClickListener clickListener = new ClickListener();
		DragListener dragListener = new DragListener();
		this.addMouseListener(clickListener);
		this.addMouseMotionListener(dragListener);
	}  

	// check whether "point" is within the (rectangular) boundary of the image 
	// (use "imageCorner", width, and height)
	boolean isClickInBound(Point p) {
		return (p.getX() >= imageCorner.getX() && p.getX() <= imageCorner.getX() + WIDTH 
			&& p.getY() >= imageCorner.getY() && p.getY() <= imageCorner.getY() + HEIGHT);
	}

	private class ClickListener extends MouseAdapter {
		// If mouse is pressed within the boundary of the image,
		// 1. set grabbed to true
		// 2. change image to "scared"
		// 3. save the mouse position
		// 4. repaint
		public void mousePressed(MouseEvent e) {
			if (isClickInBound(e.getPoint())) {
				grabbed = true;
				image = SCARED;
				prevPt = e.getPoint();
				repaint();
			}
		} 

		// If mouse released and image was grabbed
		// 1. reset grabbed to false
		// 2. reset image to "smiley"
		// 3. repaint
		public void mouseReleased(MouseEvent e) {
			if (grabbed) {
				grabbed = false;
				image = SMILEY;
				repaint();
			}
		}  
	}  

	private class DragListener extends MouseMotionAdapter {
		
		// If image was grabbed, 
		// 1. change "imageCorner" based on the difference between current mouse position and previous mouse position when the image was grabbed
		// 2. set the current point as previous point
		// 3. repaint
		public void mouseDragged(MouseEvent e) {
			Point currentPoint = e.getPoint();
			if (grabbed) {
				imageCorner.translate(
				(int) (currentPoint.getX() - prevPt.getX()),
				(int) (currentPoint.getY() - prevPt.getY()));
				prevPt = currentPoint;
				repaint();
			}
		}  
	}  

	// Draw the window, including the updated image.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		image.paintIcon(this, g, (int) imageCorner.getX(), (int) imageCorner.getY());
	}  
}  
