package hwk7;
 

public class Apartment {
	int numOfApartments; // the number of apartments of this type
	Room[] rooms; // rooms in this type of apartment
	
	Apartment(int numOfApartments, Room[] rooms) {
		this.numOfApartments = numOfApartments;
		this.rooms = rooms;
	}
	
	// Return the window orders for one apartment of this type as TotalOrder object
	TotalOrder orderForOneUnit() {
		TotalOrder orders = new TotalOrder();
		for (int i = 0; i < rooms.length; i++){
			orders.add(rooms[i].order());
		}
		return orders;
	}
	
	// Return the window orders for all apartments of this type
	TotalOrder totalOrder() {
		TotalOrder totalOrders = orderForOneUnit();
		totalOrders.times(this.numOfApartments);
		return totalOrders;
	}
	
	public String toString() {
		String ret = "" + numOfApartments + " apartments with ";
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
		super(numOfUnits, new Room[] { new LivingRoom(), new MasterBedroom(), new GuestRoom() });
	}
}

class ThreeBedroom extends Apartment {
	ThreeBedroom(int numOfUnits) {
		super(numOfUnits, new Room[] { new LivingRoom(), new MasterBedroom(), new GuestRoom(), new GuestRoom() });
	}
}
