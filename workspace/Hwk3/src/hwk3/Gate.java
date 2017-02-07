package hwk3;

enum State {
	IN, OUT, LOCKED
}

public class Gate {
	private State tripod;
	// If negative, then it means the number of persons gone outside through
	// this gate
	private int personGoneInside = 0;
	private String name;

	// Gate Constructor
	public Gate(String name) {
		this(name, State.LOCKED);
	}

	public Gate(String name, State state) { 
		this.name = name;
		this.tripod = state;
	}

	public int numPersonGoneInside() {
		return personGoneInside; 
	}

	// To set the tripod state
	public void setTripod(State dir) { 
		tripod = dir;
	}

	// return true if and only if the gate is currently locked
	public boolean open(State dir) {// the state of the tripod
		if (isLocked()){ // was locked part
			setTripod(dir);
			if (!isLocked()){ // is lock part
				setTripod(dir);
				return true;
			}
			else{ // nested if statement are ugly
				setTripod(dir);
				return false; // learn to do boolean cleaner
			}
		}
		else{
			setTripod(dir);
			return false;
		}
	}

	// to close the gate
	public void close() { 
		setTripod(State.LOCKED);
	}

	// to check if the gate is locked or not
	public boolean isLocked() {
		if (tripodState() == State.LOCKED){
			return true;
		}
		else{
			return false;
		}
	}

	// to return the tripod state
	public State tripodState() {
		return tripod;
	}

	// x number of person wants to get in through this gate
	public void getIn(int x) { 
		if (tripodState() == State.IN){
			personGoneInside += x;
		}
	}

	// x number of person wants to get out through this gate
	public void getOut(int x) { 
		if (tripodState() == State.OUT){
			personGoneInside -= x;
		}
	}

	@Override
	public String toString() {
		int people = numPersonGoneInside();
		String askExit = "came in";
		if (people <= 0){
			people = people * -1;
			askExit = "went out";
		}
		
		String in = "Gate " + name + " is open to get in.\n" + people + " persons " + askExit + " through this gate\n";
		String out = "Gate " + name + " is open to get out.\n" + people + " persons " + askExit + " through this gate\n";
		String locked = "Gate " + name + " is locked.\n" + people + " persons " + askExit + " through this gate\n";
		switch(tripod){
			case IN:{
				return in;
			}
			case OUT:{
				return out;
			}
			case LOCKED:{
				return locked;
			}
		}
		return null;
	}

}
