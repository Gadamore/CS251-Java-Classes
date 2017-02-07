package hwk7;

//import hwk6.Window;
//import hwk6.WindowOrder;

public class Window {
	private final int width, height;
	
	public Window(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public String toString() {
		return "" + width + " X " + height + " window";
	}
	
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
	final Window window;
	int num;
	
	WindowOrder(Window window, int num) {
		this.window = window;
		this.num = num;
	}
	WindowOrder add (WindowOrder order) {
		if (window.equals(order.window)){
			num = num + order.num;	
			}
		return this;
	}
	WindowOrder times(int number) {
		num = num * number;
		return this;
	}
	
	public String toString() {
		String s = "" + num +  " " + window.toString();
		return s;
	}
	
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
