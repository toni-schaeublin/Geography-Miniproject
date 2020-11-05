package Geography.appClasses;

import java.util.ArrayList;

import Geography.ServiceLocator;
import Geography.abstractClasses.Model;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_Model extends Model {
	ServiceLocator serviceLocator;
	private int value;
	private ArrayList<State> statesArray = new ArrayList<>();
	ArrayList<State> statesOfCountryArray = new ArrayList<>();
	private ArrayList<Country> countryArray = new ArrayList<>();

	public App_Model() {
		value = 0;

		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application model initialized");
	}

	public int getValue() {
		return value;
	}

	public int incrementValue() {
		value++;
		serviceLocator.getLogger().info("Application model: value incremented to " + value);
		return value;
	}

	// Methode gibt eine ArrayList mit allen Staaten zurück
	public ArrayList<State> getStates() {
		return statesArray;
	}

	/*Methode gibt eine ArrayList mit allen Staaten die zu einem bestimmten Land gehören zurück*/
	public ArrayList<State> getStatesOfCountry(String country) {
		statesOfCountryArray.clear();
		for (State s : statesArray) {
			if (s.getCountry().equalsIgnoreCase(country)) {
				statesOfCountryArray.add(s);
			}
		}
		return statesOfCountryArray;
	}

	// Methode weist der ArrayList statesArray eine ArrayList mit Staaten zu
	public void setStates(ArrayList<State> states) {
		this.statesArray = states;
	}

	// Methode gibt eine ArrayList mit allen Ländern zurück
	public ArrayList<Country> getCountries() {
		return countryArray;
	}

	// Methode weist der ArrayList countryArray eine ArrayList mit Countries zu
	public void setCountries(ArrayList<Country> countries) {
		this.countryArray = countries;
	}



	

	public void initializeGlobalLists() {
		State state = new State(0, 0, Government.none, "", "");
		statesArray.add(state);
		State state1 = new State(1, 1, Government.Anarchy, "aaa", "aaa");
		State state2 = new State(1, 1, Government.Anarchy, "bbb", "aaa");
		State state3 = new State(1, 1, Government.Anarchy, "ccc", "aaa");
		statesArray.add(state1);
		statesArray.add(state2);
		statesArray.add(state3);
		Country country = new Country(0, 0, Government.none, "none");
		countryArray.add(country);
	}
}
