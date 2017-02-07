package hwk6;

public class Building {
	Apartment[] apartments;
	
	public Building(Apartment[] apartments) {
		this.apartments = apartments;
	}
	
	// Return an array of window orders for all apartments in the building
	// Ensure that the orders for windows of the same sizes are merged.
	WindowOrder[] order() {
		WindowOrder[] window = new WindowOrder[apartments.length];
		WindowOrder[] otherWindow = new WindowOrder[apartments.length];
		Apartment apartment;
		WindowOrder[] order;
		for (int i = 0; i < apartments.length; i++){
			apartment = apartments[i];
			order = apartment.totalOrder();
			for (int j = 0; j < apartment.totalOrder().length; i++){
				window[i].add(order[i]);
			}
		}
		for (int k = 0; k < window.length; k++){
			if (window[k].equals(order[k])){
				
			}
			else{
				window[k].add(order[k]);
			}
		}
		return window;
	}
	
	private WindowOrder[] updateWindowOrder(WindowOrder[] orderArray, WindowOrder[] newOrderArray) {
		WindowOrder[] ret;

		if(orderArray.length < newOrderArray.length) {
		ret = newOrderArray;
		newOrderArray = orderArray;
		}
		else {
		ret = orderArray;
		}
		for(int i = 0; i < newOrderArray.length; i++) {
		ret[i].add(newOrderArray[i]);
		}
		return ret;
		}
	
	// return a string to represent all types of apartments in the building such as:
	// 20 apartments with (Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))
	// 15 apartments with (Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))(Guest room: 2 (5 X 6 window))
	// 10 apartments with (Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))(Guest room: 2 (5 X 6 window))(Guest room: 2 (5 X 6 window))
	// 
	public String toString() {
		String ret = "";
		
		for (int i = 0; i < apartments.length; i++){
			Apartment apart = apartments[i];
			ret += apart.toString();
		}
		return ret;
	}
}
