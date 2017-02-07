package hwk6;




// This class contains the configuration of a type of apartment 
public class Apartment {
	int numOfUnits; // the number of apartments of this type
	Room[] rooms; // rooms in this type of apartment
	
	Apartment(int numOfUnits, Room[] rooms) {
		this.numOfUnits = numOfUnits;
		this.rooms = rooms;
	}
	
	// return an array of window orders for one unit of this type of apartment
	WindowOrder[] orderForOneUnit() {
		WindowOrder[] window = new WindowOrder[rooms.length];
		for (int i = 0; i < window.length; i++){
			window[i] = rooms[i].order();
		}
		return window;
	}
	
	// return an array of window orders for all units of this type of apartment
	WindowOrder[] totalOrder() {	
		WindowOrder[] totalWindow = orderForOneUnit();
		for (int i = 0; i < totalWindow.length; i++){
			totalWindow[i].times(numOfUnits);
		}
		return totalWindow;
	}
	
	// return text like:
	//
	// 15 apartments with (Living room: 5 (6 X 8 window)) (Master bedroom: 3 (4 X 6 window)) (Guest room: 2 (5 X 6 window))
	public String toString() {
		String ret = "" + numOfUnits + " apartments with ";
		for (int i = 0; i < rooms.length; i++){
			ret = ret  + "(" + rooms[i].toString()  + ")";
		}
		return ret;
	}
}

class OneBedroom extends Apartment {
	OneBedroom(int numOfUnits) {
		super(numOfUnits, new Room[] { new LivingRoom(), new MasterBedroom() });
	}
}

class TwoBedroom extends Apartment {
	TwoBedroom(int numOfUnits) {
		super(numOfUnits, new Room[] { new LivingRoom(), new MasterBedroom(),
				new GuestRoom() });
	}
}

class ThreeBedroom extends Apartment {
	ThreeBedroom(int numOfUnits) {
		super(numOfUnits, new Room[] { new LivingRoom(), new MasterBedroom(),
				new GuestRoom(), new GuestRoom() });
	}

	// return an array of window orders for all units of this type of apartment
	//
	// Notice we have two guest rooms and they have the same size of windows.
	// override the inherited method to merge the order for the two guest rooms
	// since their windows have the same size
	@Override
	WindowOrder[] orderForOneUnit() {
		WindowOrder[] window = new WindowOrder[rooms.length - 1];
		for (int i = 0; i < window.length; i++){
			window[i] = rooms[i].order();
		}
		window[rooms.length - 2].add(rooms[rooms.length - 1].order());
		return window;
	}
}
