package hwk6;

public class Window {
	private final int width, height;
	
	public Window(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	// print text like: 4 X 6 window
	public String toString() {
		return "" + width + " X " + height + " window";
	}
	
	// compare window objects by their dimensions
	public boolean equals(Object that) {
		if (that instanceof Window) {
			if (((Window)that).width == this.width && ((Window)that).height == this.height) {
				return true;
			}
		}
		return false;
	}
	
}

class WindowOrder {
	final Window window; // window description (its width and height)
	int num;             // number of windows for this order
	
	WindowOrder(Window window, int num) {
		this.window = window;
		this.num = num;
	}

	// add the num field of the parameter to the num field of this object
	//
	// BUT
	//
	//   do the merging only of two windows have the same size
	//   do nothing if the size does not match
	// 
	// return the current object
	WindowOrder add (WindowOrder order) {
			if (window.equals(order.window)){
			num = num + order.num;	
			}
		return this;
	}

	// update the num field of this object by multiplying it with the parameter
	// and then return the current object
	WindowOrder times(int number) {
		num = num * number;
		return this;
	}
	
	// print text like: 20 4 X 6 window
	@Override
	public String toString() {
		String s = "" + num +  " " + window.toString();
		return s;
	}

	// Two orders are equal if they contain the same number of windows of the same size.
	@Override
	public boolean equals(Object that) {
		if (that instanceof WindowOrder){
			if (((WindowOrder)that).window.equals(this.window) && ((WindowOrder)that).num == this.num){
				return true;
			}
		}
		return false;
	}
}
