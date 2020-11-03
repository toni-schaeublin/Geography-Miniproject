package Geography.appClasses;

import java.util.ArrayList;

import Geography.abstractClasses.GovernedRegion;

public class Country extends GovernedRegion {
	private String nameOfCountry;
	private ArrayList<State> states;
	private State state;
	private States globalStates;
	private String stateName;
	private Countries countries;

	public Country(int area, int population, Government government, String nameOfCountry) {
		super(area, population, government);
		this.nameOfCountry = nameOfCountry;
		this.states = new ArrayList<>();
	}

	public String getNameOfCountry() {
		return this.nameOfCountry;
	}

	public void setNameOfCountry(String nameOfCountry) {
		this.nameOfCountry = nameOfCountry;

	}

	// Fügt ein Staat-Objekt zur Liste states hinzu
	public void addStateToCountry(State state) {
		this.states.add(state);
	}

//Löscht ein Staat-Objekt aus dem statesArray des Country
	public Boolean deleteStateOfCountry(String stateName) {
		int i = 0;
		Boolean stateDeleted = false;
		for(State s : this.states) {
			i++;
			if (s.getNameOfState().equalsIgnoreCase(stateName)){
				states.remove(i);
				stateDeleted = true;
			}
		}
		return stateDeleted;
	}

}
