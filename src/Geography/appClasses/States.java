package Geography.appClasses;

import java.util.ArrayList;

public class States {
	private ArrayList<State> states;
	private String stateName;

	public States() {
		this.states = new ArrayList<State>();
	}

	public void addState(State state) {
		this.states.add(state);

	}

	public int getSize() {
		int size = this.states.size();
		return size;

	}
//Entfernt einen Staat aus der Liste states sofern dieser vorhanden ist
	public String removeState(String stateName) {
		this.stateName = stateName;
		String notification = "";
		for (State e : states) {
			if (e.getNameOfState().equalsIgnoreCase(this.stateName)) {
				states.remove(e);
				notification = "State removed";

			} else {
				notification = "No State " + stateName + " found";
			}
		}
		return notification;
	}

}
