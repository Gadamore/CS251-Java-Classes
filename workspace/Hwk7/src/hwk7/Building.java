package hwk7;

//import hwk6.Apartment;
 
public class Building {
	Apartment[] apartments;
	
	public Building(Apartment[] units) {
		this.apartments = units;
	}
	
	// Return the total order all apartments in this building
	TotalOrder order() {
		TotalOrder orders = new TotalOrder();
		for (int i = 0; i < apartments.length; i ++){
			orders.add(apartments[i].totalOrder());
		}
		return orders;
	}
	
	public String toString() {
		String ret = "";

		for (int i = 0; i < apartments.length; i++){
			Apartment apart = apartments[i];
			ret += "" + apart.toString() + "\n";
		}
		return ret;
	}
}
