package Geography.appClasses;

import java.util.ArrayList;

import Geography.abstractClasses.GovernedRegion;

public class Country extends GovernedRegion {
	private ArrayList<State> states;
	
	
	public Country(String nameOfCountry, int area, int population, Government government) {
		super(nameOfCountry, area, population, government);
		this.states = new ArrayList<>();
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
			if (s.getName().equalsIgnoreCase(stateName)){
				states.remove(i);
				stateDeleted = true;
			}
		}
		return stateDeleted;
	}
	public String toString() {
		return (super.getName()+",     Area: "+ Integer.toString(super.getArea())+" km^2,"+"     Population: " 
	+Integer.toString(super.getPopulation())+",     "+super.getGovernment());
	}

}
