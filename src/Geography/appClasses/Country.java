package Geography.appClasses;

import Geography.abstractClasses.GovernedRegion;

public class Country extends GovernedRegion {
	private String nameOfCountry;
	private States states;
	private State state;
	private String stateName;

	public Country(double area, double population, Government government, String nameOfCountry) {
		super(area, population, government);
		this.nameOfCountry = nameOfCountry;
		this.states = new States();

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
