package Geography.appClasses;

import Geography.abstractClasses.GovernedRegion;

public class Country extends GovernedRegion {
	private String nameOfCountry;
	private States states;
	private State state;
	private States globalStates;
	private String stateName;
	private Countries countries;

	public Country(int area, int population, Government government, String nameOfCountry, States globalStates) {
		super(area, population, government);
		this.globalStates = globalStates;
		this.nameOfCountry = nameOfCountry;
		this.states = new States();
		int size = this.globalStates.getSize();
		for (int i = 0; i < size; i++) {
			State state=this.globalStates.getState(i);
			if (state.getCountry().equalsIgnoreCase(this.nameOfCountry)) {
				this.addStateToCountry(globalStates.getState(i));
			}

		}
	}

	public String getNameOfCountry() {
		return nameOfCountry;
	}

	public void setNameOfCountry(String nameOfCountry) {
		this.nameOfCountry = nameOfCountry;

	}

	// Fügt ein Staat-Objekt zur Liste states hinzu
	public void addStateToCountry(State state) {
		this.state = state;
		this.states.addState(state);
	}

//Löscht ein Staat-Objekt aus der Liste states
	public String deleteStateOfCountry(String stateName) {
		this.stateName = stateName;
		String notification = this.states.removeState(stateName);
		return notification;
	}

}
