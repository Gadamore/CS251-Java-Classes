package hwk7;

//import hwk6.Room;

public class Room { 
	Window window;
	int numOfWindows;
	
	Room(Window window, int numOfWindows) { 
		this.window = window;
		this.numOfWindows = numOfWindows;
	}
	 
	WindowOrder order() {
		return new WindowOrder(window, numOfWindows);
	}
	@Override
	public String toString() {
		String ret = "" + numOfWindows + " (" + window + ")";
		return ret;
	}
	@Override
	public boolean equals(Object that){
		if (that instanceof Room){
			if (((Room)that).window.equals(this.window) && ((Room)that).numOfWindows == this.numOfWindows){
				return true;
			}
		}
		return false;
	}
}

class MasterBedroom extends Room {
	MasterBedroom() {
		super(new Window(4, 6), 3);
	}
	@Override
	public String toString() {
		String ret = "Master bedroom: " + super.toString();
		return ret;
	}
}

class GuestRoom extends Room {
	GuestRoom() {
		super(new Window(5, 6), 2);
	}
	@Override
	public String toString() {
		String ret = "Guest room: " + super.toString();
		return ret;
	}
}

class LivingRoom extends Room {
	LivingRoom() {
		super(new Window(6, 8), 5);
	}
	@Override
	public String toString() {
		String ret = "Living room: " + super.toString();
		return ret;
	}
}
