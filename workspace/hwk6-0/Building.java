package hwk6;

import java.util.ArrayList;
import java.util.List;

public class Building {
	Apartment[] apartments;
	
	public Building(Apartment[] apartments) {
		this.apartments = apartments;
	}
	
	// Return an array of window orders for all apartments in the building
	// Ensure that the orders for windows of the same sizes are merged.
	WindowOrder[] order() {
		List<WindowOrder> WindowOrders = new ArrayList<WindowOrder>();
		List<WindowOrder> result = new ArrayList<WindowOrder>();
		
		for(Apartment apartment : apartments) {
			for(WindowOrder order : apartment.totalOrder()) {
				WindowOrders.add(order);
			}
		}
		
		for(WindowOrder order : WindowOrders) {
			if (!containsOrder(result, order)) {
				result.add(order);
			} else {
				for(WindowOrder existingOrder : result) {
					if (existingOrder.window.equals(order.window)) {
						existingOrder.add(order);
						break;
					}
				}
			}
		}
		
		WindowOrder[] results = new WindowOrder[result.size()];
		result.toArray(results);
		return results;
	}
	
	private boolean containsOrder(List<WindowOrder> orderArray, WindowOrder order) {
		if (orderArray.isEmpty()) return false;
		
		for(WindowOrder existingOrder : orderArray) {
			if (existingOrder.window.equals(order.window))
				return true;
		}
		return false;
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
			ret += "" + apart.toString() + "\n";
		}
		return ret;
	}
}
