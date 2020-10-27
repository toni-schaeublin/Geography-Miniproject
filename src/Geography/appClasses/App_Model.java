package Geography.appClasses;

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
	private States globalStateList;
	private Countries globalCountryList;

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

	public States getGlobalStates() {
		return globalStateList;
	}
	
	
	public Countries getGlobalCountries() {
		return globalCountryList;
	}

	public void initializeGlobalLists() {
		State state = new State(0, 0, Government.none, "", "");
		globalStateList = new States();
		globalStateList.addState(state);
		Country country = new Country(0, 0, Government.none, "");
		globalCountryList = new Countries();
		globalCountryList.addCountry(country);
	}
}
