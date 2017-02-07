package hwk7;

import java.util.ArrayList;
import java.util.List;

// This class represents a collection of window orders using an ArrayList
public class TotalOrder {
	List<WindowOrder> orders = new ArrayList<WindowOrder>();
	
	// Add a new window order to this list
	// Make sure to merge the orders for window of the same size
	// Return the current object
	TotalOrder add(WindowOrder newOrder) {
		for (int i = 0; i < orders.size(); i++){
			if (orders.get(i).window.equals(newOrder.window)){
				orders.get(i).add(newOrder);
				return this;
			}
		}
		orders.add(newOrder);
		return this;
	}
	
	// Add another collection of window order
	// Also make sure that the orders for windows of the same size are merged
	// Return the current object
	TotalOrder add(TotalOrder that) {
		WindowOrder order;
		for (int i = 0; i < that.orders.size(); i++){
			order = that.orders.get(i);
			add(order);
		}
		return this;
	}
	
	// Multiple all window orders by "num"
	TotalOrder times(int num) {
		for (int i = 0; i < orders.size(); i++){
			orders.get(i).times(num);
		}
		return this;
	}
	
	@Override
	public String toString() {
		String ret = "";
		
		for (int i = 0; i < orders.size(); i++){
			ret = ret + orders.toString() + "/n";
		}
		return ret;
	}
}
